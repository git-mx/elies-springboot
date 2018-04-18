package com.elies.springboot.dao;

import com.elies.springboot.domain.User;
import com.elies.springboot.domain.UserList;
import com.elies.springboot.domain.UserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /**
     * 根据ID获取单个用户信息
     *
     * @param userId 用户ID
     * @return User
     * @author 牟雪
     * @date 2018/4/3
     *
     **/
    User findById(@Param("userId") Long userId);

    /**
     * 用户登录
     *
     * @param loginName 登录名
     * @param loginPassword 登录密码
     * @return User
     * @author 牟雪
     * @date 2018/4/3
     *
     **/
    User findByLogin(@Param("loginName") String loginName, @Param("loginPassword") String loginPassword);

    /**
     * 获取查询套件列表页
     *
     * @param userQuery 查询条件
     * @return java.util.List<com.elies.base.domain.UserList>
     * @author 牟雪
     * @date 2018/4/3
     *
     **/
    List<UserList> findByCondition(UserQuery userQuery);

    /**
     * 添加
     *
     * @param user 用户实体
     * @return void
     * @author 牟雪
     * @date 2018/4/3
     *
     **/
    void insert(User user);

    /**
     * 修改
     *
     * @param user
     * @return void
     * @author 牟雪
     * @date 2018/4/3
     *
     **/
    void update(User user);

    /**
     * 删除用户
     *
     * @param userId 用户ID
     * @return void
     * @author 牟雪
     * @date 2018/4/3
     *
     **/
    void delete(@Param("userId")Long userId);

    /**
     * 批量删除用户
     *
     * @param userIds 用户IDS
     * @return void
     * @author 牟雪
     * @date 2018/4/3
     *
     **/
    void batchDelete(@Param("userIds")String userIds);
}