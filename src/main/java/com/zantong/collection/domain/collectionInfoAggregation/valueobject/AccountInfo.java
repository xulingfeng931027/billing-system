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
public class AccountInfo {
    /**
     * 账户id
     */
    private String id;
    /**
     * 账户名
     */
    private String accountName;
    /**
     * 账号
     */
    private String accountNumber;
    /**
     * 开户行
     */
    private String bankName;

}

