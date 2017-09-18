package com.tongyuan.model.service;

import com.tongyuan.model.domain.Directory;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-6-21.
 */
public interface DirectoryService {
    public List<Directory> queryListByPath(String absoluteAddress);
    public boolean add(Directory directory);
    public boolean update(Directory directory);
    public boolean deleteByIds(String[] ids);
    public List<Directory> queryListByName(Map<String, Object> params);
    public List<Directory> findAllDirectory();
    public List<Directory> findRootDirectory();
    public List<Directory> findRootDirectoryList(Map<String, Object> params);
    public List<Directory> queryListByParentId(Long id);
    public List<Directory> queryListById(Long id);
    public List<Directory> queryByParentName(Map<String, Object> params);
}
