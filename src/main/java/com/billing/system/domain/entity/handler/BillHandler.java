package com.billing.system.domain.entity.handler;

import java.math.BigDecimal;

/**
 * @author xulingfeng
 * @Description  计费处理器
 * @date 2023/2
 */
public interface BillHandler {

    /**
     * 零费用
     */
    BigDecimal ZERO_COST = BigDecimal.ZERO;

    /**
     * 零分钟
     */
    Integer ZERO_MINUTE = Integer.parseInt("0");


    void doHandler(BillHandleContext context);
}
