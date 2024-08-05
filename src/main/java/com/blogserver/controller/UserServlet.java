package com.blogserver.controller;

import com.blogserver.entity.FollowRequestBody;
import com.blogserver.entity.User;
import com.blogserver.service.UserService;
import com.blogserver.util.JsonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private static final Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss").create();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if(action.equals("getFollowerListByUserAccount")) {
            String userAccount = request.getParameter("userAccount");
            List<User> userList = userService.getFollowerListByUserAccount(userAccount);

            response.setContentType("application/json");
            try (PrintWriter out = response.getWriter()) {
                out.print(gson.toJson(userList));
                out.flush();
            }
        }

        if(action.equals("getAttentionListByFollowerAccount")) {
            String followerAccount = request.getParameter("followerAccount");
            List<User> userList = userService.getAttentionListByFollowerAccount(followerAccount);

            response.setContentType("application/json");
            try (PrintWriter out = response.getWriter()) {
                out.print(gson.toJson(userList));
                out.flush();
            }
        }

        if(action.equals("getCountByUserAccountAndFollowerAccount")) {
            String userAccount = request.getParameter("userAccount");
            String followerAccount = request.getParameter("followerAccount");

            Integer count = userService.getCountByUserAccountAndFollowerAccount(userAccount, followerAccount);

            String result = count.toString();
            response.setContentType("text/plain");
            try (PrintWriter out = response.getWriter()) {
                out.print(gson.toJson(result));
                out.flush();
            }
        }

        if(action.equals("getFollowerCountByUserAccount")) {
            String userAccount = request.getParameter("userAccount");
            Integer count = userService.getFollowerCountByUserAccount(userAccount);

            String result = count.toString();
            response.setContentType("text/plain");
            try (PrintWriter out = response.getWriter()) {
                out.print(gson.toJson(result));
                out.flush();
            }
        }

        if(action.equals("getAttentionCountByFollowerAccount")) {
            String followerAccount = request.getParameter("followerAccount");
            Integer count = userService.getAttentionCountByFollowerAccount(followerAccount);

            String result = count.toString();
            response.setContentType("text/plain");
            try (PrintWriter out = response.getWriter()) {
                out.print(gson.toJson(result));
                out.flush();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FollowRequestBody followRequestBody = JsonUtil.convertJsonToFollowRequestBody(request);

        System.out.println("followPost" + followRequestBody);

        userService.addFollow(followRequestBody);
    }
}
