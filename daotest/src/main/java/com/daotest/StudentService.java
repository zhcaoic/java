package com.daotest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentService {


    @Resource
    StudentDAO studentDAO;

    public Student getStudentByName(String studentName) {

        return studentDAO.selectByName(studentName);
    }

    public List<Student> getStudents() {

        return studentDAO.selectALL();
    }

}
