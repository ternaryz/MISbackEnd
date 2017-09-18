package com.tongyuan.model.service;

import com.tongyuan.model.domain.ReviewModel;

import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/7/3.
 */
public interface ReviewModelService {
    List<ReviewModel> queryAll(Map<String, Object> map);
    ReviewModel queryByModelId(Long modelId);
    List<ReviewModel> queryByModelName(Map<String, Object> map);
}
