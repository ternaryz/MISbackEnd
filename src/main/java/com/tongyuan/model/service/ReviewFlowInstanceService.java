package com.tongyuan.model.service;

import com.tongyuan.exception.result.ResultInfo;
import com.tongyuan.model.domain.ReviewFlowInstance;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/6/24.
 */

public interface ReviewFlowInstanceService {
    int add(ReviewFlowInstance reviewFlowInstance);
    ReviewFlowInstance queryByInstanceId(Long instanceId);
    int deleteByInstanceIds(String[] instanceIds);
    int setStatus(Map<String, Object> map); //返回修改的数目，返回1修改成功，返回0修改失败。map的key为status和instanceId
    List<ReviewFlowInstance> queryByNameAndStatus(Map<String, Object> map);
    int updateTime(Map<String, Object> map);

    Long startInstance(Long modelId);
}
