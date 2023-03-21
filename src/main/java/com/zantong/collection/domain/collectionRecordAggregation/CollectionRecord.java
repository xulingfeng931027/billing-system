package com.zantong.collection.domain.collectionRecordAggregation;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xulingfeng
 * @date 2023/3/19
 * @description 代收信息
 */
public class CollectionRecord {

    /**
     * 收款金额
     */
    private BigDecimal amount;
    /**
     * 客户合约
     */
    private Integer result;
    /**
     * 商户账户信息
     */
    private String businessAccountInfo;
    /**
     * 商户账户信息
     */
    private String customerAccountInfoId;
    /**
     * 商户账户信息
     */
    private String businessContractId;
    /**
     * 客户合约
     */
    private String customerContractId;
    /**
     * 创建时间
     */
    private Date createTime;
}
