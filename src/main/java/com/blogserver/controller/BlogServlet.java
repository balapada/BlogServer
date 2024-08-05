package com.blogserver.controller;

import com.blogserver.entity.Blog;
import com.blogserver.entity.BlogRequestBody;
import com.blogserver.service.BlogService;
import com.blogserver.util.JsonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/blog")
public class BlogServlet extends HttpServlet {
    private final BlogService blogService = new BlogService();
    private static final Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss").create();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        BlogRequestBody blogRequestBody = JsonUtil.convertJsonToBlog(request);
        Blog blog = blogRequestBody.getBlog();
        String action = blogRequestBody.getAction();

        System.out.println("blog post" + blog);
        System.out.println("blogRequest" + blogRequestBody);

        if(action.equals("addBlog")) blogService.blogAdd(blog);
        if(action.equals("deleteBlogByBlogId")) blogService.deleteBlogByBlogId(blog);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if("getBlogCountByUserAccount".equals(action)){
            String userAccount = request.getParameter("userAccount");

            Integer count = blogService.getBlogCountByUserAccount(userAccount);

            String result = count.toString();
            response.setContentType("text/plain");
            try (PrintWriter out = response.getWriter()) {
                out.print(gson.toJson(result));
                out.flush();
            }
        }

        if ("getBlogByUserAccount".equals(action)){
            String userAccount = request.getParameter("userAccount");

            List<Blog> blogList = blogService.getBlogByUserAccount(userAccount);

            String json = JsonUtil.convertBlogListToJson(blogList);

            //测试
            System.out.println("blog get" + json);

            response.setContentType("application/json");
            try (PrintWriter out = response.getWriter()) {
                out.print(json);
                out.flush();
            }
        }

        if ("getBlog".equals(action)){
            List<Blog> blogList = blogService.getBlog();

            String json = JsonUtil.convertBlogListToJson(blogList);

            //测试
            System.out.println("blog get" + json);

            response.setContentType("application/json");
            try (PrintWriter out = response.getWriter()) {
                out.print(json);
                out.flush();
            }
        }

        if("getBlogCommentCount".equals(action)){
            String blogIdStr = request.getParameter("blogId");
            Integer blogId = Integer.parseInt(blogIdStr);

            Integer commentCount = blogService.getCommentCountByBlogId(blogId);

            String result = commentCount.toString();
            response.setContentType("text/plain");
            try (PrintWriter out = response.getWriter()) {
                out.print(gson.toJson(result));
                out.flush();
            }
        }

        if("getBlogLikeCount".equals(action)){
            String blogIdStr = request.getParameter("blogId");
            Integer blogId = Integer.parseInt(blogIdStr);

            Integer likeCount = blogService.getLikeCountByBlogId(blogId);

            String result = likeCount.toString();
            response.setContentType("text/plain");
            try (PrintWriter out = response.getWriter()) {
                out.print(gson.toJson(result));
                out.flush();
            }
        }

        if("getProfileUrlByUserAccount".equals(action)) {
            String userAccount = request.getParameter("userAccount");

            String userProfileUrl = blogService.getProfileUrlByUserAccount(userAccount);

            System.out.println(userProfileUrl);

            response.setContentType("text/plain");
            try (PrintWriter out = response.getWriter()) {
                out.print(gson.toJson(userProfileUrl));
                out.flush();
            }

        }

    }
}
