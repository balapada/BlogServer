package com.blogserver.mapper;

import com.blogserver.entity.Like;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LikeMapper {
    @Insert("INSERT INTO table_like (userAccount, blogId, userName) " +
            "VALUES (#{userAccount}, #{blogId}, #{userName})")
    void insertLike(Like like);

    @Delete("DELETE FROM table_like WHERE userAccount = #{userAccount} AND blogId = #{blogId}")
    void deleteLikeByUserAndBlog(@Param("userAccount") String userAccount,@Param("blogId") Integer blogId);

    @Select("SELECT * FROM table_like WHERE blogId = #{blogId}")
    List<Like> getLikeListByBlogId(Integer blogId);
}
