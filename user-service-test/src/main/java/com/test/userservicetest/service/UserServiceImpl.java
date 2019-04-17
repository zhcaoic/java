package com.test.userservicetest.service;

import com.test.userservicetest.domain.DAO.UserDAO;
import com.test.userservicetest.domain.entity.UserBase;
import com.test.userservicetest.domain.util.MD5;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService{

    @Resource UserDAO userDAO;

    /**
     * 登录服务
     * @param name 登录名，可以是用户编号、昵称、邮箱、手机号
     * @param pwd 明文密码
     * @return 查询结果
     */
    @Override
    public UserBase loginService(String name, String pwd) {
        // FIXME 暂时只支持用户名登录
        // 验证数据库中该用户是否存在
        UserBase userBaseDB = userDAO.selectUserByNickname(name);
        if (userBaseDB == null) {
            return null;
        }

        // 验证是否在黑名单中
        if (userBaseDB.getLoginPermission() == 1) {
            return null;
        }

        // 验证密码
        if (userBaseDB.getPassword() == null || userBaseDB.getPassword().isEmpty()) {
            return null;
        }
        String password = MD5.pwdTransform(pwd);
        if (!password.equals(userBaseDB.getPassword())) {
            return null;
        }

        // 更改登录时间
        userBaseDB.setUserLoginTime(new Date());
        userDAO.updateUserLoginTime(userBaseDB);

        return userBaseDB;
    }



    /**
     * 注册服务
     * @param nickname 昵称
     * @param pwd 明文密码（未加密）
     * @param email 邮箱
     * @param cellphoneTemp 手机号码（未转型）
     * @return 0--注册成功；1--昵称已被占用；2--邮箱已被占用；3--手机号已被占用
     */
    @Override
    public int registerService(String nickname, String pwd, String email, String cellphoneTemp) {
        // 检查昵称是否被占用
        UserBase userBaseTemp = userDAO.selectUserByNickname(nickname);
        if (userBaseTemp != null) {
            return 1;
        }
        // 检查邮箱是否被占用
        userBaseTemp = userDAO.selectUserByEmail(email);
        if (userBaseTemp != null) {
            return 2;
        }
        // 检查手机号码是否被占用
        // String -> long 类型转换
        //FIXME 存在风险，需要先检查手机号码是否为11位纯数字，否则可能会抛出转换失败的异常！
        long cellphone = Long.parseLong(cellphoneTemp);
        userBaseTemp = userDAO.selectUserByCellphone(cellphone);
        if (userBaseTemp != null) {
            return 3;
        }
        // 若全部通过验证，则可正式开始注册服务
        // MD5加密处理
        String password = MD5.pwdTransform(pwd);
        // 生成用户编号
        // 生成规则：年份 + 本年度第 ？ 个注册序号，序号长为5。如 201900001。（可用Redis计数）
        //FIXME Redis实现计数
        // 暂时方案
        int userNumber = 201900001;
        userBaseTemp = userDAO.selectUserByUserNumber(userNumber);
        while (userBaseTemp != null) {
            userNumber ++;
            userBaseTemp = userDAO.selectUserByUserNumber(userNumber);
        }
        // 赋值
        UserBase userBaseDB = new UserBase();
        userBaseDB.setUserNumber(userNumber);
        userBaseDB.setEmail(email);
        userBaseDB.setCellphone(cellphone);
        userBaseDB.setPassword(password);
        userBaseDB.setRealPassword(pwd);
        userBaseDB.setNickname(nickname);
        userBaseDB.setLoginPermission(0);
        userBaseDB.setUserCreateTime(new Date());
        userBaseDB.setUserUpdateTime(new Date());

        // 插入新用户
        userDAO.insertUser(userBaseDB);

        return 0;
    }






















}
