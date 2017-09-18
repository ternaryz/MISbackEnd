package com.tongyuan.model.service.impl;

import com.tongyuan.model.dao.SimulationResultMapper;
import com.tongyuan.model.domain.SimulationResult;
import com.tongyuan.model.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/7/12.
 */
@Service
@SuppressWarnings("SpringJavaAutowiringInspection")
public class SimulationServiceImpl implements SimulationService {
    @Autowired
    private SimulationResultMapper simulationResultMapper;

    @Override
    public int add(SimulationResult simulationResult) {
        return simulationResultMapper.add(simulationResult);
    }

    @Override
    public int deleteByIdS(String[] ids) {
        return simulationResultMapper.deleteByIds(ids);
    }

    @Override
    public List<SimulationResult> queryByName(Map<String,Object> map) {
        return simulationResultMapper.queryByName(map);
    }
}
