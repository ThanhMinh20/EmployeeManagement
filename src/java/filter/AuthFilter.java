/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author minht
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // khong can xu ly gi o day
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        // cac trang duoc phep vao ma khong can login
        boolean isLoginRequest = uri.endsWith("login.jsp")
                || uri.endsWith("login")
                || uri.endsWith("checkLogin")
                || uri.contains("css")
                || uri.contains("js");

        // lay session hien tai
        HttpSession session = req.getSession(false);
        boolean loggedIn = (session != null && session.getAttribute("LOGIN_USER") != null);

        // neu da login hoac dang vao trang login -> cho di tiep
        if (loggedIn || isLoginRequest) {
            chain.doFilter(request, response);
        } else {
            // chua login -> quay ve login page
            res.sendRedirect("login.jsp");
        }
    }

    @Override
    public void destroy() {
        // khong can xu ly
    }
}