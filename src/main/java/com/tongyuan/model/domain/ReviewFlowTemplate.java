package com.tongyuan.model.domain;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Y470 on 2017/6/19.
 */
@Entity
public class ReviewFlowTemplate {
    @Id
    @GeneratedValue
    private Long templateId;
    @Column
    private String templateName;
    @Column
    private String description;
    @Column
    private Timestamp createTime;
    @Column
    private Timestamp lastUpdateTime;
    @Column
    private Long userId;
    @Column
    private Boolean defaultTemplate;
    @Column
    private Boolean alreadyConfig;


    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getDefaultTemplate() {
        return defaultTemplate;
    }

    public void setDefaultTemplate(Boolean defaultTemplate) {
        this.defaultTemplate = defaultTemplate;
    }

    public Boolean getAlreadyConfig() {
        return alreadyConfig;
    }

    public void setAlreadyConfig(Boolean alreadyConfig) {
        this.alreadyConfig = alreadyConfig;
    }

    @Override
    public String toString() {
        return "ReviewFlowTemplate{" +
                "templateId=" + templateId +
                ", templateName='" + templateName + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", userId=" + userId +
                ", defaultTemplate=" + defaultTemplate +
                ", alreadyConfig=" + alreadyConfig +
                '}';
    }
}
