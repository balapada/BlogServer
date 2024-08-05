package com.blogserver.service;

import com.blogserver.entity.User;
import com.blogserver.entity.vo.LoginMessage;
import com.blogserver.mapper.UserMapper;
import com.blogserver.util.GetSqlSession;
import org.apache.ibatis.session.SqlSession;

public class LoginService {

    public LoginMessage userLogin(String userAccount, String userPassword) {
        LoginMessage loginMessage = new LoginMessage();

        SqlSession session = GetSqlSession.createSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);

        //判断用户是否存在
        User user = userMapper.getUserByUserAccount(userAccount);
        if(user == null) {
            loginMessage.setCode(0);
            loginMessage.setMessage("用户不存在!");
            return loginMessage;
        }

        //若用户存在，判断密码是否正确
        if(!userPassword.equals(user.getUserPassword())) {
            loginMessage.setCode(0);
            loginMessage.setMessage("密码不正确!");
            return loginMessage;
        }

        //登陆成功
        loginMessage.setCode(1);
        loginMessage.setMessage("登陆成功!");
        loginMessage.setObject(user);
        return loginMessage;
    }

    public LoginMessage userRegister(String userAccount, String userName, String userPassword) {
        LoginMessage loginMessage = new LoginMessage();

        SqlSession session = GetSqlSession.createSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);

        int count= userMapper.countByUserAccount(userAccount);
        if(count > 0) { //账号已存在
             loginMessage.setMessage("账号已存在!");
             loginMessage.setCode(0);
             return loginMessage;
        }

        count = userMapper.countByUserName(userName);
        if(count > 0) { //昵称已存在
            loginMessage.setMessage("昵称已存在!");
            loginMessage.setCode(0);
            return loginMessage;
        }

        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserName(userName);
        user.setUserPassword(userPassword);

        int rowsAffected = 0;
        try {
            rowsAffected = userMapper.insertUser(user);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 发生异常时回滚事务
            session.rollback();
        } finally {
            session.close();
        }

        //注册失败
        if(rowsAffected <= 0) {
            loginMessage.setCode(0);
            loginMessage.setMessage("注册失败!");
            return loginMessage;
        }

        //注册成功
        loginMessage.setCode(1);
        loginMessage.setMessage("注册成功!");
        loginMessage.setObject(user);
        return loginMessage;
    }

}
