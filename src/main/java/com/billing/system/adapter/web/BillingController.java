package com.billing.system.adapter.web;

import com.billing.system.application.dto.*;
import com.billing.system.application.service.BillingRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author xulingfeng
 * @className BillingController
 * @description
 * @date 2023/2
 */
@Api("计费相关接口")
@RestController
@RequestMapping("/billing")
public class BillingController {

    @Autowired
    private BillingRecordService billingRecordService;

    /**
     * 开始计费
     * @param startBillingPayload
     * @return
     */
    @ApiOperation("开始计费")
    public ResponseDTO<BillingResponseDTO> startBilling(@Valid @RequestBody StartBillingPayload startBillingPayload) {
        BillingResponseDTO billingResponseDTO = billingRecordService.startBilling(startBillingPayload);
        // STD: 统一返回数据结构的封装 在用户接口层
        return new ResponseDTO(ResponseDTO.Status.SUCCESS, billingResponseDTO);
    }

    /**
     * 计费
     * @param billingPayload
     * @return
     */
    @ApiOperation("计费")
    @PostMapping(value = "/ongoing", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO<BillingResponseDTO> ongoningBilling(@Valid @RequestBody BillingPayload billingPayload) {
        BillingResponseDTO billingResponseDTO = billingRecordService.handleBilling(billingPayload);
        return new ResponseDTO(ResponseDTO.Status.SUCCESS, billingResponseDTO);
    }

    /**
     * 结束计费
     * @param endBillingPayload
     * @return
     */
    @ApiOperation("结束计费")
    @PostMapping(value = "/end", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO<BillingResponseDTO> endBilling(@Valid @RequestBody EndBillingPayload endBillingPayload) {
        BillingResponseDTO billingResponseDTO = billingRecordService.endBilling(endBillingPayload);
        return new ResponseDTO(ResponseDTO.Status.SUCCESS, billingResponseDTO);
    }



}
