package com.zantong.collection.application.assembly;

import com.zantong.collection.domain.collectionInfoAggregation.valueobject.BusinessContract;
import com.zantong.collection.domain.collectionInfoAggregation.valueobject.CustomerContract;
import com.zantong.collection.domain.collectionRecordAggregation.entity.CollectionInfo;
import com.zantong.contract.application.dto.CollectionInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author xulingfeng
 * @description 计费记录转配器
 * @date 2023/2
 */
@Mapper
public interface CollectionRecordAssembler {

    CollectionRecordAssembler INSTANCE = Mappers.getMapper(CollectionRecordAssembler.class);

    CollectionInfo toEntity(CollectionInfoDto dto);

    default CollectionInfo toEntity(BusinessContract businessContract, CustomerContract customerContract, BigDecimal amount, Map<String, Object> result) {
        CollectionInfo toEntity = new CollectionInfo();
        toEntity.setAmount(amount);
        toEntity.setCustomerContract(customerContract);
        toEntity.setBusinessContract(businessContract);
        toEntity.setCustomerAccountInfoId(customerContract.getAccountInfoId());
        toEntity.setBusinessAccountInfoId(businessContract.getAccountInfoId());
        toEntity.setResult((Integer) result.get("result"));
        toEntity.setAccountSerialNumber((String) result.get("accountSerialNumber"));
        return toEntity;
    }


}