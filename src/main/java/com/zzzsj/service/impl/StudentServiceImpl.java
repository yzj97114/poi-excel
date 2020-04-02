package com.zzzsj.service.impl;


import com.zzzsj.common.utils.ExcelUtil;
import com.zzzsj.entity.Student;
import com.zzzsj.mapper.StudentMapper;
import com.zzzsj.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
/**
 * @author 79282
 */
@Service
public class StudentServiceImpl implements StudentService {

//    @Autowired
//    private StudentMapper studentMapper;
//    @Override
//    public void export(HttpServletResponse response) {
//        List<Student> studentList = studentMapper.selectAllStudent();
//        ExcelUtil.exportExcel("学生数据", studentList, Student.class, response);
//    }




    @Override
    public void importStudent(MultipartFile file) {

        List<Student> students = ExcelUtil.importExcel(file, Student.class);
        System.out.println(students);
    }
}
