package com.billing.system.domain.support;

import com.billing.system.domain.entity.BaseComboInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author xulingfeng
 * @className NumberSupport
 * @description 防腐层  调用账户系统
 * @date 2022/9/11
 */
public interface AccountInfoSupport {
    /**
     * 校验号码是否存在
     *
     * @param number
     * @return
     */
    void checkNumberIsExist(String number);

    /**
     * 更新号码信息（比如固定时长套餐剩余免费分钟数（主叫与被叫））
     *
     * @param number
     * @param minutes
     */
    void updateFixedTimeCombo(String number, Integer minutes);

    /**
     * 根据号码获取套餐信息
     *
     * @param number
     * @return
     */
    BaseComboInfo getByNumber(String number);

    /**
     * 通知账户扣减费用
     *
     * @param callingNumber
     * @param callingCost
     * @param calledNumber
     * @param calledCost
     */
    void debitCost(String callingNumber, BigDecimal callingCost, String calledNumber, BigDecimal calledCost);

    /**
     * 查询套餐剩余免费分钟数
     *
     * @param number
     * @return
     */
    Integer queryRemainFreeTime(String number);

    /**
     * 查询套餐剩余免费分钟数
     *
     * @param number
     * @return
     */
    List<String> queryFamilyNums(String number);

}
