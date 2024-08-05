package com.blogserver.controller;

import com.blogserver.entity.Comment;
import com.blogserver.entity.Like;
import com.blogserver.service.LikeService;
import com.blogserver.util.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/like")
public class LikeServlet extends HttpServlet {
    private final LikeService likeService = new LikeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String blogIdStr = request.getParameter("blogId");
        Integer blogId = Integer.parseInt(blogIdStr);

        List<Like> likeList = likeService.getLikeListByBlogId(blogId);

        String json = JsonUtil.convertLikeListToJson(likeList);

        //测试
        System.out.println("likeList get" + json);

        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            out.print(json);
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Like like = JsonUtil.convertJsonToLike(request);

        System.out.println("like post" + like);

        likeService.addLike(like);
    }
}
