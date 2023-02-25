package com.billing.system.domain.entity.handler;

import java.math.BigDecimal;

/**
 * 抽象处理者
 *
 * @author 康康的远方
 * @date 2021/4/6
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
