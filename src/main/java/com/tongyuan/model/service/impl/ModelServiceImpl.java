package com.tongyuan.model.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongyuan.model.dao.ModelMapper;
import com.tongyuan.model.domain.Model;
import com.tongyuan.model.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-6-19.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class ModelServiceImpl implements ModelService{
    @Autowired
    ModelMapper modelMapper;


    @Override
    public int add(Model model) {
        return this.modelMapper.add(model);
    }

    @Override
    public int update(Model model) {
        return this.modelMapper.update(model);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.modelMapper.deleteByIds(ids);
    }

    @Override
    public List<Model> queryModelList(Map<String, Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()));
        return this.modelMapper.queryModelList(params);
    }

    @Override
    public Model queryModelByName(String name) {
        return this.modelMapper.queryModelByName(name);
    }

    @Override
    public List<Model> findAllModel() {
        return this.modelMapper.findAllModel();
    }

    @Override
    public List<Model> findRootModel() {
        return this.modelMapper.findRootModel();
    }

    @Override
    public List<Model> vagueSearchByName(Map<String, Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()));
        return this.modelMapper.vagueSearchByName(params);
    }


/*    @Override
    public void insertModelData(String modelDir, String modelName, String modelPath, String description) {
        //获取根目录
        String parentPath = ResourceUtil.getFileDriectory() + modelDir;
        ResourceUtil.getSubFile(parentPath.substring(0,
                parentPath.length() - 1),parentPath.substring(0,
                parentPath.length() - 1),description);
    }*/


}
