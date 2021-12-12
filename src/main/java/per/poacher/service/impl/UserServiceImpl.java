package per.poacher.service.impl;

import org.springframework.stereotype.Service;
import per.poacher.mapper.UserMapper;
import per.poacher.pojo.User;
import per.poacher.service.UserService;

/**
 * @author poacher
 * @create 2021-04-29-19:58
 */
@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int regist(User user) {
        return userMapper.regist(user);
    }

    @Override
    public User login(User user) {
        User login = userMapper.login(user);
        return login;
    }

    @Override
    public boolean checkName(String username) {
        User user = userMapper.registName(username);
        if (user == null) {
            return false;
        }
        return true;
    }

}
