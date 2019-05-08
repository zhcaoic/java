package com.test.dubbocommon.service;

import com.test.dubbocommon.domain.User;

public interface UserService {

    User getUserById(int id);

    boolean setNewUser(User user);

}
