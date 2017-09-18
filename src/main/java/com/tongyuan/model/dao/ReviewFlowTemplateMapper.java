package com.tongyuan.model.dao;

import com.tongyuan.model.domain.ReviewFlowTemplate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/6/24.
 */
@Mapper
public interface ReviewFlowTemplateMapper {
    int add(ReviewFlowTemplate reviewFlowTemplate);
    List<ReviewFlowTemplate> queryByName(Map<String, Object> map);
    ReviewFlowTemplate queryById(Long templateId);
    int delete(String[] templateIds);
    int changeAlreadyConfig(Long templateId);
    int updateTime(Map<String, Object> map);
    ReviewFlowTemplate getTemplateByDefault();
}
