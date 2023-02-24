package com.billing.system.domain.entity.handler;

import com.billing.system.domain.support.AccountInfoSupport;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xulingfeng
 * @description 固定时长套餐
 * @date 2023/2
 */
@Getter
@SuperBuilder
@NoArgsConstructor
@Component("fixedTime")
@Slf4j
public class FamilyComboHandle implements Handler {

    @Autowired
    private AccountInfoSupport accountInfoSupport;


    @Override
    public void doHandler(HandleContext context) {
        List<String> familyNums = accountInfoSupport.queryFamilyNums(context.getCallingNumber());
        if (familyNums.contains(context.getCalledNumber())) {
            //说明是亲情号
            context.setLastTimeInterval(0);
        }

    }
}
