package com.tongyuan.model.dao;

import com.tongyuan.model.domain.Directory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 目录dao方法
 * Created by xieyx on 2017-6-21.
 */
@Mapper
public interface DirectoryMapper {
    public List<Directory> queryListByPath(String absoluteAddress);
    boolean add(Directory directory);
    boolean update(Directory directory);
    boolean deleteByIds(String[] ids);
    public List<Directory> queryListByName(Map<String, Object> params);
    public List<Directory> findAllDirectory();
    public List<Directory> findRootDirectory();
    List<Directory> findRootDirectoryList(Map<String, Object> params);
    public List<Directory> queryListByParentId(Long id);
    public List<Directory> queryListById(Long id);
    public List<Directory> queryByParentName(Map<String, Object> params);
}
