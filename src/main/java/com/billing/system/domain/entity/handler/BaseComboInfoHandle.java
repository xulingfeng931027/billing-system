package com.billing.system.domain.entity.handler;

import com.billing.system.domain.entity.enumTypes.CallTypeEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author xulingfeng
 * @description 基础套餐
 * @date 2023/2
 */
@Component("base")
@Order(2)
public class BaseComboInfoHandle implements BillHandler {

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
    public void doHandler(BillHandleContext context) {
        Integer lastTimeInterval = context.getLastTimeInterval();
        BigDecimal lastTimeCost = context.getLastTimeCost();
        if (context.getCallType() == CallTypeEnum.CALLEDTYPE) {
            context.setLastTimeCost(lastTimeCost.add(calledCost.multiply(BigDecimal.valueOf(lastTimeInterval))));
        } else {
            context.setLastTimeCost(lastTimeCost.add(callingCost.multiply(BigDecimal.valueOf(lastTimeInterval))));
        }
    }
}
