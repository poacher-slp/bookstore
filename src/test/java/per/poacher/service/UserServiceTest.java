package per.poacher.service;

import org.junit.Test;
import per.poacher.pojo.User;
import per.poacher.service.impl.UserServiceImpl;
import per.poacher.utils.JdbcUtils;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * @author poacher
 * @create 2021-04-29-20:16
 */
public class UserServiceTest {

    private UserService userService = new UserServiceImpl();
    @Test
    public void regist() {
        Connection conn = JdbcUtils.getConnection();
        userService.regist(conn, new User(0, "poacher","poacher", "poacher@qq.com"));
        JdbcUtils.close(conn,null, null);
    }

    @Test
    public void login() {
        Connection conn = JdbcUtils.getConnection();
        System.out.println(userService.login(conn, new User(0, "admin", "admin", "admin@poacher.com")));
        JdbcUtils.close(conn, null, null);
    }

    @Test
    public void checkName() {
        Connection conn = JdbcUtils.getConnection();
        if (userService.checkName(conn,"admin")) {
            System.out.println("用户已存在");
        }
        else {
            System.out.println("用户名可用");
        }
        JdbcUtils.close(conn, null, null);
    }
}