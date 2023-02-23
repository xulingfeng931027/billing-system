package com.billing.system.infrastructure.supportImpl;


import cn.hutool.http.HttpUtil;
import com.billing.system.domain.entity.BaseComboInfo;
import com.billing.system.domain.support.AccountInfoSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author xulingfeng
 * @description 号码服务实现, 模拟与账户系统交互，获取号码相关信息
 * @date 2022/9/11
 */
@Service
@Slf4j
public class AccountInfoSupportImpl implements AccountInfoSupport {
    // 模拟套餐库数据


    @Override
    public void checkNumberIsExist(String number) {
        HttpUtil.get("/isExist?number=" + number);
//        if (!numberSet.contains(number)) {
//            throw new BillingException(number + "号码不存在");
//        }
    }

    @Override
    public void updateNumberInfo(String number, BaseComboInfo comboInfo) {
        //模拟调用账户系统更新信息
    }

    @Override
    public BaseComboInfo getByNumber(String number) {
        //模拟账户系统获取号码套餐
        return null;
    }

    @Override
    public void debitCost(String callingNumber, BigDecimal callingCost, String calledNumber, BigDecimal calledCost) {
        //模拟通知扣费
        log.info("模拟通知扣费");
    }

    @Override
    public Integer queryRemainFreeTime(String number) {
        return null;
    }
}
