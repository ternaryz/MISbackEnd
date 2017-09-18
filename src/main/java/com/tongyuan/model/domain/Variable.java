package com.tongyuan.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 变量表
 * Created by xieyxon 2017-6-17.
 */
@Entity
public class Variable {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    //仿真任务Id
    private long  id;

    @Column(name = "modelId", nullable = false)
    //所属模型Id
    private long modelId;

    @Column(name = "name", length = 128)
    //类型名
    private String name;

    @Column(name = "type")
    //变量类型
    private String type;

    @Column(name = "defaultValue", length = 255)
    //默认值
    private String defaultValue;

    @Column(name = "units", length = 255)
    //变量单位
    private String units;

    @Column(name = "lowerBound")
    //变量上界
    private String lowerBound;

    @Column(name = "upperBound")
    //变量下界
    private String upperBound;

    @Column(name = "IsParam", nullable = false)
    //针对Modelica模型，是参数还是变量，参数为1，变量为0
    private int IsParam;

    @Column(name = "IsInput")
    //针对其他模型，是否为输入变量，是为1，不是为0
    private int IsInput;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(String lowerBound) {
        this.lowerBound = lowerBound;
    }

    public String getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(String upperBound) {
        this.upperBound = upperBound;
    }

    public int getIsParam() {
        return IsParam;
    }

    public void setIsParam(int isParam) {
        IsParam = isParam;
    }

    public int getIsInput() {
        return IsInput;
    }

    public void setIsInput(int isInput) {
        IsInput = isInput;
    }
}
