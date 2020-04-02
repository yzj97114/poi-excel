package com.zzzsj.controller;

import com.zzzsj.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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
}
