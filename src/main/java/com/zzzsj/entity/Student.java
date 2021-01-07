package com.zzzsj.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.zzzsj.common.annotation.Excel;
import lombok.Data;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

/**
 * @author 79282
 */
@Data

@ColumnWidth(25)
public class Student {
    @ExcelProperty("id")
    private String id;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("性别")
    private String gender;
    @ExcelProperty("地址")
    private String address;
    @ExcelProperty("手机号码")
    private String phone;

}
