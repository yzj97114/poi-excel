package com.zzzsj.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.zzzsj.common.annotation.NumberAnnotation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
public class DemoData {
    @ExcelProperty("字符串标题")
    private String string;
    @ExcelProperty("日期标题")
    private Date date;
    @ExcelProperty("数字标题")
    @NumberAnnotation
    private BigDecimal bigDecimalData;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;
}
