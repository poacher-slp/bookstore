package per.poacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import per.poacher.pojo.Book;
import per.poacher.pojo.Cart;
import per.poacher.pojo.CartItem;
import per.poacher.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author poacher
 * @create 2021-05-04-9:08
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private BookService bookService;

    public void addItems(int id, HttpSession session, HttpRequest req) {
        Book book = bookService.queryOne(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        session.setAttribute("lastItem",cartItem.getName());
        //获取Referer并重定向到Referer的值
        HttpHeaders referer = req.getHeaders();
    }

    /**
     * 删除购物车中的某项商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void deleteItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = req.getParameter("id") != null
                ? Integer.parseInt(req.getParameter("id")) : 0;
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.deleteItem(id);
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 清空购物车中的所有商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void clearItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clear();
        resp.sendRedirect(req.getHeader("Referer"));
    }

}
