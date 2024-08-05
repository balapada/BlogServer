package com.blogserver.service;

import com.blogserver.entity.Like;
import com.blogserver.mapper.BlogMapper;
import com.blogserver.mapper.LikeMapper;
import com.blogserver.util.GetSqlSession;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class LikeService {
    public List<Like> getLikeListByBlogId(Integer blogId) {
        SqlSession session = GetSqlSession.createSqlSession();
        LikeMapper likeMapper = session.getMapper(LikeMapper.class);

        return likeMapper.getLikeListByBlogId(blogId);
    }

    public void addLike(Like like) {
        SqlSession session = GetSqlSession.createSqlSession();
        LikeMapper likeMapper = session.getMapper(LikeMapper.class);
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);

        List<Like> likeList = likeMapper.getLikeListByBlogId(like.getBlogId());
        if(likeList.contains(like)){
            try {
                likeMapper.deleteLikeByUserAndBlog(like.getUserAccount(), like.getBlogId());
                blogMapper.decrementLikeCount(like.getBlogId());
                session.commit();
            } catch (Exception e) {
                e.printStackTrace();
                // 发生异常时回滚事务
                session.rollback();
            } finally {
                session.close();
            }

        }else{

            try {
                likeMapper.insertLike(like);
                blogMapper.incrementLikeCount(like.getBlogId());
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
}
