package com.team3.boke.service.Impl;

import com.team3.boke.entity.CommentEntity;
import com.team3.boke.mapper.CommentDao;
import com.team3.boke.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 卯择宇
 * @since 2024-10-28
 */
@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentDao commentDao;

    // 插入评论
    @Override
    public int addComment(CommentEntity comment) {
        return commentDao.insertComment(comment);
    }

    // 根据评论 ID 删除评论
    @Override
    public int removeCommentById(Long id) {
        return commentDao.deleteCommentById(id);
    }

    // 根据作品 ID 查找评论
    @Override
    public List<CommentEntity> getCommentsByWorkId(Long workId) {
        return commentDao.selectCommentsByWorkId(workId);
    }


}
