package com.blogserver.entity;

import java.util.Objects;

public class Like {
    private String userAccount;
    private Integer blogId;
    private String userName;

    @Override
    public String toString() {
        return "Like{" +
                "userAccount='" + userAccount + '\'' +
                ", blogId=" + blogId +
                ", userName='" + userName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Like like = (Like) object;
        return Objects.equals(userAccount, like.userAccount) && Objects.equals(blogId, like.blogId) && Objects.equals(userName, like.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userAccount, blogId, userName);
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
