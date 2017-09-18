package com.tongyuan.model.service;

import com.tongyuan.model.domain.ReviewFlowTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/6/24.
 */

public interface ReviewFlowTemplateService {
    int add(ReviewFlowTemplate reviewFlowTemplate);
    List<ReviewFlowTemplate> queryByName(Map<String, Object> map);
    ReviewFlowTemplate queryById(Long templateId);
    int delete(String[] templateIds);
    int changeAlreadyConfig(Long templateId);
    int updateTime(Map<String, Object> map);
    ReviewFlowTemplate getTemplateByDefault();
}
