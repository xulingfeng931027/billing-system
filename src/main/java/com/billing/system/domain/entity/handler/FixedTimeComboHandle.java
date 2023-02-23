package com.billing.system.domain.entity.handler;

import com.billing.system.domain.entity.enumTypes.CallTypeEnum;
import com.billing.system.domain.support.AccountInfoSupport;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xulingfeng
 * @description 固定时长套餐
 * @date 2023/2
 */
@Getter
@SuperBuilder
@NoArgsConstructor
@Component("fixedTime")
public class FixedTimeComboHandle implements Handler {

    @Autowired
    private AccountInfoSupport accountInfoSupport;


    @Override
    public void doHandler(HandleContext context) {
        Integer lastTimeInterval = context.getLastTimeInterval();
        if (context.getCallType() == CallTypeEnum.CALLEDTYPE) {
            Integer remainFreeTime = accountInfoSupport.queryRemainFreeTime(context.getCalledNumber());
            //通话时长小于等于主加免费分钟数，通话费用为0，并扣减可使用的主叫免费分钟数
            if (lastTimeInterval <= remainFreeTime) {
                context.getBillResult().setFreeMinutes(remainFreeTime - lastTimeInterval);
                context.setStopFlag(true);
                return;
            }
            //免费时长不够用了
            context.setLastTimeInterval(lastTimeInterval - remainFreeTime);
            context.getBillResult().setFreeMinutes(ZERO_MINUTE);
        } else {

        }

    }
}
