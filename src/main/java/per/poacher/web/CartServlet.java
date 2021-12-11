package per.poacher.web;

import per.poacher.pojo.Book;
import per.poacher.pojo.Cart;
import per.poacher.pojo.CartItem;
import per.poacher.service.BookService;
import per.poacher.service.impl.BookServiceImpl;
import per.poacher.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author poacher
 * @create 2021-05-04-9:08
 */
public class CartServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();

    /**
     * 添加购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        try {
            int id = req.getParameter("id") != null
                    ? Integer.parseInt(req.getParameter("id")) : 0;
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            Book book = bookService.queryOne(conn, id);
            CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                req.getSession().setAttribute("cart",cart);
            }
            cart.addItem(cartItem);
            req.getSession().setAttribute("lastItem",cartItem.getName());
            conn.commit();
            conn.setAutoCommit(true);
            resp.sendRedirect(req.getHeader("Referer"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(conn, null, null);
        }


    }

    /**
     * 删除购物车中的某项商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    protected void clearItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clear();
        resp.sendRedirect(req.getHeader("Referer"));
    }

}
