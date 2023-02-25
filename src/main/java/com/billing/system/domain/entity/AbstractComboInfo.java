package com.billing.system.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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

    /**
     * 计算主叫费用
     *
     * @param totalTimeInterval   通话总时长
     * @param lastTimeInterval    与上一次计费时间间隔（分钟）
     * @param lastTimeCallingCost 与上一次主叫计费费用
     * @param calledNumber        被叫号
     * @return
     */
}
