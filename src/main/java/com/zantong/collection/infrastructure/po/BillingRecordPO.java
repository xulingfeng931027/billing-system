package com.zantong.collection.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xulingfeng
 * @className BillingRecordPO
 * @description 计费记录
 * @date 2023/2
 */
@TableName("billing_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillingRecordPO {

    /**
     * 记录ID
     */
    private Long id;

    /**
     * 计费状态
     */
    private String billingStatus;

    /**
     * 计费开始时间
     */
    private Date startTime;

    /**
     * 计费结束时间
     */
    private Date endTime;

    /**
     * 主叫号码
     */
    private String callerNumber;

    /**
     * 被叫号码
     */
    private String calledNumber;

    /**
     * 主叫费用
     */
    private BigDecimal callingCost;

    /**
     * 被叫费用
     */
    private BigDecimal calledCost;


    /**
     * 被叫费用
     */
    private String comboInfo;

    private Date createTime;

    private Date updateTime;
}
