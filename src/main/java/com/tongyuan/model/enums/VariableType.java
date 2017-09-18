package com.tongyuan.model.enums;

/**
 * Created by Administrator on 2017-6-29.
 */
public enum VariableType {
    Int("Integer","Int"),Float("Float","Float"),Real("Real","Double")
    ,String("String","String"),Boolean("Boolean","Boolean"),intGroup("Integer[]","Integer[]"),floatGroup("Float[]","Float[]")
    ,doubleGroup("Real[]","Double[]"),stringGroup("String[]","String[]"),File("File","File");

    private VariableType(String key, String value) {
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
            for (VariableType e : VariableType.values()) {
                if (e.getKey().equals(key)) {
                    return e.getValue();
                }
            }
        }
        return null;
    }
}
