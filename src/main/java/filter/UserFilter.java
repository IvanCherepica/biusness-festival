package filter;

import models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/user/*"})
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();

        User user = (User) session.getAttribute("user");

        if (user != null && user.getRole().equals("user")) {
            filterChain.doFilter(request, response);
            return;
        }

        ((HttpServletResponse) response).sendRedirect("/error.html");
    }

    @Override
    public void destroy() {

    }
}