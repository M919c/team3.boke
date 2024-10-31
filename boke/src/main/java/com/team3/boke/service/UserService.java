package com.team3.boke.service;

import com.team3.boke.entity.UserEntity;
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
public interface UserService{


    UserEntity getUserById(String id);

    List<UserEntity> getAllUsers();

    boolean addUser(UserEntity user);

    boolean updateUser(UserEntity user);

    boolean deleteUser(String id);
}
