package com.zzzsj.entity;


import com.zzzsj.common.annotation.Excel;
import com.zzzsj.common.enums.DecimalType;
import com.zzzsj.common.enums.TimeType;
import lombok.Data;

import java.util.Date;

@Data
public class Test {


    @Excel(orderNum = 0, titleName = "主键", empty = true)
    private Long id;
    @Excel(orderNum = 1, titleName = "类型", empty = true)
    private String type;
    @Excel(orderNum = 2, titleName = "时间",
            type = @Excel.CellType(
                    timeType = TimeType.DATE_FORMAT
            )
    )
    private Date time;
    @Excel(orderNum = 3, titleName = "数字",
            type = @Excel.CellType(
                    decimalType = DecimalType.THREE
            )
    )
    private double num;
}
