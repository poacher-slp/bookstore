package per.poacher.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author poacher
 * @create 2021-04-29-18:21
 */
public abstract class BaseDao<T> {

    private Class<T> clazz = null;

    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        clazz = (Class<T>) actualTypeArguments[0];
    }

    QueryRunner queryRunner = new QueryRunner();

    /**
     * update()方法用来执行对数据库的insert、update、delete操作
     * @param conn 数据库的连接
     * @param sql 待执行的sql语句
     * @param args 占位符
     * @return 返回-1表示执行失败，否则表示sql语句成功执行对数据库影响的行数
     */
    public int update(Connection conn, String sql, Object... args) {
        int update = -1;
        try {
            update = queryRunner.update(conn, sql, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return update;
    }

    /**
     * 查询单条记录
     * @param conn 数据库连接
     * @param sql 待执行的sql语句
     * @param args 占位符
     * @return 返回null表示没有该对象，否则有数据库由记录
     */
    public T queryOne(Connection conn, String sql, Object... args) {
        BeanHandler<T> handler = new BeanHandler<T>(clazz);
        T query = null;
        try {
            query = queryRunner.query(conn, sql, handler, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return query;
    }

    /**
     * sql语句查询多条对象
     * @param conn 数据库连接
     * @param sql 待执行的sql语句
     * @param args sql语句中的占位符
     * @return 如果返回null表示没有记录，否则有记录
     */
    public List<T> queryMore(Connection conn, String sql, Object... args) {
        BeanListHandler<T> handler = new BeanListHandler<T>(clazz);
        List<T> query = null;
        try {
            query = queryRunner.query(conn, sql, handler, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return query;
    }

    /**
     * 查询特殊值，例如最大的年龄，count(*)这种查询
     * @param conn 数据库的连接
     * @param sql 待执行的sql语句
     * @param args sql语句中的占位符
     * @param <E> 特殊值的类型
     * @return 返回null表示查询失败，否则有值
     */
    public <E> E queryValue(Connection conn, String sql, Object... args) {
        ScalarHandler<Object> handler = new ScalarHandler();
        E query = null;
        try {
            query = (E) queryRunner.query(conn, sql, handler, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return query;
    }
}
