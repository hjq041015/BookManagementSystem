package com.google.Mappers;

import com.google.entity.Account;
import com.google.entity.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select * from user where username = #{username}")
    Account findUserByUsername(String username);

    @Select("select * from student")
    List<Student> getStudentList();
}
