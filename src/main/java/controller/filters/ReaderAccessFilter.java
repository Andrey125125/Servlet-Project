package controller.filters;

import model.entity.User;
import model.exeptions.PageNotAccessibleExeption;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/library/reader/*"})
public class ReaderAccessFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpSession session = ( (HttpServletRequest)servletRequest).getSession();

        User.Role role = getUserRole(session);

        if ( !role.equals(User.Role.READER)) throw new PageNotAccessibleExeption("reader page access denied");

        filterChain.doFilter(servletRequest, servletResponse);
    }


    private User.Role getUserRole(HttpSession session) {
        return session.getAttribute("user") != null ?
                ((User) session.getAttribute("user")).getRole() : User.Role.GUEST;
    }

    @Override
    public void destroy() {

    }
}
