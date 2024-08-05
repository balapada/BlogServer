package com.blogserver.service;

import com.blogserver.mapper.UserMapper;
import com.blogserver.util.GetSqlSession;
import org.apache.ibatis.session.SqlSession;

public class ImgUploadService {

    public void updateProfile(String userProfileUrl, String userAccount) {
        SqlSession session = GetSqlSession.createSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);

        try {
            userMapper.updateUserProfileUrl(userProfileUrl, userAccount);
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
