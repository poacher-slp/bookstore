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
 * @create 2021-05-03-15:47
 */
@Controller
@RequestMapping("/client")
public class ClientBookController {


    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * 首页的分页显示
     * @param pageNum 当前页，默认是1
     * @return 返回分页后的结果
     */
    @RequestMapping("/page")
    public ModelAndView page(
            @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum,4);
        List<Book> books = bookService.queryMore();
        PageInfo<Book> page = new PageInfo<>(books, 5);
        ModelAndView mav = new ModelAndView();
        mav.addObject("page", page);
        mav.addObject("url","client/page?1=1");
        mav.setViewName("client_index");
        return mav;
    }

    /**
     * 根据价格区间处理分页
     * @param pageNum 默认1
     * @param min 默认0
     * @param max 默认Integer.MAX_VALUE
     */
    @RequestMapping("/pageByPrice")
    public ModelAndView pageByPrice(
            @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
            @RequestParam(defaultValue = "0",value = "min")Integer min,
            @RequestParam(defaultValue = Integer.MAX_VALUE+"",value = "max") Integer max) {
        ModelAndView mav = new ModelAndView();
        PageHelper.startPage(pageNum,4);
        List<Book> books = bookService.queryBooksByPrice(min, max);
        PageInfo<Book> page = new PageInfo<>(books, 5);
        mav.addObject("url","client/pageByPrice?min=" + min + "&max=" + max );
        mav.addObject("page",page);
        mav.setViewName("client_index");
        return mav;
    }

}
