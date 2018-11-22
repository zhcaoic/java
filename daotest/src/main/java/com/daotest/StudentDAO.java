package com.daotest;


import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface StudentDAO {

    public Student selectByName(@Param("studentName") String studentName);

    public List<Student> selectALL();

}
