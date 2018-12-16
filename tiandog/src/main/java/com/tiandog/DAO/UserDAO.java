package com.tiandog.DAO;

import com.tiandog.Entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

    void insertNewUser(User newUser);

    User selectByName(String name);

    void updateLoginTime(User tempUser);
}
