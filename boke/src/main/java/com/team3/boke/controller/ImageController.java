package com.team3.boke.controller;

import com.team3.boke.entity.ImageEntity;
import com.team3.boke.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 卯择宇
 * @since 2024-10-28
 */
@RestController
@RequestMapping("team3.boke/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    // 添加图片
    @PostMapping("/add")
    public ResponseEntity<String> addImage(@RequestParam Integer workId, @RequestParam("content") MultipartFile file) {
        try {
            imageService.addImage(workId, file);
            return ResponseEntity.status(HttpStatus.CREATED).body("Image added successfully!");
        } catch (RuntimeException e) {
            // 捕获并处理运行时异常，返回合适的错误响应
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding image" );
        } catch (Exception e) {
            // 捕获其他异常，返回合适的错误响应
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid request" );
        }
    }

    // 删除图片
    @DeleteMapping("/{id}")
    public String deleteImage(@PathVariable Integer id) {
        imageService.deleteImage(id);
        return "Image deleted successfully!";
    }

    // 查找图片
    @GetMapping("/{id}")
    public ResponseEntity<ImageEntity> getImage(@PathVariable Integer id) {
        ImageEntity imageEntity = imageService.getImageById(id);
        if (imageEntity.getImage() != null) {
            return ResponseEntity.ok(imageEntity);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
