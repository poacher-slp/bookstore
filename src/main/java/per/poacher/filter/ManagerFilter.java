package per.poacher.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author poacher
 * @create 2021-05-04-14:12
 */
public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Object user = httpServletRequest.getSession().getAttribute("user");
        if (user != null) {
            chain.doFilter(request, response);
            return;
        }
        httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
    }

    @Override
    public void destroy() {

    }
}
