package com.test.userservicetest.domain.DAO;

import com.test.userservicetest.domain.entity.UserBase;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDAO {

    //==================================================
    //==================  查   =========================
    //==================================================

    // ====== 单个用户查询 ======
    // 按用户ID查找用户
    UserBase selectUserByUserId(int userId);

    // 按用户编号查找用户
    UserBase selectUserByUserNumber(int userNumber);

    // 按用户昵称查找用户
    UserBase selectUserByNickname(String nickname);

    // 按用户邮箱查找用户
    UserBase selectUserByEmail(String email);

    // 按用户手机查找用户
    UserBase selectUserByCellphone(long cellphone);



    //==================================================
    //==================  增   =========================
    //==================================================

    // ====== 单个用户新增 ======
    void insertUser(UserBase userBase);



    //==================================================
    //==================  删   =========================
    //==================================================

    // ====== 单个用户删除 ======
    // 按用户ID删除用户
    void deleteUserByUserId(int userId);



    //==================================================
    //==================  改   =========================
    //==================================================

    // ====== 单个用户修改 ======
    void updateUser(UserBase userBase);




}
