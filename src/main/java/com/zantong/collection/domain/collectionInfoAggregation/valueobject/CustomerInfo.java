package com.zantong.collection.domain.collectionInfoAggregation.valueobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @author xulingfeng
 * @date 2023/3/17
 * @description 账户信息
 */
@Getter
@Builder
@AllArgsConstructor
public class CustomerInfo {

    /**
     * 名称
     */
    private String name;
    /**
     * 电话
     */
    private String phone;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 用户编号
     */
    private String customerCode;
    /**
     * 证件号
     */
    private String idCard;
    public boolean checkStatus() {
        return status == 1;
    }

    public boolean checkLegalPersonId() {
        return idCard != null;
    }

}

