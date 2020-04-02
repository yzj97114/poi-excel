package com.zzzsj.controller;

import com.zzzsj.common.utils.PdfUtils;
import com.zzzsj.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 79282
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

//    @RequestMapping("/export")
//    public void export(HttpServletResponse response) {
//        studentService.export(response);
//    }

    @RequestMapping("/import")
    public void importStudent(MultipartFile file) {
        studentService.importStudent(file);
    }

    @RequestMapping("/pdf")
    public void pdfDownload(HttpServletResponse response) {
        Map<String,String> map = new HashMap();
        map.put("name","张三");
        map.put("creatdate","今天");
        map.put("weather","晴");
        map.put("sports","踢球");
        map.put("ball","On");
        Map<String,String> map2 = new HashMap();
        map2.put("img","/Users/yyyzj/Pictures/376189334869.jpg");
        Map<String,Object> o=new HashMap();
        o.put("datemap",map);
        o.put("imgmap",map2);
        PdfUtils.pdfout(o,response);
    }

}
