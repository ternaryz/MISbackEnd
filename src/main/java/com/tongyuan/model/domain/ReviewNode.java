package com.tongyuan.model.domain;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Y470 on 2017/7/1.
 */
@Entity
public class ReviewNode {
    @Id
    @GeneratedValue
    private Long nodeId;
    @Column
    private String nodeName;
    @Column
    private String description;
    @Column
    private Long templateId;
    @Column
    private Long userId;
    @Column
    private String sequence;

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "ReviewNode{" +
                "nodeId=" + nodeId +
                ", nodeName='" + nodeName + '\'' +
                ", description='" + description + '\'' +
                ", templateId=" + templateId +
                ", userId=" + userId +
                ", sequence=" + sequence +
                '}';
    }
}
