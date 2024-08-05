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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final LoginService loginService = new LoginService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = JsonUtil.convertJsonToUser(request);

        //判断注册是否成功
        LoginMessage loginMessage = loginService.userRegister(
                user.getUserAccount(),
                user.getUserName(),
                user.getUserPassword()
        );

        //返回登陆信息
        String json = JsonUtil.convertObjectToJson(loginMessage);

        //测试
        System.out.println("regis" + json);

        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            out.print(json);
            out.flush();
        }

    }
}
