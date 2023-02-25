package com.billing.system.application.service;

import com.billing.system.application.dto.BillingPayload;
import com.billing.system.application.dto.BillingResponseDTO;
import com.billing.system.application.dto.EndBillingPayload;
import com.billing.system.application.dto.StartBillingPayload;
import com.billing.system.application.mapstruct.BillingRecordMapStruct;
import com.billing.system.common.exception.ApiException;
import com.billing.system.domain.entity.BillingRecord;
import com.billing.system.domain.entity.enumTypes.BillingStatusEnum;
import com.billing.system.domain.entity.enumTypes.CallTypeEnum;
import com.billing.system.domain.entity.handler.BillHandleContext;
import com.billing.system.domain.entity.handler.BillHandler;
import com.billing.system.domain.repository.BillingRecordRepository;
import com.billing.system.domain.support.AccountInfoSupport;
import com.billing.system.infrastructure.po.BillingRecordPO;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Autowired
    private List<BillHandler> billHandlerList;

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
        return BillingRecordMapStruct.INSTANCE.toDTO(handleBilling0(billingPayload, BillingStatusEnum.BILLING));

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
     * @param billingStatus
     */
    @Transactional(rollbackFor = Exception.class)
    public BillingRecord handleBilling0(BillingPayload billingPayload, BillingStatusEnum billingStatus) {

        //验证计费记录是否存在
        BillingRecord billingRecord = billingRecordRepository.getByRecordId(billingPayload.getRecordId());
        if (Objects.isNull(billingRecord)) {
            throw new ApiException("计费记录不存在");
        }

        //验证计费状态
        if (billingRecord.checkBillingStatusIsEnd()) {
            throw new ApiException("计费已结束");
        }

        //修改最近计费时间为上一次计费结束时间
        billingRecord.modifyLatestBillingTime(billingRecord.getEndTime());

        //修改计费结束时间
        billingRecord.modifyBillingEnTime();

        //计算主叫号通话费用
        BillHandleContext callingContext = billing(billingRecord, CallTypeEnum.CALLINGTYPE);
        billingRecord.modifyCallingCost(callingContext.getLastTimeCost());
        accountInfoSupport.updateFixedTimeCombo(billingRecord.getCallerNumber(),callingContext.getFreeMinutes());


        //计算被叫号通话费用
        callingContext = billing(billingRecord, CallTypeEnum.CALLEDTYPE);
        billingRecord.modifyCalledCost(callingContext.getLastTimeCost());
        accountInfoSupport.updateFixedTimeCombo(billingRecord.getCalledNumber(),callingContext.getFreeMinutes());

        if (BillingStatusEnum.BILLING.equals(billingStatus)) {
            //修改计费状态为进行中
            billingRecord.modifyBillingStatusToOngoing();
        } else if (BillingStatusEnum.END.equals(billingStatus)) {
            //修改计费状态为计费结束
            billingRecord.modifyBillingStatusToEnd();
        }

        //更新计费记录
        billingRecordRepository.updateBillingRecord(billingRecord);
        return billingRecord;
    }

    private BillHandleContext billing(BillingRecord billingRecord, CallTypeEnum callType) {
        Integer totalTimeInterval = billingRecord.calculationTimeIntervalOfMinutes();
        Integer lastTimeInterval = billingRecord.calculationTimeIntervalOfMinutesForLastTime();
        BillHandleContext context = BillHandleContext.builder().callType(callType).calledNumber(billingRecord.getCalledNumber()).callerNumber(billingRecord.getCallerNumber()).lastTimeInterval(lastTimeInterval).totalTimeInterval(totalTimeInterval).build();
        for (BillHandler billHandler : billHandlerList) {
            billHandler.doHandler(context);
        }
        return context;
    }


}
