package com.tongyuan.model.service;

import com.tongyuan.model.domain.Model;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-6-19.
 */
public interface ModelService {
    int add(Model model);
    int update(Model model);
    int deleteByIds(String[] ids);
    public List<Model> queryModelList(Map<String, Object> params);
     Model queryModelByName(String name);
    public List<Model> findAllModel();
    public List<Model> findRootModel();
    public List<Model> vagueSearchByName(Map<String, Object> params);
    //model解析文件目录，插入数据库
  //  public void insertModelData(String modelDir,String modelName,String modelPath,String description);
}
