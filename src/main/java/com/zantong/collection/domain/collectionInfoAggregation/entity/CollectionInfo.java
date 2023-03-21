package com.zantong.collection.domain.collectionInfoAggregation.entity;

import com.zantong.collection.domain.collectionInfoAggregation.valueobject.AccountInfo;
import com.zantong.contract.domain.entity.CustomerContract;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xulingfeng
 * @date 2023/3/19
 * @description 代收信息
 */
public class CollectionInfo {

    /**
     * 收款金额
     */
    private BigDecimal amount;
    /**
     * 收款结果
     */
    private Integer result;
    /**
     * 商户账户信息
     */
    private String businessAccountInfoId;
    /**
     * 商户账户信息
     */
    private AccountInfo customerAccountInfoId;
    /**
     * 商户账户信息
     */
    private AccountInfo businessAccountInfo;
    /**
     * 客户合约
     */
    private CustomerContract customerContract;
    /**
     * 创建时间
     */
    private Date createTime;
}
