package com.blogserver.service;

import com.blogserver.entity.FollowRequestBody;
import com.blogserver.entity.User;
import com.blogserver.mapper.FollowMapper;
import com.blogserver.mapper.UserMapper;
import com.blogserver.util.GetSqlSession;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserService {
    public List<User> getFollowerListByUserAccount(String userAccount) {
        SqlSession session = GetSqlSession.createSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);

        return userMapper.getFollowerListByUserAccount(userAccount);
    }

    public List<User> getAttentionListByFollowerAccount(String followerAccount) {
        SqlSession session = GetSqlSession.createSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);

        return userMapper.getAttentionListByFollowerAccount(followerAccount);
    }

    public void addFollow(FollowRequestBody followRequestBody) {
        SqlSession session = GetSqlSession.createSqlSession();
        FollowMapper followMapper = session.getMapper(FollowMapper.class);
        int count = followMapper.getCountByUserAccountAndFollowerAccount(followRequestBody.getUserAccount(), followRequestBody.getFollowerAccount());
        //未关注则执行关注
        if(count == 0){
            try {
                followMapper.insertFollow(followRequestBody.getUserAccount(), followRequestBody.getFollowerAccount());
                session.commit();
            } catch (Exception e) {
                e.printStackTrace();
                // 发生异常时回滚事务
                session.rollback();
            } finally {
                session.close();
            }
        }
        //已关注则取消关注
        if(count == 1){
            try {
                followMapper.deleteFollow(followRequestBody.getUserAccount(), followRequestBody.getFollowerAccount());
                session.commit();
            } catch (Exception e) {
                e.printStackTrace();
                // 发生异常时回滚事务
                session.rollback();
            } finally {
                session.close();
            }
        }
    }

    public Integer getFollowerCountByUserAccount(String userAccount) {
        SqlSession session = GetSqlSession.createSqlSession();
        FollowMapper followMapper = session.getMapper(FollowMapper.class);

        return followMapper.getFollowerCountByUserAccount(userAccount);
    }

    public Integer getAttentionCountByFollowerAccount(String followerAccount) {
        SqlSession session = GetSqlSession.createSqlSession();
        FollowMapper followMapper = session.getMapper(FollowMapper.class);

        return followMapper.getAttentionCountByFollowerAccount(followerAccount);
    }

    public Integer getCountByUserAccountAndFollowerAccount(String userAccount, String followerAccount) {
        SqlSession session = GetSqlSession.createSqlSession();
        FollowMapper followMapper = session.getMapper(FollowMapper.class);

        return followMapper.getCountByUserAccountAndFollowerAccount(userAccount, followerAccount);
    }
}
