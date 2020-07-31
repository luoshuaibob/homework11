package com.sunshine.servlet;

import com.sun.tools.internal.ws.processor.model.Request;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
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
@WebServlet(name = "RegisterUsersServlet", value = "/register")
public class RegisterUsersServlet extends javax.servlet.http.HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterUsersServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String phone =request.getParameter("phone");
        if(!name.matches("/^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[~!@#$%^&*])[\\da-zA-Z~!@#$%^&*]{8,}$/")
        || phone.length()!=6){
            System.out.println("不规范！");
            request.setAttribute("msg","格式不规范！");
            response.sendRedirect("index.jsp");
        }else{
            Users user = new Users();
            user.setName(name);
            user.setPassword(request.getParameter("password"));
            user.setPhone(phone);
            user.setStatus(1);
            try {
                SqlSession sqlSession = GetSqlSession.getSqlSession();
                sqlSession.insert("users.sql.insertUsers", user);
                System.out.println("新增成功！新增id为：" + user.getId());
            } catch (Exception e){
                GetSqlSession.rollback();
                LOGGER.error("insert error", e);
            } finally {
                GetSqlSession.commit();
            }
        }
    }
}