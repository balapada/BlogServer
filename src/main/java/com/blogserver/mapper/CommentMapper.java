package com.blogserver.mapper;

import com.blogserver.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper {
    @Insert("INSERT INTO table_comment (blogId, commentText, userAccount, userName, commentTime) " +
            "VALUES (#{blogId}, #{commentText}, #{userAccount}, #{userName}, #{commentTime})")
    void insertComment(Comment comment);

    @Select("SELECT * FROM table_comment WHERE blogId = #{blogId}")
    List<Comment> getCommentByBlogId(Integer blogId);

    @Delete("DELETE FROM table_comment WHERE commentId = #{commentId}")
    void deleteCommentByCommentId(Integer commentId);

}
