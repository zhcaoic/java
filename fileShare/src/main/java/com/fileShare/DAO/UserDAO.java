package com.fileShare.DAO;

import com.fileShare.Entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

    void insertNewUser(User newUser);

    User selectByName(String name);

    void updateLoginTime(User tempUser);

}
