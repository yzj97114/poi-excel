package com.zzzsj;

import com.zzzsj.common.utils.ExcelUtil;
import com.zzzsj.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {

        String strUrl = "/Users/yyyzj/Desktop/student.xls";
        File file = new File(strUrl);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);
            List<Student> students = ExcelUtil.importExcel(multipartFile, Student.class);
            System.out.println(students);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}
