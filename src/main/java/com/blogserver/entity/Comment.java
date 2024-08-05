package com.blogserver.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Comment {
    private Integer commentId;
    private Integer blogId;
    private String commentText;
    private String userAccount;
    private String userName;
    private Timestamp commentTime;

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", blogId=" + blogId +
                ", commentText='" + commentText + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", userName='" + userName + '\'' +
                ", commentTime=" + commentTime +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Comment comment = (Comment) object;
        return Objects.equals(commentId, comment.commentId) && Objects.equals(blogId, comment.blogId) && Objects.equals(commentText, comment.commentText) && Objects.equals(userAccount, comment.userAccount) && Objects.equals(userName, comment.userName) && Objects.equals(commentTime, comment.commentTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, blogId, commentText, userAccount, userName, commentTime);
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }
}
