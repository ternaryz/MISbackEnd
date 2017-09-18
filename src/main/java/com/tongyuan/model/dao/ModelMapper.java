package com.tongyuan.model.dao;

import com.tongyuan.model.domain.Model;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-6-17.
 */
@Mapper
public interface ModelMapper {
    int add(Model model);
    int update(Model model);
    int deleteByIds(String[] ids);
    public List<Model> queryModelList(Map<String, Object> params);
    public Model queryModelByName(String name);
    public List<Model> findAllModel();
    public List<Model> findRootModel();
    public List<Model> vagueSearchByName(Map<String, Object> params);
}
