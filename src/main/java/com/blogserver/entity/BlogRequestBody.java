package com.blogserver.entity;

import java.util.Objects;

public class BlogRequestBody {
    private Blog blog;
    private String action;

    @Override
    public String toString() {
        return "BlogRequestBody{" +
                "blog=" + blog +
                ", action='" + action + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BlogRequestBody that = (BlogRequestBody) object;
        return Objects.equals(blog, that.blog) && Objects.equals(action, that.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blog, action);
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
