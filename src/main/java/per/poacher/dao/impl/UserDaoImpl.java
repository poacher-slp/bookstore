package per.poacher.dao.impl;

import per.poacher.dao.BaseDao;
import per.poacher.dao.UserDao;
import per.poacher.pojo.User;

import java.sql.Connection;

/**
 * @author poacher
 * @create 2021-04-29-19:03
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {

    public User registName(Connection conn, String username) {
        String sql = "select id, username, password, email from t_user where username = ?";
        User user = queryOne(conn, sql, username);
        return user;
    }

    public User login(Connection conn, String username, String password) {
        String sql = "select id, username, password, email from t_user where username = ? and password = ?";
        User user = queryOne(conn, sql, username, password);
        return user;
    }


    public int regist(Connection conn, User user) {
        String sql = "insert into t_user(username, password, email) values(?,?,?)";
        int update = update(conn, sql, user.getUsername(), user.getPassword(), user.getEmail());
        return update;
    }
}
