package com.tongyuan.model.enums;

/**
 * Created by Administrator on 2017-6-28.
 */
public enum ModelType {
    Modelica("Modelica","Modelica"),Ansys("Ansys","Ansys"),Nastran("Nastran","Nastran")
    ,Fluent("Fluent","Fluent");

    private ModelType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private String key;

    private String value;

    public String getKey() {
        return key;
    }


    public void setKey(String key) {
        this.key = key;
    }


    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
    }


    public static String getValueByKey(String key) {
        if (null != key) {
            for (ModelType e : ModelType.values()) {
                if (e.getKey().equals(key)) {
                    return e.getValue();
                }
            }
        }
        return null;
    }
}
