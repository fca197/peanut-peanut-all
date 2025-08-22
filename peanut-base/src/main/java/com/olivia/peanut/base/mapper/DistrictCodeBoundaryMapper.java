package com.olivia.peanut.base.mapper;
import com.github.yulichang.base.MPJBaseMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.olivia.peanut.base.model.DistrictCodeBoundary;
import org.apache.ibatis.annotations.Mapper;

/**
 * 地区边界(DistrictCodeBoundary)表数据库访问层
 *
 * @author admin
 * @since 2025-08-22 13:33:38
 */
@Mapper
public interface DistrictCodeBoundaryMapper extends MPJBaseMapper<DistrictCodeBoundary> {

}

