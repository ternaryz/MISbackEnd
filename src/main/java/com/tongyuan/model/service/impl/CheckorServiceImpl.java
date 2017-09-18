package com.tongyuan.model.service.impl;

import com.tongyuan.model.dao.NodeInstanceAndNodeMapper;
import com.tongyuan.model.domain.CheckorPage;
import com.tongyuan.model.domain.ReviewNodeInstance;
import com.tongyuan.model.service.CheckorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/7/9.
 */

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
class CheckorServiceImpl implements CheckorService {
    @Autowired
    private NodeInstanceAndNodeMapper nodeInstanceAndNodeMapper;

    @Override
    public List<ReviewNodeInstance> queryAfterAgree(Map<String, Object> map) {
        return nodeInstanceAndNodeMapper.queryAfterAgree(map);
    }

    @Override
    public List<CheckorPage> queryByReviewer(Map<String,Object> map) {
        return nodeInstanceAndNodeMapper.queryByReviewer(map);
    }
}



