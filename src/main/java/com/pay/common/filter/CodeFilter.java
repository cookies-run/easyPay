package com.pay.common.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by yu on 2018/7/2.
 */
public class CodeFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        chain.doFilter(request, response);
    }
    @Override
    public void destroy() {
    }
}
