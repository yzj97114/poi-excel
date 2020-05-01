package com.zzzsj.controller;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author yyyzj
 * @create 2020/5/2 01:09
 */
@Controller
public class ExportExcelController {


    @GetMapping("/export")
    public void exportExcel(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws Exception {
        ServletOutputStream outputStream = response.getOutputStream();
        //拿到文件输入流
        InputStream inputStream = file.getInputStream();
        //获得只读的excel
        Workbook workbook = Workbook.getWorkbook(inputStream);

        String fileName = "zzzsj.xls";
        String filePath = "/Users/yyyzj/Desktop/";
        //创建文件 保存到本地
        File dest = new File(filePath + fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(dest);
        byte[] by = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(by)) != -1) {
            fileOutputStream.write(by, 0, len);
        }
        fileOutputStream.close();

        //根据副本文件创建可操作的excel
        WritableWorkbook writableWorkbook
                = Workbook.createWorkbook(dest, workbook);
        WritableSheet sheet = writableWorkbook.getSheet(0);
        //进行校验
        sheet.addCell(new Label(5, 1, "你是傻逼把 手机号都不输"));
        //校验结束  写入
        writableWorkbook.write();
        writableWorkbook.close();


        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName+".xls");
        byte[] buffer = new byte[1024];
        FileInputStream fileInputStream = new FileInputStream(dest);
        int len1 = 0;
        while ((len1 = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len1);
        }
        outputStream.close();

    }

}
