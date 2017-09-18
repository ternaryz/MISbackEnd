package com.tongyuan.model.dao;

import com.tongyuan.model.domain.SimulationResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/7/12.
 */
@Mapper
public interface SimulationResultMapper {
    List<SimulationResult> queryByName(Map<String, Object> map);
    int deleteByIds(String[] ids);
    int add(SimulationResult simulationResult);
}
