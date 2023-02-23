package com.billing.system.domain.entity.handler;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author xulingfeng
 * @description 基础套餐
 * @date 2023/2
 */
@Getter
@SuperBuilder
@NoArgsConstructor
@Component("base")
public class BaseComboInfoHandle implements Handler {

    //也可从数据库中读取
    /**
     * 主叫每分钟费用
     */
    @Value("${callingCost:0.5}")
    private BigDecimal callingCost;

    /**
     * 被叫每分钟费用
     */
    @Value("${callingCost:0.4}")
    private BigDecimal calledCost;


    @Override
    public void doHandler(HandleContext context) {
        BigDecimal lastTimeCalledCost = context.getLastTimeCalledCost();
        BigDecimal lastTimeCallingCost = context.getLastTimeCallingCost();
        Integer lastTimeInterval = context.getLastTimeInterval();
        context.setLastTimeCallingCost(lastTimeCallingCost.add(callingCost.multiply(BigDecimal.valueOf(lastTimeInterval))));
        context.setLastTimeCalledCost(lastTimeCalledCost.add(calledCost.multiply(BigDecimal.valueOf(lastTimeInterval))));
    }
}
