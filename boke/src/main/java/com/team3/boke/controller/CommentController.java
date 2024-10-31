package com.team3.boke.controller;

import com.team3.boke.entity.CommentEntity;
import com.team3.boke.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 卯择宇
 * @since 2024-10-28
 */
@RestController
@RequestMapping("/team3.boke/comment-entity")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 插入评论
    @PostMapping
    public ResponseEntity<String> addComment(@RequestBody CommentEntity comment) {
        System.out.println(comment);
        int result = commentService.addComment(comment);
        if (result > 0) {
            return new ResponseEntity<>("评论插入成功", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("评论插入失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 根据评论 ID 删除评论
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeComment(@PathVariable Long id) {
        int result = commentService.removeCommentById(id);
        if (result > 0) {
            return new ResponseEntity<>("评论删除成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("评论删除失败", HttpStatus.NOT_FOUND);
        }
    }

    // 根据作品 ID 查找评论
    @GetMapping("/work/{workId}")
    public ResponseEntity<List<CommentEntity>> getComments(@PathVariable Long workId) {
        List<CommentEntity> comments = commentService.getCommentsByWorkId(workId);
        if (comments != null && !comments.isEmpty()) {
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
