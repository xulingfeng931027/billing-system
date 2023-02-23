package com.billing.system.infrastructure.repositoryImpl;

import com.billing.system.domain.entity.BillingRecord;
import com.billing.system.domain.repository.BillingRecordRepository;
import com.billing.system.infrastructure.mapper.BillingRecordMapper;
import com.billing.system.infrastructure.mapstruct.BillingRecordMapStruct;
import com.billing.system.infrastructure.po.BillingRecordPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author xulingfeng
 * @className BillingRecordRepositoryImpl
 * @description 计费记录仓储接口实现
 * @date 2023/2
 */
@Repository
@Slf4j
public class BillingRecordRepositoryImpl implements BillingRecordRepository {

    @Autowired
    private BillingRecordMapper billingRecordMapper;

    @Override
    public BillingRecord saveBillingRecord(BillingRecord billingRecord) {
        BillingRecordPO billingRecordPO = BillingRecordMapStruct.INSTANCE.toPO(billingRecord);
        billingRecordMapper.insert(billingRecordPO);
        billingRecord = BillingRecordMapStruct.INSTANCE.toEntity(billingRecordPO);
        log.info("保存记录ID：" + billingRecordPO.getId());
        return billingRecord;
    }

    @Override
    public void updateBillingRecord(BillingRecord billingRecord) {
        BillingRecordPO billingRecordPO = BillingRecordMapStruct.INSTANCE.toPO(billingRecord);
        billingRecordPO.setUpdateTime(new Date());
        billingRecordMapper.updateById(billingRecordPO);
    }

    @Override
    public BillingRecord getByRecordId(Long id) {
        BillingRecordPO billingRecordPO = billingRecordMapper.selectById(id);
        return BillingRecordMapStruct.INSTANCE.toEntity(billingRecordPO);
    }
}
