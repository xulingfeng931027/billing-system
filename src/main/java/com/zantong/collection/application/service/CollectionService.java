package com.zantong.collection.application.service;

import com.zantong.collection.application.assembly.CollectionRecordAssembler;
import com.zantong.collection.domain.collectionInfoAggregation.valueobject.BusinessContract;
import com.zantong.collection.domain.collectionInfoAggregation.valueobject.CustomerContract;
import com.zantong.collection.domain.collectionRecordAggregation.entity.CollectionInfo;
import com.zantong.collection.domain.repository.CollectionRepository;
import com.zantong.collection.domain.support.ContractSupport;
import com.zantong.contract.application.dto.CollectionInfoDto;
import com.zantong.contract.domain.support.AccountInfoSupport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author xulingfeng
 * @description 签约服务
 * @date 2023/2
 */
@Service
public class CollectionService {


    private static final String ACCOUNT_RULE_CODE = "XXX";
    @Autowired
    private AccountInfoSupport accountInfoSupport;
    @Autowired
    private ContractSupport contractSupport;
    @Autowired
    private CollectionRepository collectionRecordRepository;

    /**
     * 单笔代收
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public CollectionInfoDto singleCollection(CollectionInfoDto collectionInfoDto) {
        CollectionInfo collectionInfo = CollectionRecordAssembler.INSTANCE.toEntity(collectionInfoDto);
        BusinessContract businessContract = contractSupport.queryBusinessContract(collectionInfoDto.getProjectCode());
        if (businessContract == null) {

        }
        businessContract.checkStatus();
        //校验账户信息
        accountInfoSupport.checkAccountInfo(businessContract.getAccountInfoId(), ACCOUNT_RULE_CODE);
        accountInfoSupport.checkAccountInfo(businessContract.getTempAccountInfoId(), ACCOUNT_RULE_CODE);
        CustomerContract customerContract = contractSupport.queryCustomerContract(collectionInfoDto.getProjectCode(), collectionInfoDto.getUserCode());
        collectionInfo.setCustomerAccountInfoId(customerContract.getAccountInfoId());
        if (customerContract == null) {

        }
        accountInfoSupport.checkAccountInfo(customerContract.getAccountInfoId(), ACCOUNT_RULE_CODE);
        String receiveAccountId;
        if (StringUtils.isNoneBlank(businessContract.getTempAccountInfoId())) {
            receiveAccountId = businessContract.getTempAccountInfoId();
        } else {
            receiveAccountId = businessContract.getAccountInfoId();
        }
        collectionInfo.setBusinessAccountInfoId(receiveAccountId);

        Map<String, Object> collectionResult = accountInfoSupport.executeCollection(customerContract.getAccountInfoId(), receiveAccountId, collectionInfoDto.getAmount());
        CollectionRecordAssembler.INSTANCE.toEntity(businessContract, customerContract, collectionInfoDto.getAmount(), collectionResult);
        return null;
    }
}
