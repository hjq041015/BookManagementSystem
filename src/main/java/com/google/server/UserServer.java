package com.google.server;

import com.google.entity.Student;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserServer extends UserDetailsService {
    List<Student> getStudentList();
}
