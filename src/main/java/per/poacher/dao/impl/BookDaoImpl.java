package per.poacher.dao.impl;


import per.poacher.dao.BaseDao;
import per.poacher.dao.BookDao;
import per.poacher.pojo.Book;

import java.sql.Connection;
import java.util.List;

/**
 * @author poacher
 * @create 2021-05-02-15:28
 */
public class BookDaoImpl extends BaseDao<Book> implements BookDao {
    @Override
    public int addBook(Connection conn, Book book) {
        String sql = "insert into t_book(name,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        int update = update(conn, sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
        return update;
    }

    @Override
    public int deleteBook(Connection conn, int id) {
        String sql = "delete from t_book where id = ?";
        int update = update(conn, sql, id);
        return update;
    }

    @Override
    public int updateBook(Connection conn, Book book) {
        String sql = "update t_book set name = ? ,  author = ? , price = ? , sales = ? , stock = ? ,  img_path = ? where id = ?";
        int update = update(conn, sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
        return update;
    }

    @Override
    public Book queryOne(Connection conn, int id) {
        String sql = "select id, name, author, price, sales, stock, img_path imgPath from t_book where id = ?";
        Book book = queryOne(conn, sql, id);
        return book;
    }

    @Override
    public List<Book> queryMore(Connection conn) {
        String sql = "select id, name, author, price, sales, stock, img_path imgPath from t_book";
        List<Book> books = queryMore(conn, sql);
        return books;
    }

    @Override
    public int queryForPageTotalCount(Connection conn) {
        String sql = "select count(*) from t_book";
        Number value = (Number) queryValue(conn, sql);
        return value.intValue();
    }

    @Override
    public List<Book> queryForPageItems(Connection conn, int begin, int pageSize) {
        String sql = "select id, name, author, price,  sales, stock, img_path imgPath from t_book  limit ? , ?";
        List<Book> books = queryMore(conn, sql, begin, pageSize);
        return books;
    }

    @Override
    public int queryForPageTotalCountByPrice(Connection conn, int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number number = (Number) queryValue(conn, sql, min, max);
        return number.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(Connection conn, int min, int max, int begin, int pageSize) {
        String sql = "select id, name, author, price, sales, stock, img_path imgPath from t_book " +
                "where price between ? and ? order by price limit ?, ?";
        List<Book> books = queryMore(conn, sql, min, max, begin, pageSize);
        return books;
    }
}
