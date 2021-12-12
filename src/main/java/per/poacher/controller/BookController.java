package per.poacher.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import per.poacher.pojo.Book;
import per.poacher.service.BookService;

import java.util.List;

/**
 * @author poacher
 * @create 2021-05-02-16:49
 */
@Controller
@RequestMapping("/manager")
public class BookController{

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * 添加图书
     * @param book 待添加的图书信息
     * @param pageNum 最后一页的页码
     * @return 返回最后一页
     */
    @RequestMapping("/add")
    public String add(Book book, int pageNum) {
        bookService.addBook(book);
        PageHelper.startPage(pageNum,4);
        List<Book> books = bookService.queryMore();
        PageInfo<Book> page = new PageInfo<Book>(books);
        pageNum = page.getPages();
        //重定向到最后一页
        return "redirect:/manager/page?pageNum=" + pageNum;
    }

    /**
     * 根据id删除图书信息
     * @param id 待删除的图书id
     * @param pageNum 待删除图书的当前页
     * @return 返回当前页
     */
    @RequestMapping("/delete")
    public String delete(int id, int pageNum) {
        bookService.deleteBook(id);
        //重定向到当前页
        return "redirect:/manager/page?pageNum=" + pageNum;
    }

    /**
     * 修改图书信息
     * @param book 待修改的图书信息
     * @param pageNum 待修改图书的页码
     * @return 返回到当前页
     */
    @RequestMapping("/update")
    public String update(Book book, int pageNum) {
        bookService.updateBook(book);
        return "redirect:/manager/page?pageNum=" + pageNum;
    }

    /**
     * 根据id查询图书
     * @param id 待删除的图书id
     * @return 转发到图书信息页面
     */
    @RequestMapping("/getBook")
    public ModelAndView getBook(int id) {
        ModelAndView mav = new ModelAndView();
        Book book = bookService.queryOne(id);
        mav.addObject("book", book);
        mav.setViewName("book_edit");
        return mav;
    }

    /**
     * 分页查询所有图书
     * @param pageNum 当前页，默认是1
     * @return 转发到图书管理页
     */
    @RequestMapping("/page")
    public ModelAndView listBook(
            @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        ModelAndView mav = new ModelAndView();
        PageHelper.startPage(pageNum,4);
        List<Book> books = bookService.queryMore();
        PageInfo<Book> page = new PageInfo<Book>(books,5);
        mav.addObject("url","manager/page?1=1");
        mav.addObject("page", page);
        mav.setViewName("book_manager");
        return mav;
    }
}
