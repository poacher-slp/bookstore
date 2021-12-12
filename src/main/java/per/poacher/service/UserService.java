package per.poacher.service;

import per.poacher.pojo.User;

/**
 * @author poacher
 * @create 2021-04-29-19:52
 */
public interface UserService {

    /**
     * 注册用户
     * @param user 待注册的用户
     */
    int regist(User user);

    /**
     * 登录
     * @param user 登录的用户
     * @return 返回null表示登录失败，否则登录成功
     */
    User login(User user);

    /**
     * 检查注册界面的用户名是否已存在
     * @param username 用户名
     * @return 返回true表示用户名已存在，返回false用户名可注册
     */
    boolean checkName(String username);
}
