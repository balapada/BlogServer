package com.blogserver.util;

import com.blogserver.entity.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class JsonUtil {

    public static final Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss").create();

    public static BlogRequestBody convertJsonToBlog(HttpServletRequest request) {
        try (Reader reader = request.getReader()) {
            return gson.fromJson(reader, BlogRequestBody.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static User convertJsonToUser(HttpServletRequest request) {
        try (Reader reader = request.getReader()) {
            return gson.fromJson(reader, User.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertObjectToJson(Object object) {
        return gson.toJson(object);
    }

    public static String convertBlogListToJson(List<Blog> blogList) {
        return gson.toJson(blogList);
    }

    public static CommentRequestBody convertJsonToComment(HttpServletRequest request) {
        try (Reader reader = request.getReader()) {
            return gson.fromJson(reader, CommentRequestBody.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertCommentListToJson(List<Comment> commentList) {
        return gson.toJson(commentList);
    }

    public static String convertLikeListToJson(List<Like> likeList) {
        return gson.toJson(likeList);
    }

    public static Like convertJsonToLike(HttpServletRequest request) {
        try (Reader reader = request.getReader()) {
            return gson.fromJson(reader, Like.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static FollowRequestBody convertJsonToFollowRequestBody(HttpServletRequest request) {
        try (Reader reader = request.getReader()) {
            return gson.fromJson(reader, FollowRequestBody.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
