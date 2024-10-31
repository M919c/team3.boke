package com.team3.boke.service.Impl;

import com.team3.boke.entity.ImageEntity;
import com.team3.boke.mapper.ImageDao;
import com.team3.boke.service.ImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Blob;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 卯择宇
 * @since 2024-10-28
 */
@Service
public class ImageServiceImp implements ImageService {

    @Autowired
    private ImageDao imageDao;

    // 添加图片
    @Override
    @Transactional // 保证方法中的操作在事务中进行
    public void addImage(Integer workId, MultipartFile file) {
        // 使用项目根目录的绝对路径来保存文件
        String directoryPath = System.getProperty("user.dir") + File.separator + "data" + File.separator + "image";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Step 1: 创建 ImageEntity 实例并插入数据库
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setWorkId(workId);
        imageEntity.setUrl("temp"); // 临时 URL
        imageDao.insert(imageEntity);  // 插入数据库，获取 ID

        // 获取生成的 ID
        Integer imageId = imageEntity.getId();
        if (imageId == null) {
            throw new RuntimeException("Failed to retrieve image ID from database.");
        }

        // Step 2: 使用数据库生成的 ID 命名文件
        String fileName = imageId + getFileExtension(file.getOriginalFilename());
        File imageFile = new File(directory, fileName);

        try {
            // Step 3: 将文件保存到本地
            file.transferTo(imageFile);

            // Step 4: 更新数据库中图片的 URL
            imageEntity.setUrl(imageFile.getAbsolutePath());
            imageDao.updateById(imageEntity); // 更新 URL
        } catch (IOException e) {
            imageDao.deleteById(imageId); // 删除数据库记录
            throw new RuntimeException("Error saving image file: " + e.getMessage(), e);
        } catch (Exception e) {
            if (imageFile.exists()) {
                imageFile.delete();
            }
            throw new RuntimeException("Error updating image URL in database: " + e.getMessage(), e);
        }
    }


    @Override
    @Transactional // 添加事务管理
    public void deleteImage(Integer id) {
        // 查询图片实体以获取文件路径
        ImageEntity imageEntity = imageDao.selectById(id);
        if (imageEntity != null) {
            // 删除文件
            File imageFile = new File(imageEntity.getUrl());
            if (imageFile.exists()) {
                if (!imageFile.delete()) {
                    throw new RuntimeException("Failed to delete image file: " + imageEntity.getUrl());
                }
            }
            // 删除数据库记录
            imageDao.deleteById(id); // 使用 MyBatis Plus 的 deleteById 方法
        } else {
            throw new RuntimeException("Image not found with id: " + id);
        }
    }

    public ImageEntity getImageById(Integer id) {
        ImageEntity imageEntity = imageDao.selectById(id); // 通过 MyBatis Plus 查询图片
        if (imageEntity != null) {
            imageEntity.setImage(imageEntity.getUrl().replace("\\", "/")); // 设置图片字节数组到实体
            return imageEntity;
        }
        return null;
    }



    // 获取文件扩展名
    private String getFileExtension(String originalFilename) {
        return originalFilename != null && originalFilename.lastIndexOf('.') > 0 ?
                originalFilename.substring(originalFilename.lastIndexOf('.')) : ""; // 返回文件扩展名
    }
}