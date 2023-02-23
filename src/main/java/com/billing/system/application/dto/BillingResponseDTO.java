package com.billing.system.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author xulingfeng
 * @className BillingResponseDTO
 * @description
 * @date 2022/9/11
 */
@ApiModel
@Data
@AllArgsConstructor
public class BillingResponseDTO {
    /**
     * 记录ID
     * */
    @ApiModelProperty("记录ID")
    private String recordId;

    /**
     * 计费状态
     */
    @ApiModelProperty("计费状态")
    private String billingStatus;

    /**
     * 计费开始时间
     */
    @ApiModelProperty("计费开始时间")
    private Date startTime;

    /**
     * 计费结束时间
     */
    @ApiModelProperty("计费结束时间")
    private Date endTime;

    /**
     * 主叫号
     */
    @ApiModelProperty("主叫号")
    private String callingNumber;

    /**
     * 主叫号费用
     */
    @ApiModelProperty("主叫号费用")
    private BigDecimal callingCost;

    /**
     * 被叫号
     */
    @ApiModelProperty("被叫号")
    private String calledNumber;

    /**
     * 被叫号费用
     */
    @ApiModelProperty("被叫号费用")
    private BigDecimal calledCost;
}
