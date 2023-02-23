package com.billing.system.domain.repository;

import com.billing.system.domain.entity.BillingRecord;

/**
 * @author xulingfeng
 * @className BillingRecordRepository
 * @description 计费记录仓储接口
 * @date 2023/2
 */
public interface BillingRecordRepository {
    /**
     * 保存（新建）计费记录
     * @param billingRecord
     */
    BillingRecord saveBillingRecord(BillingRecord billingRecord);

    /**
     * 根据记录ID获取记录详情
     * @param recordIdObject
     * @return
     */
    BillingRecord getByRecordId(Long recordIdObject);

    /**
     * 更新计费记录
     * @param billingRecord
     */
    void updateBillingRecord(BillingRecord billingRecord);
}
