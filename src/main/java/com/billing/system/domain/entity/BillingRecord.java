package com.billing.system.domain.entity;

import com.billing.system.common.exception.ApiException;
import com.billing.system.domain.entity.enumTypes.BillingStatusEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @author xulingfeng
 * @description
 * @date 2023/2
 */
@Data
@Builder
public class BillingRecord {
    /**
     * 记录ID
     */
    private Long id;

    /**
     * 计费状态
     */
    private BillingStatusEnum billingStatus;

    /**
     * 计费开始时间
     */
    private Date startTime;

    /**
     * 计费结束时间
     */
    private Date endTime;

    /**
     * 最近一次计费时间
     */
    private Date latestBillingTime;

    /**
     * 主叫号
     */
    private String callerNumber;

    /**
     * 主叫号费用
     * 备注：后续类型调整为BigDecimal
     */
    private BigDecimal callerCost;

    /**
     * 被叫号
     */
    private String calledNumber;

    /**
     * 被叫号费用
     */
    private BigDecimal calledCost;


    /**
     * 设置计费状态为开始计费
     */
    public void setBillingStatusToStart() {
        if (this.billingStatus != null) {
            throw new ApiException("计费状态已设置，不可再设置为开始计费状态");
        }
        if (this.startTime != null) {
            throw new ApiException("计费开始时间已设置，非未设置开始计费状态");
        }
        this.billingStatus = BillingStatusEnum.START;
        //初次设置为开始计费状态，计费开始时间同时设置
        this.startTime = new Date();
    }

    /**
     * 修改计费结束时间为当前时间
     */
    public void modifyBillingEnTime() {
        this.endTime = new Date();
    }

    /**
     * 修改记录状态为计费进行中
     *
     * @return
     */
    public void modifyBillingStatusToOngoing() {
        boolean isStatusEnd = checkBillingStatusIsEnd();
        if (isStatusEnd) {
            throw new ApiException("计费已结束");
        }
        this.billingStatus = BillingStatusEnum.BILLING;
    }

    /**
     * 修改记录状态为计费结束
     */
    public void modifyBillingStatusToEnd() {
        boolean isStatusEnd = checkBillingStatusIsEnd();
        if (isStatusEnd) {
            throw new ApiException("计费已结束");
        }
        this.billingStatus = BillingStatusEnum.END;
    }

    /**
     * 校验计费状态是否计费结束
     *
     * @return
     */
    public boolean checkBillingStatusIsEnd() {
        if (this.billingStatus == null) {
            throw new ApiException("计费状态为空");
        }
        return this.billingStatus.equals(BillingStatusEnum.END);
    }

    /**
     * 计算通话总时长（分钟）
     *
     * @return
     */
    public Integer calculationTimeIntervalOfMinutes() {
        if (this.startTime == null) {
            throw new ApiException("计费开始时间不能为空");
        }
        if (this.endTime == null) {
            throw new ApiException("计费结束时间不能为空");
        }

        if (endTime.before(endTime)) {
            throw new ApiException("计费结束时间不能早于开始时间");
        }

        int periodTimeOfMinute = (int) ((endTime.getTime() - startTime.getTime()) / 1000 / 60);
        return new Integer(periodTimeOfMinute);
    }

    /**
     * 修改最近计费结束时间
     *
     * @param latestBillingTime
     */
    public void modifyLatestBillingTime(Date latestBillingTime) {
        if (!Objects.isNull(latestBillingTime)) {
            this.latestBillingTime = latestBillingTime;
        }
    }

    /**
     * 计算与上一次计费时间间隔（分钟）
     *
     * @return
     */
    public Integer calculationTimeIntervalOfMinutesForLastTime() {
        if (this.latestBillingTime == null) {
            int periodTimeOfMinute = (int) ((endTime.getTime() - startTime.getTime()) / 1000 / 60);
            return new Integer(periodTimeOfMinute);
        } else {
            int periodTimeOfMinute = (int) ((endTime.getTime() - latestBillingTime.getTime()) / 1000 / 60);
            return new Integer(periodTimeOfMinute);
        }
    }

    /**
     * 修改主叫号费用
     *
     * @param callingCost
     */
    public void modifyCallingCost(BigDecimal callingCost) {
        this.callerCost = callingCost;
    }

    /**
     * 修改被叫号费用
     *
     * @param callingCost
     */
    public void modifyCalledCost(BigDecimal callingCost) {
        this.callerCost = callingCost;
    }


}
