package com.zzzsj.mapper;

import com.zzzsj.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 79282
 */
@Mapper
public interface StudentMapper {
    List<Student> selectAllStudent();
}
