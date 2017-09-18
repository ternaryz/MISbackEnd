package com.tongyuan.model.service;

import com.tongyuan.model.domain.DetailPage;
import com.tongyuan.model.domain.ReviewNodeInstance;

import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/7/6.
 */
public interface NodeInstanceService {
    int add(ReviewNodeInstance reviewNodeInstance);
    ReviewNodeInstance queryById(Long id);
    int updateStatus(Map<String, Object> map);  //map中有 id、 status（3或4）
    int updateTime(Map<String, Object> map);

    List<DetailPage> details(Long instanceId);
}
