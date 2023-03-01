package com.billing.system.application.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author xulingfeng
 * @description 开始计费指令
 * @date 2023/2
 */
@ApiModel
@Data
public class StartBillingPayload {

    @ApiModelProperty("主叫号")
    @NotNull(message = "主叫号不能为空")
    @Pattern(regexp = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", message = "主叫号格式不对")
    private String callerNumber;

    @ApiModelProperty("被叫号")
    @NotNull(message = "被叫号不能为空")
    @Pattern(regexp = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", message = "被叫号格式不对")
    private String calledNumber;
}
