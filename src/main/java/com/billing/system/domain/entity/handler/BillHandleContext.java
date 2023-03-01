package com.billing.system.domain.entity.handler;

import com.billing.system.domain.entity.enumTypes.CallTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author xulingfeng
 * @Description  计费信息上下文
 * @date 2023/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillHandleContext {

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
    @Builder.Default
    private BigDecimal lastTimeCost = BillHandler.ZERO_COST;
    private String calledNumber;

    private String callerNumber;
    private CallTypeEnum callType;
    /**
     * 扣除的免费分钟数
     */
    private Integer freeMinutes;


}
