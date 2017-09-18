package com.tongyuan.model.domain;

import javax.persistence.*;
import java.util.Date;


/**
 * 模型目录表
 * Created by xieyx on 2017-6-17.
 */
@Entity
public class Directory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    //目录ID
    private long id;

    @Column( nullable = false, length = 128)
    //目录名称
    private String name;

    @Column( length = 255)
    //目录描述
    private String description;

    @Column
    //父目录ID
    private long parentId;

    @Column
    //创建时间
    private Date createTime;

    @Column
    //最后修改时间
    private Date lastUpdateTime;

    @Column
    //删除标记为
    private String absoluteAddress;

    @Column
    //删除标记为
    private String relativeAddress;

    @Column
    //删除标记为
    private Boolean isDeleted;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getAbsoluteAddress() {
        return absoluteAddress;
    }

    public void setAbsoluteAddress(String absoluteAddress) {
        this.absoluteAddress = absoluteAddress;
    }

    public String getRelativeAddress() {
        return relativeAddress;
    }

    public void setRelativeAddress(String relativeAddress) {
        this.relativeAddress = relativeAddress;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}

