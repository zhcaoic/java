package com.logintest;

import org.apache.ibatis.annotations.Param;

public interface UserDAO {

    User selectByUsername(@Param("username") String username);

}
