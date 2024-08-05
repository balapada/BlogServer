package com.blogserver.test;

import com.blogserver.entity.User;
import com.blogserver.mapper.UserMapper;
import com.blogserver.util.GetSqlSession;
import org.apache.ibatis.session.SqlSession;

public class test {
    public static void main(String[] args) {
        SqlSession session = GetSqlSession.createSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);

        User user = new User();
        user.setUserAccount("zhangsan");
        user.setUserName("张三");
        user.setUserPassword("123456");
        try {
            userMapper.insertUser(user);
            String name = userMapper.getUserNameByUserAccount(user.getUserAccount());
            System.out.println(name);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }

    }
}
