package com.tongyuan.model.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongyuan.model.dao.DirectoryMapper;
import com.tongyuan.model.domain.Directory;
import com.tongyuan.model.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 目录数据操作
 * Created by xieyx on 2017-6-21.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class DirectoryServiceImpl implements DirectoryService{
    @Autowired
    private DirectoryMapper directoryMapper;

    @Override
    public List<Directory> queryListByPath(String absoluteAddress) {
        return this.directoryMapper.queryListByPath(absoluteAddress);
    }

    @Override
    public boolean add(Directory directory) {
        return this.directoryMapper.add(directory);
    }

    @Override
    public boolean update(Directory directory) {
        return this.directoryMapper.update(directory);
    }

    @Override
    public boolean deleteByIds(String[] ids) {
        return this.directoryMapper.deleteByIds(ids);
    }

    @Override
    public List<Directory> queryListByName(Map<String, Object> params) {
        return this.directoryMapper.queryListByName(params);
    }

    @Override
    public List<Directory> findAllDirectory() {
        return this.directoryMapper.findAllDirectory();
    }

    @Override
    public List<Directory> findRootDirectory() {
        return this.directoryMapper.findRootDirectory();
    }

    @Override
    public List<Directory> findRootDirectoryList(Map<String, Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()));
        return this.directoryMapper.findRootDirectoryList(params);
    }

    @Override
    public List<Directory> queryListByParentId(Long id) {
        return this.directoryMapper.queryListByParentId(id);
    }

    @Override
    public List<Directory> queryListById(Long id) {
        return this.directoryMapper.queryListById(id);
    }

    @Override
    public List<Directory> queryByParentName(Map<String, Object> params) {
        return this.directoryMapper.queryByParentName(params);
    }


}
