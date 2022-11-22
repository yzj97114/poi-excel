package com.zzzsj;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.zzzsj.common.utils.ExcelUtil;
import com.zzzsj.entity.DemoData;
import org.apache.commons.collections4.ListUtils;
import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yyyzj
 * @create 2022/11/22 下午2:44
 */
public class simpleWrite {

    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setBigDecimalData(new BigDecimal("0.567"));
            list.add(data);
        }
        formatNumber(list);

        return list;
    }


    private  <T> void formatNumber(List<T> list) {
        for (T t : list) {
            try {
                ExcelUtil.testFieldAnnotation(t.getClass(),t);
            } catch (Exception e) {
                //log.error
                //throw exception
                System.out.println(e);
            }
        }
    }
    /**
     * 最简单的写
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>
     * 2. 直接写即可
     */
    @Test
    public void simpleWrite() {

        String fileName =  "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());

    }
}
