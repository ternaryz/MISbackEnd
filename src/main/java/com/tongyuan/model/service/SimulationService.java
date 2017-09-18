package com.tongyuan.model.service;

import com.tongyuan.model.domain.SimulationResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/7/12.
 */
public interface SimulationService {
    int add(SimulationResult simulationResult);
    int deleteByIdS(String[] ids);
    List<SimulationResult> queryByName(Map<String, Object> map);
}
