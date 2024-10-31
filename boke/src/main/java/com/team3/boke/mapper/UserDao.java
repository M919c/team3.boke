package com.team3.boke.mapper;

import com.team3.boke.entity.UserEntity;
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
public interface UserDao extends BaseMapper<UserEntity> {

    // 查询 t_user 表中 id 的最大值
    @Select("SELECT MAX(CAST(id AS UNSIGNED)) FROM t_user")
    String selectMaxId();
    // 根据 ID 查询用户
    @Select("SELECT * FROM t_user WHERE id = #{id}")
    UserEntity selectUserById(@Param("id") String id);

    // 查询所有用户
    @Select("SELECT * FROM t_user")
    List<UserEntity> selectAllUsers();

    // 插入新用户
    @Insert("INSERT INTO t_user (id, user_password, nickname, email, phone, gender, introduction) " +
            "VALUES (#{id}, #{userPassword}, #{nickname}, #{email}, #{phone}, #{gender}, #{introduction})")
    int insertUser(UserEntity user);

    // 更新用户信息
    @Update("UPDATE t_user SET user_password = #{userPassword}, nickname = #{nickname}, email = #{email}, " +
            "phone = #{phone}, gender = #{gender}, introduction = #{introduction} WHERE id = #{id}")
    int updateUser(UserEntity user);

    // 根据 ID 删除用户
    @Delete("DELETE FROM t_user WHERE id = #{id}")
    int deleteUserById(@Param("id") String id);

}
