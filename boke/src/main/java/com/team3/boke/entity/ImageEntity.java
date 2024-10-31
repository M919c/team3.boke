package com.team3.boke.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.sql.Blob;

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
@TableName("t_image")
@ApiModel(value = "ImageEntity对象", description = "")
public class ImageEntity extends Model<ImageEntity> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("work_id")
    private Integer workId;

    @TableField("url")
    private String url;

    // 新增字段，用于存储图片字节数组
    @TableField(exist = false) // 表示此字段不在数据库中
    private String image;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}

