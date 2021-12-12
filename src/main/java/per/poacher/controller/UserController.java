package per.poacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import per.poacher.pojo.User;
import per.poacher.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author poacher
 * @create 2021-05-02-13:34
 */
@Controller
public class UserController{

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 注册功能
     * @param user 带注册的用户信息
     * @param code 验证码
     * @param session session
     * @return 返回注册后结果的视图
     */
    @RequestMapping("/regist")
    public ModelAndView regist(User user, String code, HttpSession session) {
        String token = (String) session.getAttribute(KAPTCHA_SESSION_KEY);
        session.removeAttribute(KAPTCHA_SESSION_KEY);
        ModelAndView mav = new ModelAndView();
        System.out.println(token);
        //检查验证码是否正确
        if (token != null && token.equalsIgnoreCase(code)) {
            System.out.println("验证码正确");
            //验证码正确，检查用户名是否可用
            if (userService.checkName(user.getUsername())) {
                System.out.println("用户名不可用");
                //不可用，转发回到注册界面
                mav.addObject("msg", "用户名已存在！");
                mav.addObject("username",user.getUsername());
                mav.addObject("email",user.getEmail());
                mav.setViewName("regist");
                return mav;
            }
            System.out.println("用户名可用");
            //可用则注册，并转发到注册成功页面
            userService.regist(user);
            mav.setViewName("regist_success");
            return mav;
        }
        System.out.println("验证码错误");
        //不正确，显示错误信息，并回显部分信息
        mav.addObject("msg", "验证码错误！");
        mav.addObject("username",user.getUsername());
        mav.addObject("email",user.getEmail());
        //转发到注册界面
        mav.setViewName("regist");
        return mav;
    }

    /**
     * 登录功能
     * @param user 带登录的用户信息
     * @param session session
     * @return 返回登录后的结果页面
     */
    @RequestMapping("/login")
    public ModelAndView login(User user, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        User login = userService.login(user);
        if (login == null) {
            //登录失败，重新回到登录界面
            mav.addObject("msg", "用户名或密码输入错误");
            mav.addObject("username",user.getUsername());
            mav.addObject("password",user.getPassword());
            mav.setViewName("login");
            return mav;
        }
        //登录成功，转发到登录成功界面
        session.setAttribute("user", login);
        mav.setViewName("login_success");
        return mav;
    }

    /**
     * 注销功能
     * @param session session
     * @return 返回注销后的页面
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        //重定向到登录页面
        return "redirect:login";
    }

    /**
     * 利用ajax进行注册时的用户名检验
     * @param username 待注册的username
     * @throws ServletException
     * @throws IOException
     * @return
     */
    @ResponseBody
    @RequestMapping("ajaxExistUsername")
    public Map<String, Object> ajaxExistUsername(String username) {
        boolean existUsername = userService.checkName(username);
        Map<String, Object> map = new HashMap<>();
        map.put("existUsername", existUsername);
        return map;
    }
}
