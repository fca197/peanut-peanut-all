package com.olivia.peanut.base.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime; 
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter; 
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
/**
 * 地区边界(TDistrictCodeBoundary)表实体类
 *
 * @author admin
 * @since 2025-08-23 17:46:41
 */
@Accessors(chain=true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName(value = "t_district_code_boundary")
public class TDistrictCodeBoundary  extends  BaseEntity<TDistrictCodeBoundary> {

        /***
        *  区域编码
        */
     @TableField(value= "district_code")
     private String districtCode;
        /***
        *  区域名称
        */
     @TableField(value= "district_name")
     private String districtName;
        /***
        *  边界
        */
     @TableField(value= "polyline")
     private String polyline;
 
}

