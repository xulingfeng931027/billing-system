package com.billing.system.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author xulingfeng
 * @description 统一响应格式类型
 * @date 2023/2
 */
@ApiModel
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ResponseDTO<T> {
    @ApiModelProperty("响应状态")
    @NonNull
    private Status status;
    @ApiModelProperty("响应信息")
    private T message;

    public enum Status {
        SUCCESS, FAILURE
    }
}
