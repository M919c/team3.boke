package com.team3.boke.service;

import com.team3.boke.entity.CommentEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 卯择宇
 * @since 2024-10-28
 */
public interface CommentService {

    // 插入评论
    int addComment(CommentEntity comment);

    // 根据评论 ID 删除评论
    int removeCommentById(Long id);

    // 根据作品 ID 查找评论
    List<CommentEntity> getCommentsByWorkId(Long workId);

}
