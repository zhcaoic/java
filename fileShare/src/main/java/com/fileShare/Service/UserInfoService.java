package com.fileShare.Service;


import com.fileShare.DAO.UserBasicInfoDAO;
import com.fileShare.Entity.UserBasicInfo;
import com.fileShare.Util.ParameterEmptyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;

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


    //==================================================================
    //更改个人信息
    public void updateUserInfo(long userId, String realName, String email, String phone,
                               String ageTemp, String address, String occupation) throws Exception{

        //取当前数据库中的用户信息
        UserBasicInfo dbUserBasicInfoForUpdate = getUserInfoByUserId(userId);
        if (dbUserBasicInfoForUpdate == null) {
            return;
        }

        //遍历更新所有属性值, 年龄除外
        HashMap<String, String> parameterMap = new HashMap<>();
        parameterMap.put("realName", realName);
        parameterMap.put("email", email);
        parameterMap.put("phone", phone);
        parameterMap.put("address", address);
        parameterMap.put("occupation", occupation);

        Field[] fields = dbUserBasicInfoForUpdate.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            for (String key : parameterMap.keySet()) {
                String value = parameterMap.get(key);
                if (f.getName() == key) {
                    //在数据库中不为null且传入参数值为null时不更新，其余情况更新值
                    if (f.get(dbUserBasicInfoForUpdate) == null || value != "") {
                        f.set(dbUserBasicInfoForUpdate, value);
                    }
                }
            }
        }

        //对int类型属性进行空字符串替换为0
        int age;
        if (ageTemp == "") {
            age = 0;
        } else {
            age = Integer.parseInt(ageTemp);
        }
        //单独处理更新年龄
        if (dbUserBasicInfoForUpdate.getAge() == 0 || age != 0) {
            dbUserBasicInfoForUpdate.setAge(age);
        }

        //对String类型属性进行空字符串替换为null
        UserBasicInfo userBasicInfoForUpdate = ParameterEmptyUtil.parameterEmptyDeal(dbUserBasicInfoForUpdate);

        //更新时间调整
        userBasicInfoForUpdate.setUpdateTime(new Date());

        userBasicInfoDAO.updateUserInfo(userBasicInfoForUpdate);

    }

}
