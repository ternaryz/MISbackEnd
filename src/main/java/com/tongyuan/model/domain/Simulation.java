package com.tongyuan.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 模型仿真表
 * Created by xieyx on 2017-6-17.
 */
@Entity
public class Simulation {
    @Id
    @GeneratedValue
    @Column(name = "Id", unique = true, nullable = false)
    //仿真任务Id
    private Long id;

    @Column(name = "ModelId")
    //模型Id
    private Long modelId;

    @Column(name = "ModelVersion", length = 255)
    //模型版本
    private String modelVersion;

    @Column(name = "UserId" , length = 128)
    //用户Id
    private Long userId;

    @Column(name = "Name", length = 128)
    //仿真任务名称
    private String name;

    @Column(name = "Description" , length = 255)
    //仿真任务描述
    private String description;

    @Column
    //起始时间
    private Date StartTime;

    @Column
    //终止时间
    private Date EndTime;

    @Column(name = "SimStep")
    //仿真步长
    private Double simStep;

    @Column(name = "Deviation")
    //仿真算法
    private int deviation;

    @Column(name = "ResultFilePath" , length = 255)
    //仿真结果路径
    private String resultFilePath;

    @Column(name = "TemplateFileUrl" , length = 255)
    //Web端文件路径
    private String templateFileUrl;

    @Column
    //仿真创建时间
    private Date createTime;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date startTime) {
        StartTime = startTime;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date endTime) {
        EndTime = endTime;
    }

    public Double getSimStep() {
        return simStep;
    }

    public void setSimStep(Double simStep) {
        this.simStep = simStep;
    }

    public int getDeviation() {
        return deviation;
    }

    public void setDeviation(int deviation) {
        this.deviation = deviation;
    }

    public String getResultFilePath() {
        return resultFilePath;
    }

    public void setResultFilePath(String resultFilePath) {
        this.resultFilePath = resultFilePath;
    }

    public String getTemplateFileUrl() {
        return templateFileUrl;
    }

    public void setTemplateFileUrl(String templateFileUrl) {
        this.templateFileUrl = templateFileUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
