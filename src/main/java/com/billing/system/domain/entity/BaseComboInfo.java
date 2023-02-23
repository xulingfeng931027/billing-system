package com.billing.system.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * @author xulingfeng
 * @description 基础套餐
 * @date 2023/2
 */
@Getter
@SuperBuilder
@NoArgsConstructor
public class BaseComboInfo extends AbstractComboInfo {

    /**
     * 计算主叫费用
     *
     * @param totalTimeInterval   通话总时长
     * @param lastTimeInterval    与上一次计费时间间隔（分钟）
     * @param lastTimeCallingCost 与上一次主叫计费费用
     * @param calledNumber        被叫号
     * @return
     */
    public BigDecimal computeCostForCalling(Integer totalTimeInterval, Integer lastTimeInterval, BigDecimal lastTimeCallingCost, String calledNumber) {
        if (lastTimeCallingCost == null) {
            lastTimeCallingCost = ZERO_COST;
        }
        return lastTimeCallingCost.add(callingCost.multiply(BigDecimal.valueOf(lastTimeInterval)));
    }

    /**
     * 计算被叫费用
     *
     * @param totalTimeInterval  通话总时长
     * @param lastTimeInterval   与上一次计费时间间隔（分钟）
     * @param lastTimeCalledCost 与上一次被叫计费费用
     * @param callingNumber      主叫号
     * @return
     */
    public BigDecimal computeCostForCalled(Integer totalTimeInterval, Integer lastTimeInterval, BigDecimal lastTimeCalledCost, String callingNumber) {
        if (lastTimeCalledCost == null) {
            lastTimeCalledCost = ZERO_COST;
        }
        return lastTimeCalledCost.add(calledCost.multiply(BigDecimal.valueOf(lastTimeInterval)));
    }

}
