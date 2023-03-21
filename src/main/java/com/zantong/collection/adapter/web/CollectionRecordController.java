package com.zantong.collection.adapter.web;

import com.zantong.collection.application.service.CollectionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xulingfeng
 * @className BillingController
 * @description
 * @date 2023/2
 */
@Api("签约接口")
@RestController
@RequestMapping("/collction/record")
public class CollectionRecordController {

    @Autowired
    private CollectionService contractService;

}
