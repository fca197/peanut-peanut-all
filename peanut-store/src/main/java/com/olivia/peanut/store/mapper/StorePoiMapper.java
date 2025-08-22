package com.olivia.peanut.store.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.olivia.peanut.store.model.StorePoi;
import org.apache.ibatis.annotations.Mapper;

/**
 * store poi(StorePoi)表数据库访问层
 *
 * @author admin
 * @since 2025-08-22 16:10:27
 */
@Mapper
public interface StorePoiMapper extends MPJBaseMapper<StorePoi> {

}

