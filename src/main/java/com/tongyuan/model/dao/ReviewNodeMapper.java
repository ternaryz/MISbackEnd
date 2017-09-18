package com.tongyuan.model.dao;

import com.tongyuan.model.domain.ReviewNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/7/2.
 */
@Mapper
public interface ReviewNodeMapper {
    int add(ReviewNode reviewNode);
    List<ReviewNode> queryByTemplateId(Map<String, Object> map);
    ReviewNode queryByNodeId(Long nodeId);
    int reset(Long templateId);
}
