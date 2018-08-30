package com.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(value = {"/someServlet", "/xxx", "/jjj"}, //熟悉urlPatterns与属性value功能相同，只能使用一个，不能同时使用
            name = "SomeServlet",  //设置servlet名字
            initParams = {@WebInitParam(name = "name", value = "sjh"), //设置初始化参数
                          @WebInitParam(name = "age", value = "23")},
            loadOnStartup = 2

)
public class SomeServlet extends HttpServlet {

    public SomeServlet(){
        System.out.println("SomeServlet被创建");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.append("Served at Servlet3.0<br>");

        //获取ServletName
        String servletName = this.getServletName();
        out.append("servletName = " + servletName + "<br>");

        //获取初始化参数
        String name = this.getInitParameter("name");
        String age = this.getInitParameter("age");
        out.append("name = " + name + "     age = " + age + "<br>");

        //枚举获取初始化参数
        Enumeration<String> names = this.getInitParameterNames();
        while (names.hasMoreElements()){
            String ename = names.nextElement();
            String evalue = this.getInitParameter(ename);
            out.append("ename = " + evalue + " ");
        }

    }
}
