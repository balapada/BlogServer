package com.blogserver.entity;

import java.util.Objects;

public class CommentRequestBody {
    private Comment comment;
    private String action;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CommentRequestBody that = (CommentRequestBody) object;
        return Objects.equals(comment, that.comment) && Objects.equals(action, that.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment, action);
    }

    @Override
    public String toString() {
        return "CommentRequestBody{" +
                "comment=" + comment +
                ", action='" + action + '\'' +
                '}';
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
