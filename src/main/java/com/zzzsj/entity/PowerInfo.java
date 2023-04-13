package com.zzzsj.entity;

import lombok.Data;

/**
 * @author yyyzj
 * @create 2023/4/12 下午2:01
 */
@Data
public class PowerInfo {
    private String date;
    private String totalPower;
    private String peakPower;
    private String highPower;
    private String lowPower;
    private String pricePower;
}
