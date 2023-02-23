package com.billing.system.infrastructure.mapstruct;

import com.billing.system.domain.entity.BillingRecord;
import com.billing.system.infrastructure.po.BillingRecordPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author xulingfeng
 * @className BillingRecordConverter
 * @description
 * @date 2023/2
 */
@Mapper
public interface BillingRecordMapStruct {

    BillingRecordMapStruct INSTANCE = Mappers.getMapper(BillingRecordMapStruct.class);

    BillingRecordPO toPO(BillingRecord billingRecord);


    BillingRecord toEntity(BillingRecordPO billingRecordPO);
}
