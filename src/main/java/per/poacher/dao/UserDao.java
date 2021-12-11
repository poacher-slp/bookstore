package per.poacher.dao;

import per.poacher.pojo.User;

import java.sql.Connection;

/**
 * @author poacher
 * @create 2021-04-29-18:56
 */
public interface UserDao {
    /**
     * 根据注册界面的username查询用户名是否可用
     * @param username
     * @return 如果返回null表示用户名可用，否则表示用户名已经注册
     */
    User registName(Connection conn, String username);

    /**
     * 验证登录界面的用户名和密码
     * @param conn 数据库的连接
     * @param username 用户名
     * @param password 密码
     * @return 返回null表示用户名错误或者密码错误，否则表示登录成功
     */
    User login(Connection conn, String username, String password);

    /**
     * 注册界面的用户名和密码
     * @param user
     * @return 返回-1表示注册失败，否则注册成功
     */
    int regist(Connection conn, User user);
}
