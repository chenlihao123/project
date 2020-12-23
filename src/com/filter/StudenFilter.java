package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author chenlihao
 * @create 2020-12-23 23:20
 */
@WebFilter(urlPatterns = { "/studentHome/*","/studentHome/person.html" })
public class StudenFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse=(HttpServletResponse)resp;
        HttpSession session = httpServletRequest.getSession();
        Object admin = session.getAttribute("admin");
        Object teacher = session.getAttribute("teacher");
        Object student = session.getAttribute("student");
        // 如果等于 null，说明还没有登录
        if (admin == null&&teacher==null&&student==null) {
            httpServletResponse.sendRedirect("http://localhost:8080/onlinelearning/index.html");
            return;
        } else {
// 让程序继续往下访问用户的目标资源
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
