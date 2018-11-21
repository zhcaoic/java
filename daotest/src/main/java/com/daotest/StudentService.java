package com.daotest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired private StudentDAO studentDAO;

    public List<Student> getStudents() {
        return studentDAO.getStudents();
    }

    public Student getStudentByName(String studentName) {
        return studentDAO.getStudentByName(studentName);
    }


}
