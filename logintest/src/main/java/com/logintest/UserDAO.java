package com.logintest;

import org.apache.ibatis.annotations.Param;

public interface UserDAO {

    User selectByUsername(@Param("username") String username);

    void insertNewUser(User newUser);

    User selectByUserId(@Param("userId") int userId);

    void updateUserInfo(User updatedUser);

}
