package com.tiandog.Controller;

import com.tiandog.Entity.CookiesUser;
import com.tiandog.Entity.UserBasicInfo;
import com.tiandog.Service.UserInfoService;
import com.tiandog.Service.UserService;
import com.tiandog.Util.CookiesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class RegisterController {

    //==================================================================
    @Resource private UserService userService;
    @Resource private UserInfoService userInfoService;


    //==================================================================
    //注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam("username") String name,
                           @RequestParam("password") String password,
                           @RequestParam("pwdConfirm") String pwdConfirm) {
        if (!password.equals(pwdConfirm)) {
            return "error/registerError";
        } else {
            userService.registerService(name, password);

            return "views/loginAfterRegister";
        }

    }


    //==================================================================
    //查询个人信息
    @RequestMapping(value = "/userInfoDeal")
    public String getUserInfo(HttpServletRequest request,
                           ModelMap modelMap) throws Exception{

        //从Cookies中获取当前登录用户的ID
        CookiesUser cookiesUser = CookiesUtil.getLoginUser(request);
        long cookieUserId = cookiesUser.getId();
        UserBasicInfo userBasicInfo = userInfoService.getUserInfoByUserId(cookieUserId);

        if (userBasicInfo == null) {
            return "views/createUserInfo";
        } else {
            modelMap.addAttribute("userBasicInfo", userBasicInfo);

            //遍历UserBasicInfo对象，检查是否有属性值为null
            //存放值为null的属性名称
            ArrayList<String> arrayValueIsNull = new ArrayList<>();
            //反射获取成员变量
            Field[] fields = userBasicInfo.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i ++) {
                Field f = fields[i];
                //允许访问私有变量
                f.setAccessible(true);
                if (f.get(userBasicInfo) == null) {
                    arrayValueIsNull.add(f.getName());
                }
            }

            if (arrayValueIsNull.isEmpty()) {
                return "views/userInfo";
            } else {
                modelMap.addAttribute("arrayValueIsNull", arrayValueIsNull);

                return "views/userInfo";
            }
        }
    }


    //==================================================================
    //创建个人信息
    @RequestMapping(value = "/createUserInfoDeal", method = RequestMethod.POST)
    public String createUserInfo(@RequestParam("realName") String realName,
                                 @RequestParam("email") String email,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("age") String ageTemp,
                                 @RequestParam("address") String address,
                                 @RequestParam("occupation") String occupation,
                                 HttpServletRequest request) throws Exception{

        //从Cookies中获取当前登录用户的ID
        CookiesUser cookiesUser = CookiesUtil.getLoginUser(request);
        long cookieUserId = cookiesUser.getId();

        userInfoService.createUserInfo(cookieUserId, realName, email, phone, ageTemp, address, occupation);

        return "views/index";
    }


    //==================================================================
    //更新个人信息
    @RequestMapping(value = "/updateUserInfoDeal")
    public String updateUserInfo(@RequestParam("realName") String realName,
                                 @RequestParam("email") String email,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("age") String ageTemp,
                                 @RequestParam("address") String address,
                                 @RequestParam("occupation") String occupation,
                                 HttpServletRequest request) throws Exception{

        //从Cookies中获取当前登录用户的ID
        CookiesUser cookiesUser = CookiesUtil.getLoginUser(request);
        long cookieUserId = cookiesUser.getId();

        userInfoService.updateUserInfo(cookieUserId, realName, email, phone, ageTemp, address, occupation);

        return "views/index";
    }


}
