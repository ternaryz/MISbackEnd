package com.tongyuan.model.service.impl;

import com.tongyuan.model.dao.VariableMapper;
import com.tongyuan.model.domain.Variable;
import com.tongyuan.model.service.VariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017-6-29.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class VariableServiceImpl implements VariableService {
    @Autowired
    VariableMapper variableMapper;

    @Override
    public int add(Variable variable) {
        return this.variableMapper.add(variable);
    }

    @Override
    public int update(Variable variable) {
        return this.variableMapper.update(variable);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.variableMapper.deleteByIds(ids);
    }
}
