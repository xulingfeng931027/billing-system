package com.billing.system.application.mapstruct;

import com.billing.system.application.dto.BillingResponseDTO;
import com.billing.system.application.dto.EndBillingPayload;
import com.billing.system.application.dto.BillingPayload;
import com.billing.system.application.dto.StartBillingPayload;
import com.billing.system.domain.entity.BillingRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author xulingfeng
 * @description 计费记录转配器
 * @date 2023/2
 */
@Mapper
public interface BillingRecordMapStruct {

    BillingRecordMapStruct INSTANCE = Mappers.getMapper(BillingRecordMapStruct.class);

    @Mappings({
            @Mapping(source = "callerNumber", target = "callerNumber", resultType = String.class),
            @Mapping(source = "calledNumber", target = "calledNumber", resultType = String.class)
    })
    BillingRecord toEntity(StartBillingPayload startBillingPayload);

    BillingRecord toEntity(BillingPayload billingPayload);

    BillingRecord toEntity(EndBillingPayload endBillingPayload);

    @Mapping(source = "id", target = "recordId", resultType = String.class)
    BillingResponseDTO toDTO(BillingRecord billingRecord);
}
