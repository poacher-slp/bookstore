package per.poacher.dao;

import org.junit.Test;
import per.poacher.dao.impl.UserDaoImpl;
import per.poacher.pojo.User;
import per.poacher.utils.JdbcUtils;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * @author poacher
 * @create 2021-04-29-19:21
 */
public class UserDaoTest {
    UserDaoImpl userDao = new UserDaoImpl();
    @Test
    public void registName() {

        Connection conn = JdbcUtils.getConnection();
        if (userDao.registName(conn, "admin") != null) {
            System.out.println("用户名已存在");
        }
        else {
            System.out.println("用户名可用");
        }
        JdbcUtils.close(conn,null, null);
    }

    @Test
    public void login() {
        Connection conn = JdbcUtils.getConnection();
        if (userDao.login(conn, "admin", "admin") != null) {
            System.out.println("登录成功");
        }
        else {
            System.out.println("用户名或密码错误，请重新输入");
        }

    }

    @Test
    public void regist() {
        Connection conn = JdbcUtils.getConnection();
        System.out.println(userDao.regist(conn, new User(0, "admin", "admin", "admin@poacher.com")));
    }
}