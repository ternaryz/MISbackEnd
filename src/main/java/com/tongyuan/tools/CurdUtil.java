package com.tongyuan.tools;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Y470 on 2017/8/17.
 */
public class CurdUtil {
    public static Map<String,Object> curd(int index){
        Map<String,Object> map = new HashMap<>();
        if(index>0){
            map.put("message","操作成功！");
            map.put("flag",true);
        }else{
            map.put("message","操作失败！");
            map.put("flag",false);
        }
        return map;
    }
}
