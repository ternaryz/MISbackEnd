package com.tongyuan.model.service.impl;

import com.tongyuan.model.dao.NodeInstanceMapper;
import com.tongyuan.model.domain.DetailPage;
import com.tongyuan.model.domain.ReviewNodeInstance;
import com.tongyuan.model.service.NodeInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/7/6.
 */
@Service
@SuppressWarnings("SpringJavaAutowiringInspection")
public class NodeInstanceServiceImpl implements NodeInstanceService {
    @Autowired
    private NodeInstanceMapper nodeInstanceMapper;

    @Override
    public int add(ReviewNodeInstance reviewNodeInstance){
        return nodeInstanceMapper.add(reviewNodeInstance);
    }
    @Override
    public ReviewNodeInstance queryById(Long id){
        return nodeInstanceMapper.queryById(id);
    }
    @Override
    public int updateStatus(Map<String,Object> map){
        return nodeInstanceMapper.updateStatus(map);
    }
    @Override
    public int updateTime(Map<String,Object> map) {
        return nodeInstanceMapper.updateTime(map);
    }

    @Override
    public List<DetailPage> details(Long instanceId){
        return nodeInstanceMapper.details(instanceId);
    }
}
