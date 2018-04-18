package com.elies.springboot.service;

/**
 * 用户服务接口
 *
 * @author 牟雪
 * @since 2018/4/3
 */

import com.elies.springboot.domain.*;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;


public interface UserService {

    /**
     *
     * 用户登录
     *
     * @param userLogin POST对象
     * @return User
     * @author 牟雪
     * @date 2018/4/3
     *
     **/
    User userLogin(UserLogin userLogin);

    /**
     * 获取查询条件列表页
     *
     * @param userQuery
     * @return java.util.List<com.elies.base.domain.UserList>
     * @author 牟雪
     * @date 2018/4/3
     *
     **/
    PageInfo<UserList> listUser(UserQuery userQuery);

    /**
     * 添加用户
     *
     * @param userInsert 添加实体
     * @return void
     * @author 牟雪
     * @date 2018/4/3
     *
     **/
    void insertUser(UserInsert userInsert);

    /**
     * 修改用户
     *
     * @param userUpdate 修改实体
     * @return void
     * @author 牟雪
     * @date 2018/4/3
     *
     **/
    void updateUser(UserUpdate userUpdate);

    /**
     * 删除用户
     *
     * @param userId 用户ID
     * @return void
     * @author 牟雪
     * @date 2018/4/3
     *
     **/
    void deleteUser(@Param("userId") Long userId);

    /**
     * 批量删除用户
     *
     * @param userIds 用户IDS
     * @return void
     * @author 牟雪
     * @date 2018/4/3
     *
     **/
    void batchDeleteUser(@Param("userIds") String userIds);
}
