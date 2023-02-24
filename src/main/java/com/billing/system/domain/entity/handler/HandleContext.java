package com.billing.system.domain.entity.handler;

import com.billing.system.domain.entity.enumTypes.CallTypeEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class HandleContext {

    /**
     * 总时长
     */
    private Integer totalTimeInterval;
    /**
     * 跟上一次间隔的时长
     */
    private Integer lastTimeInterval;
    /**
     * 上一次计费的费用
     */
    private BigDecimal lastTimeCallingCost = Handler.ZERO_COST;
    /**
     * 上一次计费的费用
     */
    private BigDecimal lastTimeCalledCost = Handler.ZERO_COST;
    private String calledNumber;

    private String callingNumber;
    private CallTypeEnum callType;

    private BillResult calledBillResult;

    private BillResult callingBillResult;
}
