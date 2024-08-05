package com.blogserver.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface FollowMapper {
    @Select("SELECT COUNT(*) FROM table_relation WHERE userAccount = #{userAccount} AND followerAccount = #{followerAccount}")
    int getCountByUserAccountAndFollowerAccount(@Param("userAccount") String userAccount,@Param("followerAccount") String followerAccount);

    @Select("SELECT COUNT(*) FROM table_relation WHERE userAccount = #{userAccount}")
    int getFollowerCountByUserAccount(String userAccount);

    @Select("SELECT COUNT(*) FROM table_relation WHERE followerAccount = #{followerAccount}")
    int getAttentionCountByFollowerAccount(String followerAccount);

    @Insert("INSERT INTO table_relation (userAccount, followerAccount) " +
            "VALUES (#{userAccount}, #{followerAccount})")
    void insertFollow(@Param("userAccount") String userAccount,@Param("followerAccount") String followerAccount);

    @Delete("DELETE FROM table_relation WHERE userAccount = #{userAccount} AND followerAccount = #{followerAccount}")
    void deleteFollow(@Param("userAccount") String userAccount,@Param("followerAccount") String followerAccount);
}
