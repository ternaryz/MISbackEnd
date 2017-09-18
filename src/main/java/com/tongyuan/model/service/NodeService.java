package com.tongyuan.model.service;

import com.tongyuan.model.domain.ReviewNode;

import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/7/1.
 */
public interface NodeService {
    int add(ReviewNode node);
    List<ReviewNode> queryByTemplateId(Map<String, Object> map);
    ReviewNode queryByNodeId(Long nodeId);
    int reset(Long templateId);
}
