package com.test.userservicetest.controller;

import com.test.userservicetest.domain.entity.SessionUser;
import com.test.userservicetest.domain.entity.UserBase;
import com.test.userservicetest.domain.util.StringRedisUtil;
import com.test.userservicetest.domain.util.SessionUtil;
import com.test.userservicetest.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserBaseController {

    // 首页访问计数器key名
    public static final String INDEX_COUNT = "indexCount";


    @Resource UserServiceImpl userServiceImpl;

    @Resource StringRedisUtil stringRedisUtil;



    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String userIndex(ModelMap modelMap) {


        // 计数首页访问次数
        if (stringRedisUtil.getExpire(INDEX_COUNT) != -1) {
            //FIXME key有过期时间！异常处理，日志记录
            System.out.println("------------> key有过期时间！");
        }
        stringRedisUtil.incrByOne(INDEX_COUNT);
        String indexCount = stringRedisUtil.get(INDEX_COUNT);

        modelMap.addAttribute("indexCount", indexCount);

        return "views/index";
    }



    /**
     * 登录控制器
     * @param name 用户名
     * @param pwd 密码
     * @param preUrl 跳转登录前网址
     * @param request 网页请求
     * @param modelMap 返回数据模型
     * @return 视图名称
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String name,
                        @RequestParam("password") String pwd,
                        @RequestParam("preUrl") String preUrl,
                        HttpServletRequest request,
                        ModelMap modelMap) {
        // 验证参数是否为空
        if (name == null || name.isEmpty() || pwd == null || pwd.isEmpty()) {
            modelMap.addAttribute("paramIsEmpty",1);
            return "views/login";
        }
        // 校验用户合法性
        UserBase userBaseDB = userServiceImpl.loginService(name, pwd);
        if (userBaseDB == null) {
            modelMap.addAttribute("loginFail",1);
            return "views/login";
        }
        // 用户合法，设置Session
        SessionUser sessionUser = new SessionUser();
        sessionUser.setId(userBaseDB.getUserId());
        sessionUser.setUserNumber(userBaseDB.getUserNumber());
        sessionUser.setNickname(userBaseDB.getNickname());
        sessionUser.setLoginPermission(userBaseDB.getLoginPermission());
        boolean result = SessionUtil.setLoginUser(request, sessionUser);
        if (result == false) {
            return "views/error";
        }

        // 按preUrl(登录前页面URL)分别重定向
        if (preUrl == null || preUrl.isEmpty()) {
            // preUrl为null或""空字符串，重定向至首页
            return "redirect:/user/index";
        } else if (!preUrl.substring(0, 22).equals("http://localhost:8080/")) {
            // preUrl为其他站点URL，重定向至首页
            return "redirect:/user/index";
        } else {
            // preUrl为本站点URL，重定向至preUrl
            return "redirect:" + preUrl;
        }

    }



    /**
     * 登出控制器
     * @param request 网页请求
     * @return 视图名称
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        boolean result = SessionUtil.removeSession(request);
        if (result == false) {
            return "views/error";
        }
        return "redirect:/user/index";
    }



    /**
     * 注册控制器
     * @param nickname 昵称
     * @param pwd 密码
     * @param pwdConfirm 验证密码
     * @param email 邮箱
     * @param cellphoneTemp 手机号
     * @param modelMap 返回数据模型
     * @return 视图名称
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam("nickname") String nickname,
                           @RequestParam("password") String pwd,
                           @RequestParam("pwdConfirm") String pwdConfirm,
                           @RequestParam("email") String email,
                           @RequestParam("cellphone") String cellphoneTemp,
                           ModelMap modelMap) {
        // 检查输入是否为空
        if (nickname == null || nickname.isEmpty() || pwd == null || pwd.isEmpty() || pwdConfirm == null || pwdConfirm.isEmpty()
                || email == null || email.isEmpty() || cellphoneTemp == null || cellphoneTemp.isEmpty()) {
            modelMap.addAttribute("inputIsNull", 1);
            return "views/register";
        }
        // 检查密码是否一致
        if (!pwd.equals(pwdConfirm)) {
            modelMap.addAttribute("pwdNotEqual", 1);
            return "views/register";
        }
        // 调用注册服务
        int registerResult = userServiceImpl.registerService(nickname, pwd, email, cellphoneTemp);
        if (registerResult == 1) {
            modelMap.addAttribute("nicknameIsExisted", 1);
            return "views/register";
        } else if (registerResult == 2) {
            modelMap.addAttribute("emailIsExisted", 1);
            return "views/register";
        } else if (registerResult == 3) {
            modelMap.addAttribute("cellphoneIsExisted", 1);
            return "views/register";
        } else {
            return "redirect:/user/index";
        }

    }

}
