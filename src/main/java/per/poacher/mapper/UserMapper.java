package per.poacher.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import per.poacher.pojo.User;

/**
 * @author poacher
 * @create 2021-04-29-18:56
 */
@Repository
public interface UserMapper {
    /**
     * 根据注册界面的username查询用户名是否可用
     * @param username 待查询的用户名
     * @return 如果返回null表示用户名可用，否则表示用户名已经注册
     */
    @Select("select * from t_user where username = #{username}")
    User registName(String username);

    /**
     * 验证登录界面的用户名和密码
     * @param user 登录界面的用户信息
     * @return 返回null表示用户名错误或者密码错误，否则表示登录成功
     */
    @Select("select * from t_user where username = #{username} and password = #{password}")
    User login(User user);

    /**
     * 注册界面的用户名和密码
     * @param user 注册用户的信息
     * @return 返回1表示注册成功，否则表示失败
     */
    @Insert("insert into t_user(`username`, `password`, `email`) values(#{username},#{password},#{email})")
    int regist(User user);
}
