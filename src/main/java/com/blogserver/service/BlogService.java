package com.blogserver.service;

import com.blogserver.entity.Blog;
import com.blogserver.mapper.BlogMapper;
import com.blogserver.mapper.UserMapper;
import com.blogserver.util.GetSqlSession;
import org.apache.ibatis.session.SqlSession;

import java.util.Collections;
import java.util.List;

public class BlogService {

    public List<Blog> getBlog() {
        SqlSession session = GetSqlSession.createSqlSession();
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);

        List<Blog> blogList = blogMapper.getBlog();

        Collections.reverse(blogList);

        return blogList;
    }
    public void blogAdd(Blog blog) {
        SqlSession session = GetSqlSession.createSqlSession();
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);

        try {
            blogMapper.insertBlog(blog);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 发生异常时回滚事务
            session.rollback();
        } finally {
            session.close();
        }

    }

    public Integer getCommentCountByBlogId(Integer blogId) {
        SqlSession session = GetSqlSession.createSqlSession();
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);

        return blogMapper.getCommentCountByBlogId(blogId);
    }

    public Integer getLikeCountByBlogId(Integer blogId) {
        SqlSession session = GetSqlSession.createSqlSession();
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);

        return blogMapper.getLikeCountByBlogId(blogId);
    }

    public String getProfileUrlByUserAccount(String userAccount) {
        SqlSession session = GetSqlSession.createSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);

        return userMapper.getProfileUrlByUserAccount(userAccount);
    }

    public List<Blog> getBlogByUserAccount(String userAccount) {
        SqlSession session = GetSqlSession.createSqlSession();
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);

        List<Blog> blogList = blogMapper.getBlogByUserAccount(userAccount);

        Collections.reverse(blogList);

        return blogList;
    }

    public Integer getBlogCountByUserAccount(String userAccount) {
        SqlSession session = GetSqlSession.createSqlSession();
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);

        return blogMapper.getBlogCountByUserAccount(userAccount);
    }

    public void deleteBlogByBlogId(Blog blog) {
        SqlSession session = GetSqlSession.createSqlSession();
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);

        try {
            blogMapper.deleteBlogByBlogId(blog.getBlogId());
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
