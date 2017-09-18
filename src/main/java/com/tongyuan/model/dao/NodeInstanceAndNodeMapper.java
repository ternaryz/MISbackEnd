package com.tongyuan.model.dao;

import com.tongyuan.model.domain.CheckorPage;
import com.tongyuan.model.domain.ReviewNodeInstance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/7/9.
 */
@Mapper
public interface NodeInstanceAndNodeMapper {
    List<ReviewNodeInstance> queryAfterAgree(Map<String, Object> map);
    //审核者同意后，map中有nodeInstance.instanceId和node.sequence
    List<CheckorPage> queryByReviewer(Map<String, Object> map);
    //呈现给审核者的页面，map中有node.userId
}
