package per.poacher.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author poacher
 * @create 2021-04-29-15:34
 */
public class JdbcUtils {

    private static DataSource dataSource = null;

    static {
        //读取配置文件
        try {
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //创建properties对象
            Properties ps = new Properties();
            //从流中加载数据
            ps.load(is);
            //创建数据库连接
            dataSource = DruidDataSourceFactory.createDataSource(ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取数据库的连接
     * @return 如果返回null表示获取连接失败，有值就是获取成功
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    /**
     * 考虑事务的关闭连接
     * @param conn 数据库的连接
     * @param ps 预编译语句
     * @param rs 数据库结果集
     */
    public static void close(Connection conn, Statement ps, ResultSet rs) {
        DbUtils.closeQuietly(conn);
        DbUtils.closeQuietly(ps);
        DbUtils.closeQuietly(rs);
    }
}
