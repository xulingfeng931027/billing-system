package com.billing.system.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author xulingfeng
 * @description 固定时长套餐
 * @date 2023/2
 */
@Getter
@AllArgsConstructor
@SuperBuilder
@Slf4j
public class FixedTimeComboInfo extends AbstractComboInfo {

    /**
     * 主叫免费分钟数
     */
    private Integer callingFreeMinutes;

    /**
     * 被叫免费分钟数
     */
    private Integer calledFreeMinutes;

    /**
     * 月固定费用
     */
    private BigDecimal monthlyFixedCost;

    @Override
    public BigDecimal computeCostForCalling(Integer totalTimeInterval, Integer lastTimeInterval, BigDecimal lastTimeCallingCost, String calledNumber) {
        log.info("剩余主叫免费分钟数：" + callingFreeMinutes);
        log.info("通话时长（分钟）：" + totalTimeInterval);
        log.info("与上一次计费通话时长（分钟）：" + lastTimeInterval);
        //通话时长小于等于主加免费分钟数，通话费用为0，并扣减可使用的主叫免费分钟数
        if (lastTimeInterval <= callingFreeMinutes) {
            this.callingFreeMinutes = this.callingFreeMinutes - lastTimeInterval;
            log.info("扣减后剩余主叫免费`分钟数：" + callingFreeMinutes);
            return ZERO_COST;
        }
        //免费市场不够用了
        lastTimeInterval = lastTimeInterval - this.calledFreeMinutes;
        log.info("扣减后剩余主叫免费分钟数：" + callingFreeMinutes);
        this.callingFreeMinutes = ZERO_MINUTE;
        return super.computeCostForCalling(totalTimeInterval, lastTimeInterval, lastTimeCallingCost, calledNumber);
    }

    @Override
    public BigDecimal computeCostForCalled(Integer totalTimeInterval, Integer lastTimeInterval, BigDecimal lastTimeCalledCost, String callingNumber) {
        log.info("剩余被叫免费分钟数：" + calledFreeMinutes);
        int compare = Integer.compare(lastTimeInterval, this.calledFreeMinutes);
        //通话时长小于等于主加免费分钟数，通话费用为0，并扣减可使用的主叫免费分钟数
        if (compare <= 0) {
            this.calledFreeMinutes = this.calledFreeMinutes - lastTimeInterval;
            return ZERO_COST;
        }
        lastTimeInterval = lastTimeInterval - this.calledFreeMinutes;
        this.calledFreeMinutes = ZERO_MINUTE;
        if (Objects.isNull(lastTimeCalledCost)) {
            return calledCost.multiply(new BigDecimal(lastTimeInterval));
        } else {
            return calledCost.multiply(new BigDecimal(lastTimeInterval)).add(lastTimeCalledCost);
        }
    }
}
