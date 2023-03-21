package com.zantong.collection.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zantong.collection.infrastructure.po.BillingRecordPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xulingfeng
 * @className BillingRecordMapper
 * @description
 * @date 2023/2
 */
@Mapper
public interface CollectionMapper extends BaseMapper<BillingRecordPO> {

}
