package com.blogserver.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Blog {
    private Integer blogId;
    private String userAccount;
    private String userName;
    private String blogText;
    private Timestamp blogTime;
    private Integer commentCount;
    private Integer likeCount;
    private String blogPhotoUrl;

    @Override
    public String toString() {
        return "Blog{" +
                "blogId=" + blogId +
                ", userAccount='" + userAccount + '\'' +
                ", userName='" + userName + '\'' +
                ", blogText='" + blogText + '\'' +
                ", blogTime=" + blogTime +
                ", commentCount=" + commentCount +
                ", likeCount=" + likeCount +
                ", blogPhotoUrl='" + blogPhotoUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Blog blog = (Blog) object;
        return Objects.equals(blogId, blog.blogId) && Objects.equals(userAccount, blog.userAccount) && Objects.equals(userName, blog.userName) && Objects.equals(blogText, blog.blogText) && Objects.equals(blogTime, blog.blogTime) && Objects.equals(commentCount, blog.commentCount) && Objects.equals(likeCount, blog.likeCount) && Objects.equals(blogPhotoUrl, blog.blogPhotoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blogId, userAccount, userName, blogText, blogTime, commentCount, likeCount, blogPhotoUrl);
    }

    public String getBlogPhotoUrl() {
        return blogPhotoUrl;
    }

    public void setBlogPhotoUrl(String blogPhotoUrl) {
        this.blogPhotoUrl = blogPhotoUrl;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Timestamp getBlogTime() {
        return blogTime;
    }

    public void setBlogTime(Timestamp blogTime) {
        this.blogTime = blogTime;
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

    public String getBlogText() {
        return blogText;
    }

    public void setBlogText(String blogText) {
        this.blogText = blogText;
    }


}
