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
public class FamilyComboInfo extends AbstractComboInfo {

    /**
     * 亲情号
     */
    private List<String> familiarityNumbers;

    /**
     * 月固定费用
     */
    private BigDecimal monthlyFixedCost;

}
