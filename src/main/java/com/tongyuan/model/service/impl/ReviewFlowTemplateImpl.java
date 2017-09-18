package com.tongyuan.model.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongyuan.model.dao.ReviewFlowTemplateMapper;
import com.tongyuan.model.domain.ReviewFlowTemplate;
import com.tongyuan.model.service.ReviewFlowTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/6/29.
 */
@Service
@SuppressWarnings("SpringJavaAutowiringInspection")
public class ReviewFlowTemplateImpl implements ReviewFlowTemplateService {
    @Autowired
    private ReviewFlowTemplateMapper reviewFlowTemplateMapper;

    @Override
    public int add(ReviewFlowTemplate reviewFlowTemplate){
        return reviewFlowTemplateMapper.add(reviewFlowTemplate);
    };

    @Override
    public List<ReviewFlowTemplate> queryByName(Map<String,Object> map){
        PageHelper.startPage(Integer.parseInt(map.get("page").toString()),Integer.parseInt(map.get("rows").toString()));
        return reviewFlowTemplateMapper.queryByName(map);
    };

    @Override
    public ReviewFlowTemplate queryById(Long templateId){
        return reviewFlowTemplateMapper.queryById(templateId);
    };

    @Override
    public int delete(String[] templateIds){
        return reviewFlowTemplateMapper.delete(templateIds);
    };

    @Override
    public int changeAlreadyConfig(Long instanceId){
        return reviewFlowTemplateMapper.changeAlreadyConfig(instanceId);
    }
    @Override
    public int updateTime(Map<String,Object> map){
        return reviewFlowTemplateMapper.updateTime(map);
    }

    @Override
    public ReviewFlowTemplate getTemplateByDefault(){
        return reviewFlowTemplateMapper.getTemplateByDefault();
    }
}
