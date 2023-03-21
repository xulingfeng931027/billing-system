package com.zantong.collection.domain.support;

import com.zantong.collection.domain.collectionInfoAggregation.valueobject.BusinessContract;
import com.zantong.collection.domain.collectionInfoAggregation.valueobject.CustomerContract;

/**
 * @author xulingfeng
 * @className NumberSupport
 * @description 防腐层  调用账户系统
 * @date 2022/9/11
 */
public interface ContractSupport {
    /**
     * 查商户合约
     */
    BusinessContract queryBusinessContract(String projectCode);
    /**
     * 查客户合约
     */
    CustomerContract queryCustomerContract(String projectCode,String userCode);

}
