package com.team3.boke.service.Impl;

import com.team3.boke.entity.UserEntity;
import com.team3.boke.mapper.UserDao;
import com.team3.boke.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 卯择宇
 * @since 2024-10-28
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    private static AtomicLong idCounter = new AtomicLong();


    private String generateUniqueId() {
        // 查询 t_user 表中 id 的最大值
        String maxId = userDao.selectMaxId();

        // 如果没有找到，则从 1000000001 开始
        if (maxId == null) {
            return String.valueOf(1000000001);
        }

        // 将 maxId 转换为整数并加一
        long newId = Long.parseLong(maxId) + 1;

        return String.valueOf(newId);
    }

    @Override
    public UserEntity getUserById(String id) {
        return userDao.selectUserById(id);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userDao.selectAllUsers();
    }

    @Override
    public boolean addUser(UserEntity user) {
        // 可以添加额外的业务逻辑，比如检查用户是否已经存在
        String uniqueId = generateUniqueId();
        user.setId(uniqueId); // 设置生成的唯一 ID
        return userDao.insertUser(user) > 0;
    }

    @Override
    public boolean updateUser(UserEntity user) {
        // 可以添加额外的业务逻辑，比如检查用户是否存在
        return userDao.updateUser(user) > 0;
    }

    @Override
    public boolean deleteUser(String id) {
        // 可以添加额外的业务逻辑，比如检查用户是否存在
        return userDao.deleteUserById(id) > 0;
    }
}
