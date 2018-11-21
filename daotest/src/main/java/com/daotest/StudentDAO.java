package com.daotest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAO {

    @Autowired private SqlSessionTemplate template;

    private static final String MAPPER_NAMESPACE = "com.daotest.StudentMapper";

    public Student getStudentByName(String studentName) {
        System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
        Student student = template.selectOne(MAPPER_NAMESPACE + ".selectByName", studentName);
        System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
        return student;
    }

    public List<Student> getStudents() {
        return template.selectList(MAPPER_NAMESPACE + ".selectALL");
    }

}
