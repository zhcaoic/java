package com.tiandog.Service;

import com.tiandog.DAO.UserBasicInfoDAO;
import com.tiandog.Entity.UserBasicInfo;
import com.tiandog.Util.ParameterEmptyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserInfoService {

    @Resource private UserBasicInfoDAO userBasicInfoDAO;


    //==================================================================
    //查询个人信息
    public UserBasicInfo getUserInfoByUserId(long userId) {
        UserBasicInfo dbUserBasicInfo = userBasicInfoDAO.selectUserInfoByUserId(userId);

        if (dbUserBasicInfo == null) {
            return null;
        } else {
            return dbUserBasicInfo;
        }
    }


    //==================================================================
    //创建个人信息
    public void createUserInfo(long userId, String realName, String email, String phone,
                               String ageTemp, String address, String occupation) throws Exception{
        UserBasicInfo tempUserBasicInfo = new UserBasicInfo();
        tempUserBasicInfo.setUserId(userId);
        tempUserBasicInfo.setRealName(realName);
        tempUserBasicInfo.setEmail(email);
        tempUserBasicInfo.setPhone(phone);

        //对int类型属性进行空字符串替换为0
        int age;
        if (ageTemp == "") {
            age = 0;
        } else {
            age = Integer.parseInt(ageTemp);
        }
        tempUserBasicInfo.setAge(age);

        tempUserBasicInfo.setAddress(address);
        tempUserBasicInfo.setOccupation(occupation);
        tempUserBasicInfo.setUpdateTime(new Date());

        //对String类型属性进行空字符串替换为null
        UserBasicInfo tempUserBasicInfoAfterDeal = ParameterEmptyUtil.parameterEmptyDeal(tempUserBasicInfo);

        userBasicInfoDAO.insertUserInfo(tempUserBasicInfoAfterDeal);
    }

}
