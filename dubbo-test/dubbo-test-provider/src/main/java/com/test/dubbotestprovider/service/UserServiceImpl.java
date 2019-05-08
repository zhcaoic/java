package com.test.dubbotestprovider.service;


import com.test.dubbocommon.domain.User;
import com.test.dubbocommon.service.UserService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUserById(int id) {
        if (id == 1) {
            User user = new User();
            user.setId(1);
            user.setName("新津川带佐");
            user.setDetail("在中国留学五年的川桑。");
            return user;
        } else if (id == 2) {
            User user = new User();
            user.setId(2);
            user.setName("龟田志斌");
            user.setDetail("少年阿斌的高中成绩并不理想。");
            return user;
        } else if (id == 3) {
            User user = new User();
            user.setId(3);
            user.setName("侯国玉");
            user.setDetail("国家一级唢呐表演艺术家，代表作：《再见了妈妈，今晚我就要远航》。");
            return user;
        } else {
            return null;
        }
    }



    @Override
    public boolean setNewUser(User user) {
        if (user != null) {
            System.out.println("ID : " + user.getId());
            System.out.println("Name : " + user.getName());
            System.out.println("Detail : " + user.getDetail());
            return true;
        } else {
            System.out.println("User is null.");
            return false;
        }
    }

}
