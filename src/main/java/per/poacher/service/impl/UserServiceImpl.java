package per.poacher.service.impl;

import per.poacher.dao.UserDao;
import per.poacher.dao.impl.UserDaoImpl;
import per.poacher.pojo.User;
import per.poacher.service.UserService;
import per.poacher.utils.JdbcUtils;

import java.sql.Connection;

/**
 * @author poacher
 * @create 2021-04-29-19:58
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    public void regist(Connection conn, User user) {
        userDao.regist(conn, user);
    }

    public User login(Connection conn, User user) {
        User login = userDao.login(conn, user.getUsername(), user.getPassword());
        return login;
    }

    public boolean checkName(Connection conn, String username) {
        if (userDao.registName(conn, username) != null) {
            return true;
        }
        return false;
    }

}
