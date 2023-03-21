package com.zantong.contract.application.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @author xulingfeng
 * @date 2023/3/19
 * @description 代收信息
 */
@Data
public class CollectionInfoDto {

    /**
     * 收款金额
     */
    @Min(0)
    private BigDecimal amount;
    /**
     * 用户编码
     */
    @NotBlank
    private String userCode;
    /**
     * 项目代码
     */
    @NotBlank
    private String projectCode;
}
