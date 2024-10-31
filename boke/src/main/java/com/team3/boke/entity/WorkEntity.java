package com.team3.boke.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import lombok.Data;
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
@TableName("t_work")
@ApiModel(value = "WorkEntity对象", description = "")
public class WorkEntity extends Model<WorkEntity> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("user_id")
    private String userId;

    @TableField("work_type")
    private String workType;

    @TableField("tag")
    private String tag;

    @TableField("work_date")
    private LocalDate workDate;

    @TableField("content")
    private String content;

    @TableField("like_count")
    private Integer likeCount;   // 新增点赞数量

    @TableField("dislike_count")
    private Integer dislikeCount; // 新增踩数量

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
