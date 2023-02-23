package com.billing.system.domain.entity.handler;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BillResult {

    /**
     * 通话费用
     */
    private BigDecimal cost;
    /**
     * 扣除的免费分钟数
     */
    private Integer freeMinutes;
    /**
     * 家庭套餐免费分钟数
     */
    private Integer familyFreeMinutes;
}
