package com.billing.system.application.service;

import com.billing.system.application.dto.BillingPayload;
import com.billing.system.application.dto.BillingResponseDTO;
import com.billing.system.application.dto.EndBillingPayload;
import com.billing.system.application.dto.StartBillingPayload;
import com.billing.system.application.mapstruct.BillingRecordMapStruct;
import com.billing.system.common.exception.ApiException;
import com.billing.system.domain.entity.BaseComboInfo;
import com.billing.system.domain.entity.BillingRecord;
import com.billing.system.domain.entity.enumTypes.BillingStatusEnum;
import com.billing.system.domain.entity.enumTypes.CallTypeEnum;
import com.billing.system.domain.repository.BillingRecordRepository;
import com.billing.system.domain.support.AccountInfoSupport;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author xulingfeng
 * @className BillingRecordService
 * @description 计费服务
 * @date 2023/2
 */
@Service
public class BillingRecordService {

    @Autowired
    private BillingRecordRepository billingRecordRepository;

    @Autowired
    private AccountInfoSupport numberSupport;

    @Autowired
    private AccountInfoSupport accountInfoSupport;

    /**
     * 开始计费
     *
     * @param startBillingPayload
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public BillingResponseDTO startBilling(StartBillingPayload startBillingPayload) {
        BillingRecord billingRecord = BillingRecordMapStruct.INSTANCE.toEntity(startBillingPayload);
        //验证主号是否存在(当前从账户系统获取)
        numberSupport.checkNumberIsExist(billingRecord.getCallerNumber());

        //验证被叫号是否存在
        numberSupport.checkNumberIsExist(billingRecord.getCalledNumber());

        //设置为开始计费状态
        billingRecord.setBillingStatusToStart();

        //保存计费记录
        billingRecord = billingRecordRepository.saveBillingRecord(billingRecord);

        return BillingRecordMapStruct.INSTANCE.toDTO(billingRecord);
    }

    /**
     * 进行计费
     *
     * @return
     */
    public BillingResponseDTO handleBilling(BillingPayload billingPayload) {
        //根据计费id查询计费记录
        BillingRecord billingRecord = BillingRecordMapStruct.INSTANCE.toEntity(billingPayload);

        return BillingRecordMapStruct.INSTANCE.toDTO(handleBilling0(billingRecord, BillingStatusEnum.BILLING));

    }

    /**
     * 结束计费
     *
     * @param endBillingPayload
     * @return
     */
    public BillingResponseDTO endBilling(EndBillingPayload endBillingPayload) {
        BillingRecord billingRecord = BillingRecordMapStruct.INSTANCE.toEntity(endBillingPayload);

        //计费， AopContext解决同类中方法调用切面切不到问题
        billingRecord = ((BillingRecordService) AopContext.currentProxy()).handleBilling0(billingRecord, BillingStatusEnum.END);

        //扣减费用
        accountInfoSupport.debitCost(billingRecord.getCallerNumber(), billingRecord.getCallerCost(),
                billingRecord.getCalledNumber(), billingRecord.getCalledCost());

        return BillingRecordMapStruct.INSTANCE.toDTO(billingRecord);
    }

    /**
     * 计费步骤
     *
     * @param billingRecord
     * @param billingStatus
     */
    @Transactional(rollbackFor = Exception.class)
    public BillingRecord handleBilling0(BillingRecord billingRecord, BillingStatusEnum billingStatus) {

        //验证计费记录是否存在
        billingRecord = billingRecordRepository.getByRecordId(billingRecord.getId());
        if (Objects.isNull(billingRecord)) {
            throw new ApiException("计费记录不存在");
        }

        //验证计费状态
        boolean isStatusEnd = billingRecord.checkBillingStatusIsEnd();
        if (isStatusEnd) {
            throw new ApiException("计费已结束");
        }

        //获取主叫号套餐信息
        BaseComboInfo callingNumberComboInfo = accountInfoSupport.getByNumber(billingRecord.getCallerNumber());

        //获取被叫号套餐信息

        BaseComboInfo calledNumberComboInfo = accountInfoSupport.getByNumber(billingRecord.getCalledNumber());

        //修改最近计费时间为上一次计费结束时间
        billingRecord.modifyLatestBillingTime(billingRecord.getEndTime());

        //修改计费结束时间
        billingRecord.modifyBillingEnTime();

        //计算主叫号通话费用
        BigDecimal callingCost = billing(billingRecord, callingNumberComboInfo, CallTypeEnum.CALLINGTYPE);
        billingRecord.modifyCallingCost(callingCost);

        //计算被叫号通话费用
        BigDecimal calledCost = billing(billingRecord, calledNumberComboInfo, CallTypeEnum.CALLEDTYPE);
        billingRecord.modifyCalledCost(calledCost);

        if (BillingStatusEnum.BILLING.equals(billingStatus)) {
            //修改计费状态为进行中
            billingRecord.modifyBillingStatusToOngoing();
        } else if (BillingStatusEnum.END.equals(billingStatus)) {
            //修改计费状态为计费结束
            billingRecord.modifyBillingStatusToEnd();
        }

        //更新计费记录
        billingRecordRepository.updateBillingRecord(billingRecord);

        //通知账户系统，更新号码套餐内容，比如剩余免费主叫与被叫通话时长
        accountInfoSupport.updateNumberInfo(billingRecord.getCallerNumber(), callingNumberComboInfo);
        accountInfoSupport.updateNumberInfo(billingRecord.getCalledNumber(), calledNumberComboInfo);
        return billingRecord;
    }

    private BigDecimal billing(BillingRecord billingRecord, BaseComboInfo comboInfo, CallTypeEnum callType) {
        Integer totalTimeInterval = billingRecord.calculationTimeIntervalOfMinutes();
        Integer lastTimeInterval = billingRecord.calculationTimeIntervalOfMinutesForLastTime();

        if (CallTypeEnum.CALLINGTYPE.equals(callType)) {
            BigDecimal callingCost = comboInfo.computeCostForCalling(totalTimeInterval, lastTimeInterval, billingRecord.getCallerCost(), billingRecord.getCalledNumber());
            return callingCost;
        } else if (CallTypeEnum.CALLEDTYPE.equals(callType)) {
            BigDecimal calledCost = comboInfo.computeCostForCalled(totalTimeInterval, lastTimeInterval, billingRecord.getCalledCost(), billingRecord.getCallerNumber());
            return calledCost;
        } else {
            throw new ApiException("不支持通话类型");
        }
    }


}
