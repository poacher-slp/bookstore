package per.poacher.dao;

import org.junit.Test;
import per.poacher.dao.impl.BookDaoImpl;
import per.poacher.pojo.Book;
import per.poacher.utils.JdbcUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @author poacher
 * @create 2021-05-02-15:57
 */
public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();
    private Connection conn = JdbcUtils.getConnection();

    @Test
    public void addBook() {
//        bookDao.addBook(conn, new Book(0, "你是谁", "poacher", new BigDecimal(65), 100, 100, null));
//        JdbcUtils.close(conn, null, null);
    }

    @Test
    public void deleteBook() {
        bookDao.deleteBook(conn, 20);
        JdbcUtils.close(conn, null, null);
    }

    @Test
    public void updateBook() {
//        bookDao.updateBook(conn, new Book(21, "你到底是谁", "poacher", new BigDecimal(65), 100, 100, null));
//        JdbcUtils.close(conn, null, null);
    }

    @Test
    public void queryOne() {
        Book book = bookDao.queryOne(conn, 1);
//        System.out.println(book);
        JdbcUtils.close(conn, null, null);
    }

    @Test
    public void queryMore() {
//        List<Book> books = bookDao.queryMore(conn);
//        for (Book book : books) {
//            System.out.println(book);
//        }
//        JdbcUtils.close(conn, null, null);
    }

    @Test
    public void queryForPageTotalCount() {
//        System.out.println(bookDao.queryForPageTotalCount(conn));
    }

    @Test
    public void queryForPageItems() {
//        for (Book queryForPageItem : bookDao.queryForPageItems(conn, 8, 4)) {
//            System.out.println(queryForPageItem);
//        }
    }

    @Test
    public void queryForPageTotalCountByPrice() {
//        System.out.println(bookDao.queryForPageTotalCountByPrice(conn, 10 ,100));
    }

    @Test
    public void queryForPageItemsByPrice() {
//        for (Book queryForPageItem : bookDao.queryForPageItemsByPrice(conn, 10, 100, 0, 4)) {
//            System.out.println(queryForPageItem);
//        }
    }
}