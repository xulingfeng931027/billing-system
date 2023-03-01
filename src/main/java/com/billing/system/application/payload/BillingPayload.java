package com.billing.system.application.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author xulingfeng
 * @description 计费指令
 * @date 2022/9/11
 */
@ApiModel
@Data
public class BillingPayload {
    @ApiModelProperty("计费记录ID")
    @NotNull(message = "记录ID必传")
    private Long recordId;

}
