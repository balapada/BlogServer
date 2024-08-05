package com.blogserver.controller;

import com.blogserver.entity.Comment;
import com.blogserver.entity.CommentRequestBody;
import com.blogserver.service.CommentService;
import com.blogserver.util.JsonUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
    private final CommentService commentService = new CommentService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        CommentRequestBody commentRequestBody = JsonUtil.convertJsonToComment(request);
        Comment comment = commentRequestBody.getComment();
        String action = commentRequestBody.getAction();

        System.out.println("commentRequestBody" + commentRequestBody);
        System.out.println("comment post" + comment);

        if(action.equals("addComment")) commentService.commentAdd(comment);
        if(action.equals("deleteCommentByCommentId")) commentService.deleteCommentByCommentId(comment);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String blogIdStr = request.getParameter("blogId");
        Integer blogId = Integer.parseInt(blogIdStr);

        List<Comment> commentList = commentService.getCommentByBlogId(blogId);

        String json = JsonUtil.convertCommentListToJson(commentList);

        //测试
        System.out.println("comment get" + json);

        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            out.print(json);
            out.flush();
        }
    }
}
