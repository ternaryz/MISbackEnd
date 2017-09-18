package com.tongyuan.model.dao;

import com.tongyuan.model.domain.ReviewModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/7/3.
 */
@Mapper
public interface ReviewModelMapper {
    List<ReviewModel> queryAll(Map<String, Object> map);
    ReviewModel queryByModelId(Long modelId);
    List<ReviewModel> queryByModelName(Map<String, Object> map);
}
