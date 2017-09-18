package com.tongyuan.model.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongyuan.model.domain.DetailPage;
import com.tongyuan.model.domain.ReviewNodeInstance;
import com.tongyuan.model.service.NodeInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/9/6.
 */
@Controller
@RequestMapping("/api/nodeInstance")
public class ReviewNodeInstanceController {
    @Autowired
    private NodeInstanceService nodeInstanceService;

    @PostMapping("/details")
    @ResponseBody
    public JSONObject details(@RequestParam("instanceId")Long instanceId){
        //Map<String,Object> map = new HashMap<>();
        List<DetailPage> reviewNodeInstanceList = nodeInstanceService.details(instanceId);
        JSONObject jo = new JSONObject();
        jo.put("records",reviewNodeInstanceList);
        return jo;
    }
}
