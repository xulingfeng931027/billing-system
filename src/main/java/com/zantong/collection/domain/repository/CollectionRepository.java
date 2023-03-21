package com.zantong.collection.domain.repository;

import com.zantong.collection.domain.collectionInfoAggregation.entity.CollectionInfo;

import java.util.Map;

/**
 * @author xulingfeng
 * @className BillingRecordRepository
 * @description 计费记录仓储接口
 * @date 2023/2
 */
public interface CollectionRepository {

    /**
     * 保存商户合约
     *
     * @return
     */
    Integer saveRecord(CollectionInfo collectionInfo, Map<String, Object> result);

}
