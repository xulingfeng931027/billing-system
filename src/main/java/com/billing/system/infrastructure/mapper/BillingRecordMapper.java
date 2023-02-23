package com.billing.system.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.billing.system.infrastructure.po.BillingRecordPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xulingfeng
 * @className BillingRecordMapper
 * @description
 * @date 2023/2
 */
@Mapper
public interface BillingRecordMapper extends BaseMapper<BillingRecordPO> {

}
