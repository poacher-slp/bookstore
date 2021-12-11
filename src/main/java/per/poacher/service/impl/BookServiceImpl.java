package per.poacher.service.impl;

import per.poacher.dao.BookDao;
import per.poacher.dao.impl.BookDaoImpl;
import per.poacher.pojo.Book;
import per.poacher.pojo.Page;
import per.poacher.service.BookService;

import java.sql.Connection;
import java.util.List;

/**
 * @author poacher
 * @create 2021-05-02-16:41
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();
    @Override
    public void addBook(Connection conn, Book book) {
        bookDao.addBook(conn, book);
    }

    @Override
    public void deleteBook(Connection conn, int id) {
        bookDao.deleteBook(conn, id);
    }

    @Override
    public void updateBook(Connection conn, Book book) {
        bookDao.updateBook(conn, book);
    }

    @Override
    public Book queryOne(Connection conn, int id) {
        Book book = bookDao.queryOne(conn, id);
        return book;
    }

    @Override
    public List<Book> queryMore(Connection conn) {
        List<Book> books = bookDao.queryMore(conn);
        return books;
    }

    @Override
    public Page<Book> page(Connection conn, int pageNo, int pageSize) {

        Page<Book> bookPage = new Page<>();
        bookPage.setPageSize(pageSize); //设置页面显示的数量
        int pageTotalCount = bookDao.queryForPageTotalCount(conn);  //求总记录数
        bookPage.setPageTotalCount(pageTotalCount); //设置总记录数
        //求总页码
        int pageToatal = pageTotalCount % pageSize != 0
                ? pageTotalCount / pageSize + 1 : pageTotalCount / pageSize;
        bookPage.setPageTotal(pageToatal);
        bookPage.setPageNo(pageNo);     //设置页码
        int begin = (bookPage.getPageNo() - 1) * pageSize;  //求当前页数据开始的索引
        List<Book> items = bookDao.queryForPageItems(conn, begin, pageSize);
        bookPage.setItems(items);

        return bookPage;
    }

    @Override
    public Page<Book> pageByPrice(Connection conn, int pageNo, int pageSize, int min, int max) {
        Page<Book> bookPage = new Page<>();
        bookPage.setPageSize(pageSize); //设置页面显示的数量
        int pageTotalCount = bookDao.queryForPageTotalCountByPrice(conn, min, max);  //求总记录数
        bookPage.setPageTotalCount(pageTotalCount); //设置总记录数
        //求总页码
        int pageToatal = pageTotalCount % pageSize != 0
                ? pageTotalCount / pageSize + 1 : pageTotalCount / pageSize;
        bookPage.setPageTotal(pageToatal);
        bookPage.setPageNo(pageNo);     //设置页码
        int begin = (bookPage.getPageNo() - 1) * pageSize;  //求当前页数据开始的索引
        List<Book> items = bookDao.queryForPageItemsByPrice(conn,min, max, begin, pageSize);
        bookPage.setItems(items);
        return bookPage;
    }
}
