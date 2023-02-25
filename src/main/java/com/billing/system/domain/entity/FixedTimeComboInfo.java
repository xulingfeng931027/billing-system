package com.billing.system.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
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
@Data
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

}
