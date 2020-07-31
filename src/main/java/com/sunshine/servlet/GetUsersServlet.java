package com.sunshine.servlet;

import com.sunshine.bean.Users;
import com.sunshine.utils.GetSqlSession;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by
 */
@WebServlet(name = "GetUsersServlet", value = "/getUsersByName")
public class GetUsersServlet extends javax.servlet.http.HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetUsersServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users users = null;
        try {
            SqlSession sqlSession = GetSqlSession.getSqlSession();
            users = sqlSession.selectOne("users.sql.getUsersByName", request.getParameter("name"));
            if(users.getPassword().equals(request.getParameter("password"))){
                System.out.println("cg");
                request.setAttribute("status",1);
                response.sendRedirect("info.jsp");
            }else{
                System.out.println("密码与用户名不匹配，失败！");
                response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            LOGGER.error("select error", e);
            GetSqlSession.rollback();
        } finally {
            GetSqlSession.commit();
        }
        System.out.println(users.toString());
    }
}