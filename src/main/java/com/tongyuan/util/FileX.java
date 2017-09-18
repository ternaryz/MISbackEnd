package com.tongyuan.util;

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017-7-5.
 */
@Component
public class FileX {
    //对压缩文件进行读取
    public Map<String,Object> readZip(String fileDir) throws IOException {
        Map<String , Object> dataMap = new HashMap<>();
        Integer g[] = new Integer [60];

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        FileInputStream fin  = null;
        try {
            fin = new FileInputStream(fileDir);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int read;
        byte[] bytes=new byte[1024];
        try {
            while((read = fin.read(bytes)) >0){
                out.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        fin.close();

        bytes = out.toByteArray(); // 这就是全部的字节数组了。
        out.close();
        long byteslength = bytes.length;
        System.out.println(byteslength);
        dataMap.put("byteslength",byteslength);
        dataMap.put("bytes",bytes);
        return dataMap;
    }
}
