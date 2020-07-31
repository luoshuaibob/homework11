package com.sunshine.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter implements Filter {


    @Override

    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println("过滤器初始化");

    }



    @Override

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)

            throws IOException, ServletException {

        System.out.println("过滤器过滤到请求");
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        /*

            执行完过滤的功能代码之后，一定要将请求放行！！！

            如果哪一天，访问页面死活出不来，在排除了500及404的错误后，百分之99.9的情况就是过滤器中没有放行请求

         */
        if(request.getParameter("status")==null){
            System.out.println("过滤掉！");
            httpServletResponse.sendRedirect("index.jsp");
        }else{
            chain.doFilter(request, response);
        }


    }



    @Override

    public void destroy() {

        System.out.println("过滤器销毁");

    }

}
