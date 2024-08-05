package com.blogserver.mapper;

import com.blogserver.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {
    @Insert("INSERT INTO table_user (userAccount, userName, userPassword) " +
            "VALUES (#{userAccount}, #{userName}, #{userPassword})")
    int insertUser(User user);

    @Select("SELECT userName FROM table_user WHERE userAccount = #{userAccount}")
    String getUserNameByUserAccount(String userAccount);

    @Select("SELECT * FROM table_user WHERE userAccount = #{userAccount}")
    User getUserByUserAccount(String userAccount);

    @Select("SELECT COUNT(*) FROM table_user WHERE userAccount = #{userAccount}")
    int countByUserAccount(String userAccount);

    @Select("SELECT COUNT(*) FROM table_user WHERE userName = #{userName}")
    int countByUserName(String userName);

    @Update("UPDATE table_user SET userProfileUrl = #{userProfileUrl} WHERE userAccount = #{userAccount}")
    void updateUserProfileUrl(@Param("userProfileUrl") String userProfileUrl,@Param("userAccount") String userAccount);

    @Select("SELECT userProfileUrl FROM table_user WHERE userAccount = #{userAccount}")
    String getProfileUrlByUserAccount(String userAccount);

    @Select("SELECT tu.* " +
            "FROM table_user tu " +
            "INNER JOIN table_relation tr ON tu.userAccount = tr.followerAccount " +
            "WHERE tr.userAccount = #{userAccount}")
    List<User> getFollowerListByUserAccount(String userAccount);

    @Select("SELECT tu.* " +
            "FROM table_user tu " +
            "INNER JOIN table_relation tr ON tu.userAccount = tr.userAccount " +
            "WHERE tr.followerAccount = #{followerAccount}")
    List<User> getAttentionListByFollowerAccount(String followerAccount);
}
