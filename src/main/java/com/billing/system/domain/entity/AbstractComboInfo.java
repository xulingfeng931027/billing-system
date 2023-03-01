package com.billing.system.domain.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xulingfeng
 * @description 基础套餐
 * @date 2023/2
 */
@Data
public abstract class AbstractComboInfo {
    /**
     * 主叫每分钟费用
     */
    protected BigDecimal callingCost;

    /**
     * 被叫每分钟费用
     */
    protected BigDecimal calledCost;


    /**
     * 零费用
     */
    BigDecimal ZERO_COST = BigDecimal.ZERO;

    /**
     * 零分钟
     */
    Integer ZERO_MINUTE = Integer.parseInt("0");

}
