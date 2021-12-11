package per.poacher.web;

import per.poacher.pojo.Book;
import per.poacher.pojo.Page;
import per.poacher.service.BookService;
import per.poacher.service.impl.BookServiceImpl;
import per.poacher.utils.JdbcUtils;
import per.poacher.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author poacher
 * @create 2021-05-02-16:49
 */
public class BookServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();

    /**
     * 增加图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        try {
            int pageNo = req.getParameter("pageNo") != null
                    ? Integer.parseInt(req.getParameter("pageNo")) + 1 : 0;

            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            //获取请求的参数，并封装到类中
            Book book = WebUtils.paramToBean(req.getParameterMap(), new Book());
            //调用service方法，进而保存到数据库
            bookService.addBook(conn, book);
            conn.commit();
            conn.setAutoCommit(true);
            //跳转到/manager/bookServlet?action=list页面
            resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
//            req.getRequestDispatcher().forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(conn, null, null);
        }
    }

    /**
     * 删除图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            String id = req.getParameter("id");
            int i = Integer.parseInt(id);
            bookService.deleteBook(conn, i);
            resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(conn, null, null);
        }
    }

    /**
     * 根据图书id查询特定的图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        try {
            String id = req.getParameter("id");
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            Book book = bookService.queryOne(conn, Integer.parseInt(id));
            req.setAttribute("book",book);
            conn.commit();
            conn.setAutoCommit(true);
            req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(conn, null, null);
        }
    }

    /**
     * 修改图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebUtils.paramToBean(req.getParameterMap(), new Book());
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            bookService.updateBook(conn, book);
            conn.commit();
            conn.setAutoCommit(true);
            resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(conn, null , null);
        }
    }

    /**
     * 显示所有图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //通过BookService查询得到所有的图书信息
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            List<Book> books = bookService.queryMore(conn);
            conn.commit();
            conn.setAutoCommit(true);
            //把图书信息保存到Request域中
            req.setAttribute("books", books);
            //请求转发到/pages/manager/book_manager.jsp页面
            req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(conn, null, null);
        }


    }

    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        try {
            int pageNo = req.getParameter("pageNo") != null
                    ? Integer.parseInt(req.getParameter("pageNo")) : 1;
            int pageSize = req.getParameter("pageSize") != null
                    ? Integer.parseInt(req.getParameter("pageSize")) : Page.PAGE_SIZE;
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            Page<Book> page = bookService.page(conn, pageNo, pageSize);
            page.setUrl("manager/bookServlet?action=page");
            req.setAttribute("page", page);
            conn.commit();
            conn.setAutoCommit(true);
            req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(conn, null, null);
        }
    }
}
