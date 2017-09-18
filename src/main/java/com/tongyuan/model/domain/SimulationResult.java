package com.tongyuan.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by Y470 on 2017/7/12.
 */
@Entity
public class SimulationResult {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Long modelId;
    @Column
    private String modelVersion;
    @Column
    private Long userId;
    @Column
    private String simuName;
    @Column
    private  String description;
    @Column
    private Timestamp startTime;
    @Column
    private Timestamp endTime;
    @Column
    private Double simStep;
    @Column
    private String algorithm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getModelVersion() {
        return modelVersion;
    }

    public void setModelVersion(String modelVersion) {
        this.modelVersion = modelVersion;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSimuName() {
        return simuName;
    }

    public void setSimuName(String simuName) {
        this.simuName = simuName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Double getSimStep() {
        return simStep;
    }

    public void setSimStep(Double simStep) {
        this.simStep = simStep;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
