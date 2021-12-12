package per.poacher.service.impl;

import org.springframework.stereotype.Service;
import per.poacher.mapper.BookMapper;
import per.poacher.pojo.Book;
import per.poacher.service.BookService;

import java.util.List;

/**
 * @author poacher
 * @create 2021-05-02-16:41
 */
@Service
public class BookServiceImpl implements BookService {

    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public int addBook(Book book) {
        return bookMapper.addBook(book);
    }

    @Override
    public int deleteBook(int id) {
        return bookMapper.deleteBook(id);
    }

    @Override
    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    @Override
    public Book queryOne(int id) {
        return bookMapper.queryOne(id);
    }

    @Override
    public List<Book> queryMore() {
        return bookMapper.queryMore();
    }

    @Override
    public List<Book> queryBooksByPrice(int min, int max) {
        return bookMapper.queryBooksByPrice(min, max);
    }

}
