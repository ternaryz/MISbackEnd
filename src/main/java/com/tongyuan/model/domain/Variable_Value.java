package com.tongyuan.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 	变量值表
 * Created by xieyx on 2017-6-17.
 */
@Entity
public class Variable_Value {
    @Id
    @GeneratedValue
    @Column(name = "Id", unique = true, nullable = false)
    //Id
    private long id;

    @Column(name = "VariableId", nullable = false)
    //变量Id
    private long variableId;

    @Column(name = "SimulationId", nullable = false)
    //仿真任务Id
    private long simulationId;

    @Column(name = "Value", length = 255)
    //变量
    private String value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVariableId() {
        return variableId;
    }

    public void setVariableId(long variableId) {
        this.variableId = variableId;
    }

    public long getSimulationId() {
        return simulationId;
    }

    public void setSimulationId(long simulationId) {
        this.simulationId = simulationId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
