package com.blogserver.controller;

import com.blogserver.entity.User;
import com.blogserver.entity.vo.LoginMessage;
import com.blogserver.service.LoginService;
import com.blogserver.util.JsonUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final LoginService loginService = new LoginService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = JsonUtil.convertJsonToUser(request);

        //判断登陆是否成功
        LoginMessage loginMessage = loginService.userLogin(user.getUserAccount(), user.getUserPassword());

        //返回登陆信息
        String json = JsonUtil.convertObjectToJson(loginMessage);

        //测试
        System.out.println("login" + json);

        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            out.print(json);
            out.flush();
        }

    }
}
