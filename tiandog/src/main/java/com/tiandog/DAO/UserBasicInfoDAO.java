package com.tiandog.DAO;

import com.tiandog.Entity.UserBasicInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBasicInfoDAO {

    UserBasicInfo selectUserInfoByUserId(long userId);

    void insertUserInfo(UserBasicInfo userBasicInfo);

}
