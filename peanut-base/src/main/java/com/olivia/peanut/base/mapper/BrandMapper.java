package com.olivia.peanut.base.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.olivia.peanut.base.model.Brand;
import org.apache.ibatis.annotations.Mapper;

/**
 * 品牌表(Brand)表数据库访问层
 *
 * @author admin
 * @since 2025-08-25 15:03:19
 */
@Mapper
public interface BrandMapper extends MPJBaseMapper<Brand> {

}

