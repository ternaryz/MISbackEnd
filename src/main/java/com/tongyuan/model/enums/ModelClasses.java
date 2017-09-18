package com.tongyuan.model.enums;

/**
 * Created by xieyx on 2017-6-28.
 */
public enum ModelClasses {
       //modelica受限类型
    Calss("calss","Calss"),Model("model","Model"),Connector("connector","Connector")
    ,Record("record","Record"),Block("block","Block"),Type("type","Type"),Function("function","Function"),Package("package","Package");

    private ModelClasses(String key, String value) {
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
            for (ModelClasses e : ModelClasses.values()) {
                if (e.getKey().equals(key)) {
                    return e.getValue();
                }
            }
        }
        return null;
    }
}
