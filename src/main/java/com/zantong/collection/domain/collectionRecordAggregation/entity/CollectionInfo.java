package com.zantong.collection.domain.collectionRecordAggregation.entity;

import com.zantong.collection.domain.collectionInfoAggregation.valueobject.BusinessContract;
import com.zantong.collection.domain.collectionInfoAggregation.valueobject.CustomerContract;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xulingfeng
 * @date 2023/3/19
 * @description 代收信息
 */
@Data
public class CollectionInfo {

    /**
     * 收款金额
     */
    private BigDecimal amount;
    /**
     * 客户合约
     */
    private CustomerContract customerContract;
    /**
     * 客户合约
     */
    private BusinessContract businessContract;
    /**
     * 账户id
     */
    private String customerAccountInfoId;
    /**
     * 关联的账户id
     */
    private String businessAccountInfoId;

    private Integer result;
    /**
     * 账户流水号
     */
    private String accountSerialNumber;


}
