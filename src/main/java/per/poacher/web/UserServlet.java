package per.poacher.web;

import com.google.gson.Gson;
import per.poacher.pojo.User;
import per.poacher.service.UserService;
import per.poacher.service.impl.UserServiceImpl;
import per.poacher.utils.JdbcUtils;
import per.poacher.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author poacher
 * @create 2021-05-02-13:34
 */
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /**
     * 注册功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String code = req.getParameter("code");
        String email = req.getParameter("email");
        User user = WebUtils.paramToBean(req.getParameterMap(), new User());
        Connection conn = JdbcUtils.getConnection();
        try {
            conn.setAutoCommit(false);
            //检查验证码是否正确 先要求验证码为asdf
            if (token != null && token.equalsIgnoreCase(code)) {
                //正确 检查用户名是否可用
                if (userService.checkName(conn,username)) {
                    //不可用 跳回注册页面
                    req.setAttribute("msg", "用户名已存在！");
                    req.setAttribute("username",username);
                    req.setAttribute("email",email);
                    req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
                }
                else {
                    //可用 调用service保存到数据库
                    userService.regist(conn,user);
                    conn.commit();
                    conn.setAutoCommit(true);
                    //跳到注册成功页面
                    req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
                }
            }
            else {//不正确
                //显示错误信息并回显部分信息
                req.setAttribute("msg", "验证码错误！");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                //跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.close(conn, null, null);
        }
    }

    /**
     * 登录功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = WebUtils.paramToBean(req.getParameterMap(), new User());
        //调用service层的方法
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            User login = userService.login(conn, user);
            //根据login()方法返回结果判断登录是否成功
            if (login != null) {
                //成功，请求重定向
                conn.commit();
                conn.setAutoCommit(true);
                req.getSession().setAttribute("user",login);
                req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
                return;
            }
            //失败，请求重定向
            req.setAttribute("msg", "用户名或密码输入错误");
            req.setAttribute("username",username);
            req.setAttribute("password",password);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(conn, null, null);
        }

    }

    /**
     * 注销功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());

    }

    /**
     * 利用ajax进行注册时的用户名检验
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        try {
            String username = req.getParameter("username");
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            boolean existUsername = userService.checkName(conn, username);
            HashMap<String, Object> map = new HashMap<>();
            map.put("existUsername", existUsername);
            Gson gson = new Gson();
            String json = gson.toJson(map);
            conn.commit();
            conn.setAutoCommit(true);
            resp.getWriter().write(json);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            JdbcUtils.close(conn, null, null);
        }

    }
}
