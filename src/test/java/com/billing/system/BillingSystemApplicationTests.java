package com.billing.system;

import com.billing.system.domain.entity.handler.BillHandleContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class BillingSystemApplicationTests {

	@Test
	void contextLoads() {
		BillHandleContext build = BillHandleContext.builder().build();
//		BillHandleContext build = new BillHandleContext();
		System.out.println(build.getLastTimeCalledCost());
	}

}
