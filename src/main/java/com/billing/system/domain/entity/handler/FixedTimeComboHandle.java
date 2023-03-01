package com.billing.system.domain.entity.handler;

import com.billing.system.domain.entity.enumTypes.CallTypeEnum;
import com.billing.system.domain.support.AccountInfoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author xulingfeng
 * @description 固定时长套餐
 * @date 2023/2
 */
@Component("fixedTime")
@Order(1)
public class FixedTimeComboHandle implements BillHandler {

    @Autowired
    private AccountInfoSupport accountInfoSupport;


    @Override
    public void doHandler(BillHandleContext context) {
        Integer lastTimeInterval = context.getLastTimeInterval();
        Integer remainFreeTime;
        if (context.getCallType() == CallTypeEnum.CALLEDTYPE) {
            remainFreeTime = accountInfoSupport.queryRemainFreeTime(context.getCalledNumber());

        } else {
            remainFreeTime = accountInfoSupport.queryRemainFreeTime(context.getCallerNumber());
        }
        //通话时长小于等于主加免费分钟数，通话费用为0，并扣减可使用的主叫免费分钟数
        //免费时长不够用了
        if (lastTimeInterval <= remainFreeTime) {
            context.setFreeMinutes(remainFreeTime - lastTimeInterval);
            context.setLastTimeInterval(0);
            return;
        }
        context.setLastTimeInterval(lastTimeInterval - remainFreeTime);
        context.setFreeMinutes(ZERO_MINUTE);

    }
}
