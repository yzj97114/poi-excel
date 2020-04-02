package com.zzzsj.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 79282
 */
public interface StudentService {
//    void export(HttpServletResponse response);

    void importStudent(MultipartFile file);
}
