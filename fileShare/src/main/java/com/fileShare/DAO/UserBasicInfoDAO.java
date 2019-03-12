package com.fileShare.DAO;


import com.fileShare.Entity.UserBasicInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBasicInfoDAO {

    UserBasicInfo selectUserInfoByUserId(long userId);

    void insertUserInfo(UserBasicInfo userBasicInfo);

    void updateUserInfo(UserBasicInfo userBasicInfo);

}
