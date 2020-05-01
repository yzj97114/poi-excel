package com.zzzsj;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.io.IOException;

/**
 * @author yyyzj
 * @create 2020/5/2 01:05
 */
public class ExcelTest {
    public static void main(String[] args) throws Exception{
        //获得Excel文件
        Workbook wb = Workbook.getWorkbook(new File("/Users/yyyzj/Desktop/居居佳/student.xls"));
        WritableWorkbook book = Workbook.createWorkbook(new File("/Users/yyyzj/Desktop/居居佳/student.xls"), wb);
        WritableSheet sheet = book.getSheet(0);
        sheet.addCell(new Label(5, 1, "手机号有误"));
        book.write();
        book.close();
    }
}
