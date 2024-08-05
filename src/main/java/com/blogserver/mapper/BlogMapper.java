package com.blogserver.mapper;

import com.blogserver.entity.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BlogMapper {
    @Insert("INSERT INTO table_blog (userAccount, userName, blogText, blogTime, blogPhotoUrl) " +
            "VALUES (#{userAccount}, #{userName}, #{blogText}, #{blogTime}, #{blogPhotoUrl})")
    void insertBlog(Blog blog);

    @Select("SELECT * FROM table_blog")
    List<Blog> getBlog();

    @Select("SELECT * FROM table_blog WHERE userAccount = #{userAccount}")
    List<Blog> getBlogByUserAccount(String userAccount);

    @Select("SELECT COUNT(*) FROM table_blog WHERE userAccount = #{userAccount}")
    int getBlogCountByUserAccount(String userAccount);

    @Select("SELECT commentCount FROM table_blog WHERE blogId = #{blogId}")
    Integer getCommentCountByBlogId(Integer blogId);

    @Update("UPDATE table_blog SET commentCount = commentCount + 1 WHERE blogId = #{blogId}")
    void incrementCommentCount(int blogId);

    @Update("UPDATE table_blog SET commentCount = commentCount - 1 WHERE blogId = #{blogId}")
    void decrementCommentCount(int blogId);

    @Select("SELECT likeCount FROM table_blog WHERE blogId = #{blogId}")
    Integer getLikeCountByBlogId(Integer blogId);

    @Update("UPDATE table_blog SET likeCount = likeCount + 1 WHERE blogId = #{blogId}")
    void incrementLikeCount(int blogId);

    @Update("UPDATE table_blog SET likeCount = likeCount - 1 WHERE blogId = #{blogId}")
    void decrementLikeCount(int blogId);

    @Delete("DELETE FROM table_blog WHERE blogId = #{blogId}")
    void deleteBlogByBlogId(Integer blogId);
}
