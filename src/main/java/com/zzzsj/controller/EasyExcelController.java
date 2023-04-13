package com.zzzsj.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.zzzsj.entity.FillData;
import com.zzzsj.entity.PowerInfo;
import com.zzzsj.entity.Student;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yyyzj
 * @create 2021/1/7 13:56
 */
@RestController
public class EasyExcelController {

    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>
     * 1. 创建excel对应的实体对象 参
     * <p>
     * 2. 设置返回的 参数
     * <p>
     * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();

        // 背景设置为红色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short)20);
        headWriteCellStyle.setWriteFont(headWriteFont);
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 背景绿色
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short)20);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        EasyExcel.write(response.getOutputStream(), Student.class)
                .sheet("模板")
                .registerWriteHandler(horizontalCellStyleStrategy)
                .doWrite(data());
    }

    private List data() {

        Student student = new Student();
        List<Student> students = new ArrayList<>();
        student.setId("1");
        student.setAddress("11");
        student.setGender("男");
        student.setName("yyyzj");
        student.setPhone("1234567");
        students.add(student);
        student = new Student();
        student.setId("2");
        student.setAddress("22");
        student.setGender("女");
        student.setName("zzzsj");
        student.setPhone("12345678");
        students.add(student);
        return students;
    }

    @GetMapping("1234")
    public void exportExcel(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        String template = "/Users/yyyzj/Desktop/demo.xlsx";

        //工作薄对象
        ExcelWriter workBook = EasyExcel.write(response.getOutputStream()).withTemplate(template).build();

        //工作区对象
        WriteSheet sheet = EasyExcel.writerSheet().build();


        FillData fillData = new FillData();
        fillData.setId("1");
        fillData.setGetElectricityDataTime("20230402");
        fillData.setStartTime("20230402");
        fillData.setEndTime("20230403");
        fillData.setCompanyNmae("yyyyzj");
        fillData.setBindNumbers("1");
        fillData.setApplicationTime("12321");


        List<PowerInfo> powerInfos = new ArrayList<>();
        PowerInfo powerInfo = new PowerInfo();
        powerInfo.setTotalPower("123456789");
        powerInfo.setPricePower("2222222");
        powerInfo.setPeakPower("333333");
        powerInfo.setLowPower("44444");
        powerInfo.setHighPower("555555");
        powerInfo.setDate("20230413");
        powerInfos.add(powerInfo);

        powerInfo = new PowerInfo();
        powerInfo.setTotalPower("5");
        powerInfo.setPricePower("4");
        powerInfo.setPeakPower("3");
        powerInfo.setLowPower("2");
        powerInfo.setHighPower("1");
        powerInfo.setDate("20230414");
        powerInfos.add(powerInfo);

        powerInfo = new PowerInfo();
        powerInfo.setTotalPower("888888");//填统计的值
        powerInfo.setPricePower("888888");//填统计的值
        powerInfo.setPeakPower("888888");//填统计的值
        powerInfo.setLowPower("888888");//填统计的值
        powerInfo.setHighPower("888888");//填统计的值
        powerInfo.setDate("统计");
        powerInfos.add(powerInfo);



        //使用工作薄对象填充数据
        workBook.fill(fillData,sheet);
        workBook.fill(powerInfos,sheet);
        workBook.finish();

    }
}
