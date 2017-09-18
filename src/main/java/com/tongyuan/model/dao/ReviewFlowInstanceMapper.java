package com.tongyuan.model.dao;

import com.tongyuan.model.domain.ReviewFlowInstance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/6/24.
 */
@Mapper
public interface ReviewFlowInstanceMapper {
    int add(ReviewFlowInstance reviewFlowInstance);
    ReviewFlowInstance queryByInstanceId(Long instanceId);
    int deleteByInstanceIds(String[] instanceIds);
    int setStatus(Map<String, Object> map);
    List<ReviewFlowInstance> queryByNameAndStatus(Map<String, Object> map);
    int updateTime(Map<String, Object> map);
}
