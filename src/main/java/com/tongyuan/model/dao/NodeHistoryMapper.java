package com.tongyuan.model.dao;

import com.tongyuan.model.domain.NodeHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/7/24.
 */
@Mapper
public interface NodeHistoryMapper {
    int add(Map<String, Object> map);
    List<NodeHistory> query(Map<String, Object> map);
}
