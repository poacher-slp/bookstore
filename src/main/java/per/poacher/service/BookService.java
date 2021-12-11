package per.poacher.service;

import per.poacher.pojo.Book;
import per.poacher.pojo.Page;

import java.sql.Connection;
import java.util.List;

/**
 * @author poacher
 * @create 2021-05-02-16:38
 */
public interface BookService {

    /**
     * 实现添加图书信息
     * @param conn
     * @param book
     */
    void addBook(Connection conn, Book book);

    /**
     * 实现删除图书信息
     * @param conn
     * @param id
     */
    void deleteBook(Connection conn, int id);

    /**
     * 实现修改图书信息
     * @param conn
     * @param book
     */
    void updateBook(Connection conn, Book book);

    /**
     * 实现查询一条图书信息
     * @param conn
     * @param id
     * @return
     */
    Book queryOne(Connection conn, int id);

    /**
     * 实现查询多条图书信息
     * @param conn
     * @return
     */
    List<Book> queryMore(Connection conn);

    Page<Book> page(Connection conn, int pageNo, int pageSize);

    Page<Book> pageByPrice(Connection conn, int pageNo, int pageSize, int min, int max);
}
