package com.blogserver.service;

import com.blogserver.entity.Comment;
import com.blogserver.mapper.BlogMapper;
import com.blogserver.mapper.CommentMapper;
import com.blogserver.util.GetSqlSession;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CommentService {

    public List<Comment> getCommentByBlogId(Integer blogId) {
        SqlSession session = GetSqlSession.createSqlSession();
        CommentMapper commentMapper = session.getMapper(CommentMapper.class);

        return commentMapper.getCommentByBlogId(blogId);
    }

    public void commentAdd(Comment comment) {
        SqlSession session = GetSqlSession.createSqlSession();
        CommentMapper commentMapper = session.getMapper(CommentMapper.class);
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);

        try {
            commentMapper.insertComment(comment);
            blogMapper.incrementCommentCount(comment.getBlogId());
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 发生异常时回滚事务
            session.rollback();
        } finally {
            session.close();
        }
    }

    public void deleteCommentByCommentId(Comment comment) {
        SqlSession session = GetSqlSession.createSqlSession();
        CommentMapper commentMapper = session.getMapper(CommentMapper.class);
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);

        try {
            commentMapper.deleteCommentByCommentId(comment.getCommentId());
            blogMapper.decrementCommentCount(comment.getBlogId());
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
