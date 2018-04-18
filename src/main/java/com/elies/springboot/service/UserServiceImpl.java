package com.elies.springboot.service;

/**
 * 用户服务接口实现
 *
 * @author 牟雪
 * @since 2018/4/3
 */

import com.elies.springboot.dao.UserMapper;
import com.elies.springboot.domain.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public User userLogin(UserLogin userLogin){
        return userMapper.findByLogin(userLogin.getLoginName(), userLogin.getLoginPassword());
    }

    @Override
    public PageInfo<UserList> listUser(UserQuery userQuery){
        PageHelper.startPage(userQuery.getPageIndex(), userQuery.getPageSize());
        List<UserList> users = userMapper.findByCondition(userQuery);
        if(CollectionUtils.isEmpty(users)){
            return new PageInfo<>(Lists.<UserList>newArrayList());
        }
        return new PageInfo<>(users);
    }

    @Override
    public void insertUser(UserInsert userInsert){
        User user = new User();
        BeanUtils.copyProperties(userInsert, user);
        user.setCreateTime(System.currentTimeMillis());
        userMapper.insert(user);
    }

    @Override
    public void updateUser(UserUpdate userUpdate){
        User user = userMapper.findById(userUpdate.getUserId());
        BeanUtils.copyProperties(userUpdate, user);
        user.setUpdateTime(System.currentTimeMillis());
        userMapper.update(user);
    }

    @Override
    public void deleteUser(Long userId){
        userMapper.delete(userId);
    }

    @Override
    public void batchDeleteUser(String userIds){
        userMapper.batchDelete(userIds);
    }
}
