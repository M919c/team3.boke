package com.team3.boke.mapper;

import com.team3.boke.entity.CommentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 卯择宇
 * @since 2024-10-28
 */
@Mapper
public interface CommentDao extends BaseMapper<CommentEntity> {

    // 插入评论
    @Insert("INSERT INTO t_comment (work_id, source_user_id, target_user_id, " +
            "comment_date, content,target_comment_id) " +
            "VALUES (#{workId}, #{sourceUserId}, #{targetUserId}, " +
            "#{commentDate}, #{content},#{targetCommentId})")
    int insertComment(CommentEntity comment);

    // 根据评论 ID 删除评论
    @Delete("DELETE FROM t_comment WHERE id = #{id}")
    int deleteCommentById(@Param("id") Long id);

    // 根据作品 ID 查找评论
    @Select("SELECT id, work_id, source_user_id, target_user_id, comment_date, content " +
            "FROM t_comment WHERE work_id = #{workId}")
    List<CommentEntity> selectCommentsByWorkId(@Param("workId") Long workId);
}