package per.poacher.service;

import org.junit.Test;
import per.poacher.service.impl.BookServiceImpl;
import per.poacher.utils.JdbcUtils;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * @author poacher
 * @create 2021-05-03-10:01
 */
public class BookServiceTest {

    private BookService bookService = new BookServiceImpl();
    private Connection conn = JdbcUtils.getConnection();
    @Test
    public void page() {
//        System.out.println(bookService.page(conn, 2, 4));
        JdbcUtils.close(conn, null, null);
    }

    @Test
    public void pageByPrice() {
//        System.out.println(bookService.pageByPrice(conn, 1 ,4, 10,100));
        JdbcUtils.close(conn, null, null);
    }
}