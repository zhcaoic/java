package com.daotest;



import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {

    public Student selectByName(String studentName);

    public List<Student> selectALL();

}
