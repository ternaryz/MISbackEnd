package com.tongyuan.model.service;

import com.tongyuan.model.domain.User;

import java.util.Map;

/**
 * Created by Administrator on 2017-7-11.
 */
public interface UserService {
    int add(User user);
    int update(User user);
    int deleteByIds(String[] ids);
    User queryUserById(Long id);
    User querUserByName(Map<String, Object> params);
    User getUserByName(String userName);
}
