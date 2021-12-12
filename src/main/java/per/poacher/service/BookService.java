package per.poacher.service;

import per.poacher.pojo.Book;

import java.util.List;

/**
 * @author poacher
 * @create 2021-05-02-16:38
 */
public interface BookService {

    /**
     * 实现添加图书信息
     * @param book 待添加的图书信息
     */
    int addBook(Book book);

    /**
     * 实现删除图书信息
     * @param id 待删除的图书id
     */
    int deleteBook(int id);

    /**
     * 实现修改图书信息
     * @param book 待修改的图书信息
     */
    int updateBook(Book book);

    /**
     * 实现查询一条图书信息
     * @param id 待查询的图书id值
     * @return 返回图书信息
     */
    Book queryOne(int id);

    /**
     * 实现查询多条图书信息
     * @return 返回图书集合
     */
    List<Book> queryMore();

    /**
     * 根据价格区间进行查询图书信息
     * @param min 最低价格
     * @param max 最高价格
     * @return 返回价格区间内的所有图书信息
     */
    List<Book> queryBooksByPrice(int min, int max);
}
