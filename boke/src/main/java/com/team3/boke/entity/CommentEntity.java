package com.team3.boke.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 卯择宇
 * @since 2024-10-28
 */
@Data
@TableName("t_comment")
@ApiModel(value = "CommentEntity", description = "评论实体类，表示用户对作品的评论")
public class CommentEntity extends Model<CommentEntity> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("work_id")
    private Integer workId;

    @TableField("source_user_id")
    private String sourceUserId;

    @TableField("target_user_id")
    private String targetUserId;

    @TableField("comment_date")
    private LocalDateTime commentDate;

    @TableField("content")
    private String content;

    // 目标评论 ID
    @TableField("target_comment_id")
    private Integer targetCommentId;

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
