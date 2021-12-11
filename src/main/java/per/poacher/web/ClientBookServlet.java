package per.poacher.web;

import per.poacher.pojo.Book;
import per.poacher.pojo.Page;
import per.poacher.service.BookService;
import per.poacher.service.impl.BookServiceImpl;
import per.poacher.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * @author poacher
 * @create 2021-05-03-15:47
 */
public class ClientBookServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();

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
            page.setUrl("client/bookServlet?action=page");
            req.setAttribute("page", page);
            conn.commit();
            conn.setAutoCommit(true);
            req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(conn, null, null);
        }
    }

    /**
     * 根据价格区间处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        try {
            int pageNo = req.getParameter("pageNo") != null
                    ? Integer.parseInt(req.getParameter("pageNo")) : 1;
            int pageSize = req.getParameter("pageSize") != null
                    ? Integer.parseInt(req.getParameter("pageSize")) : Page.PAGE_SIZE;
            int min = req.getParameter("min") != null
                    ? Integer.parseInt(req.getParameter("min")) : 0;
            int max = req.getParameter("max") != null
                    ? Integer.parseInt(req.getParameter("max")) : Integer.MAX_VALUE;

            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            Page<Book> page = bookService.pageByPrice(conn, pageNo, pageSize, min, max);

            StringBuilder stringBuilder = new StringBuilder("client/bookServlet?action=pageByPrice");
            if (req.getParameter("min") != null) {
                stringBuilder.append("&min=").append(req.getParameter("min"));
            }
            if (req.getParameter("max") != null) {
                stringBuilder.append("&max=").append(req.getParameter("max"));
            }
            page.setUrl(stringBuilder.toString());
            
            req.setAttribute("page", page);
            conn.commit();
            conn.setAutoCommit(true);
            req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(conn, null, null);
        }
    }

}
