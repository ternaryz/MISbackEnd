package com.tongyuan.model.service.impl;

import com.tongyuan.model.dao.UserMapper;
import com.tongyuan.model.domain.User;
import com.tongyuan.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2017-7-11.
 */

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public int add(User user) {
        return this.userMapper.add(user);
    }

    @Override
    public int update(User user) {
        return this.userMapper.update(user);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.userMapper.deleteByIds(ids);
    }

    @Override
    public User queryUserById(Long id) {
        return this.userMapper.queryUserById(id);
    }

    @Override
    public User querUserByName(Map<String, Object> params) {
        return this.userMapper.querUserByName(params);
    }

    @Override
    public User getUserByName(String name){
        return userMapper.getUserByName(name);
    }
}
