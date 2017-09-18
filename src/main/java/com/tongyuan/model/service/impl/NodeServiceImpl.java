package com.tongyuan.model.service.impl;

import com.tongyuan.model.dao.ReviewNodeMapper;
import com.tongyuan.model.domain.ReviewNode;
import com.tongyuan.model.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/7/1.
 */
@Service
@SuppressWarnings("SpringJavaAutowiringInspection")
public class NodeServiceImpl implements NodeService{
    @Autowired
    private ReviewNodeMapper reviewNodeMapper;

    @Override
    public int add(ReviewNode reviewNode){
        return reviewNodeMapper.add(reviewNode);
    }

    @Override
    public List<ReviewNode> queryByTemplateId(Map<String,Object> map){
        return reviewNodeMapper.queryByTemplateId(map);
    }
    @Override
    public ReviewNode queryByNodeId(Long nodeId){
        return reviewNodeMapper.queryByNodeId(nodeId);
    }

    @Override
    public int reset(Long templateId){
        return reviewNodeMapper.reset(templateId);
    }
}
