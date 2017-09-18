package com.tongyuan.model.controller;

import com.alibaba.fastjson.JSONObject;
import com.tongyuan.model.DownLoad.SplitNumber;
import com.tongyuan.tools.ServletUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Y470 on 2017/7/24.
 */
@Controller
@RequestMapping("/load")
public class DownloadController {
    @RequestMapping("")
    public String load(){
        return "load";
    }

    @RequestMapping(value="/download",method = RequestMethod.POST)
    public void download(HttpServletRequest request, HttpServletResponse response)throws Exception{
        URL url = new URL(request.getParameter("url"));
        String dest = request.getParameter("dest");
        int threadNum = Integer.valueOf(request.getParameter("threadNum"));
        String fileName = request.getParameter("fileName");
        SplitNumber splitNumber = new SplitNumber(url,dest,threadNum,fileName);
        splitNumber.splitProcess();

        JSONObject result=new JSONObject();
        result.put("message","下载成功！");
        result.put("flag",true);
        ServletUtil.createSuccessResponse(200, result, response);
    }
}
