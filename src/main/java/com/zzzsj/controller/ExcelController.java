package com.zzzsj.controller;

import com.zzzsj.common.utils.ExcelUtil;
import com.zzzsj.entity.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RequestMapping(value = "/excel")
@RestController
public class ExcelController {
    @GetMapping(value = "/test")
    public void export( HttpServletResponse response) {

        Test test = new Test();
        test.setId(1L);
        test.setNum(1.1);
        test.setTime(new Date());
        test.setType("1");
        List<Test> list = new ArrayList<>();
        list.add(test);
        System.out.println(ExcelUtil.exportExcel("测试", list,Test.class , response));
    }
}
