package com.billing.system.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author xulingfeng
 * @description 结束计费指令
 * @date 2022/9/11
 */
@ApiModel
@Data
public class EndBillingPayload {

    @ApiModelProperty("计费记录ID")
    @NotNull(message = "记录ID必传")
    @NotEmpty(message = "记录ID不能为空字符串")
    private String recordId;

}
