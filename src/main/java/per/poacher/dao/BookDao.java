package per.poacher.dao;


import per.poacher.pojo.Book;

import java.sql.Connection;
import java.util.List;

/**
 * @author poacher
 * @create 2021-05-02-15:22
 */
public interface BookDao {

    /**
     * 添加图书
     * @param conn 数据库连接
     * @param book 待加入的图书
     * @return 返回结果集的行数
     */
    int addBook(Connection conn, Book book);

    /**
     * 根据图书id进行删除图书
     * @param conn 数据库连接
     * @param id 待删除的图书id
     * @return 返回结果集的行数
     */
    int deleteBook(Connection conn, int id);

    /**
     * 根据图书的id修改图书信息
     * @param conn 数据库的连接
     * @param book 待修改的图书
     * @return 返回结果集影响的行数
     */
    int updateBook(Connection conn, Book book);

    /**
     * 查询一条图书信息
     * @param conn 数据库的丽娜姐
     * @param id 需要查询的图书的id
     * @return 返回图书信息
     */
    Book queryOne(Connection conn, int id);

    /**
     * 查询多条图书信息
     * @return 返回图书的集合
     */
    List<Book> queryMore(Connection conn);

    /**
     * 查询总记录数
     * @return 返回总记录数
     */
    int queryForPageTotalCount(Connection conn);

    /**
     * 获取分页显示的数据的信息
     * @param begin 开始索引
     * @param pageSize 分页
     * @return 返回图书总数
     */
    List<Book> queryForPageItems(Connection conn, int begin, int pageSize);

    /**
     * 根据价格区间查询图书的总数
     * @param conn 获取数据库连接
     * @param min 最低价格
     * @param max 最高价格
     * @return
     */
    int queryForPageTotalCountByPrice(Connection conn, int min, int max);

    /**
     * 根据价格区间获取图书的具体信息
     * @param conn 获取数据库连接
     * @param min 最低价格
     * @param max 最高价格
     * @param begin 分页的起始页
     * @param pageSize 分页的条数
     * @return 返回图书信息
     */
    List<Book> queryForPageItemsByPrice(Connection conn, int min, int max, int begin, int pageSize);
}
