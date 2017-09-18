package com.tongyuan.pageModel;

/**
 * Created by Administrator on 2017-8-16.
 */
public class DirectoryModel {
    //模型层次结构id
     private long id;
     //模型层次结构name
    private String name;
    //模型层次结构parentid
    private String parentId;

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
