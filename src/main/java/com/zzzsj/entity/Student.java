package com.zzzsj.entity;

import com.zzzsj.common.annotation.Excel;
import lombok.Data;

/**
 * @author 79282
 */
@Data
public class Student {
    @Excel(orderNum = 0, titleName = "id", empty = true)
    private String id;
    @Excel(orderNum = 1, titleName = "姓名", empty = true)
    private String name;
    @Excel(orderNum = 2, titleName="性别", empty = true)
    private String gender;
    @Excel(orderNum = 3, titleName = "地址", empty = true)
    private String address;
    @Excel(orderNum = 4, titleName = "手机号码", empty = true)
    private String phone;

}
