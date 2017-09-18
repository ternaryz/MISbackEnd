package com.tongyuan.model.service;

import com.tongyuan.model.domain.NodeHistory;

import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/7/24.
 */
public interface NodeHistoryService {
    List<NodeHistory> query(Map<String, Object> map);
    int add(Map<String, Object> map);
}
