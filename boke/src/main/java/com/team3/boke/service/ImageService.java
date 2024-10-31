package com.team3.boke.service;

import com.team3.boke.entity.ImageEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 卯择宇
 * @since 2024-10-28
 */
public interface ImageService {
    // 添加图片
    void addImage(Integer workId, MultipartFile content);

    // 删除图片
    void deleteImage(Integer id);

    ImageEntity getImageById(Integer id);
}
