package com.tongyuan.model.dao;

import com.tongyuan.model.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Created by Administrator on 2017-7-11.
 */
@Mapper
public interface UserMapper {
    int add(User user);
    int update(User user);
    int deleteByIds(String[] ids);
    User queryUserById(Long id);
    User querUserByName(Map<String, Object> params);
    User getUserByName(String name);
}
