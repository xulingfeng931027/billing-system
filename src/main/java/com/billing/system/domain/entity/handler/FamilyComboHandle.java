package com.billing.system.domain.entity.handler;

import com.billing.system.domain.support.AccountInfoSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xulingfeng * @description 亲情套餐
 * @date 2023/2
 */
@Component("fixedTime")
@Slf4j
@Order(0)
public class FamilyComboHandle implements BillHandler {

    @Autowired
    private AccountInfoSupport accountInfoSupport;


    @Override
    public void doHandler(BillHandleContext context) {
        List<String> familyNums = accountInfoSupport.queryFamilyNums(context.getCallerNumber());
        if (familyNums.contains(context.getCalledNumber())) {
            //说明是亲情号 直接计费为0
            context.setLastTimeInterval(0);
        }

    }
}
