package com.tongyuan.model.service.impl;

import com.tongyuan.model.dao.NodeHistoryMapper;
import com.tongyuan.model.domain.NodeHistory;
import com.tongyuan.model.service.NodeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/7/24.
 */
@Service
@SuppressWarnings("SpringJavaAutowiringInspection")
public class NodeHistoryServiceImpl implements NodeHistoryService{
    @Autowired
    private NodeHistoryMapper nodeHistoryMapper;

    @Override
    public List<NodeHistory> query(Map<String, Object> map) {
        return nodeHistoryMapper.query(map);
    }

    @Override
    public int add(Map<String, Object> map) {
        return nodeHistoryMapper.add(map);
    }
}
