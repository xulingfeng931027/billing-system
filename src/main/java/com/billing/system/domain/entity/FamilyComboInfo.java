package com.billing.system.domain.entity;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @author xulingfeng
 * @description 家庭套餐
 * @date 2023/2
 */
@Getter
@SuperBuilder
@Slf4j
public class FamilyComboInfo extends AbstractComboInfo {

    /**
     * 亲情号
     */
    private List<String> familiarityNumbers;

    /**
     * 月固定费用
     */
    private BigDecimal monthlyFixedCost;

    @Override
    public BigDecimal computeCostForCalling(Integer totalTimeInterval, Integer lastTimeInterval, BigDecimal lastTimeCallingCost, String calledNumber) {
        if (isFamiliartiyNumblers(calledNumber)) {
            log.info(calledNumber + "与被叫号为亲情号");
            return ZERO_COST;
        } else {
            return super.computeCostForCalling(totalTimeInterval, lastTimeInterval, lastTimeCallingCost, calledNumber);
        }
    }

    @Override
    public BigDecimal computeCostForCalled(Integer totalTimeInterval, Integer lastTimeInterval, BigDecimal lastTimeCalledCost, String callingNumber) {
        if (isFamiliartiyNumblers(callingNumber)) {
            log.info(callingNumber + "与主叫号为亲情号");
            return ZERO_COST;
        } else {
            return super.computeCostForCalled(totalTimeInterval, lastTimeInterval, lastTimeCalledCost, callingNumber);
        }
    }

    /**
     * 校验号码是否在亲情号中
     *
     * @param number
     * @return
     */
    private boolean isFamiliartiyNumblers(String number) {
        if (Objects.isNull(familiarityNumbers) || !familiarityNumbers.contains(number)) {
            return false;
        } else {
            return true;
        }
    }

}
