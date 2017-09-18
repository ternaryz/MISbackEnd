package com.tongyuan.model.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.tongyuan.exception.SqlNumberException;
import com.tongyuan.model.domain.CheckorPage;
import com.tongyuan.model.domain.ReviewFlowInstance;
import com.tongyuan.model.domain.ReviewModel;
import com.tongyuan.model.domain.ReviewNodeInstance;
import com.tongyuan.model.service.CheckorService;
import com.tongyuan.model.service.ReviewFlowInstanceService;
import com.tongyuan.model.service.ReviewModelService;
import com.tongyuan.model.service.StatusChangeService;
import com.tongyuan.tools.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by Y470 on 2017/7/7.
 */
@Controller
@RequestMapping("/api/checkor")
public class CheckorController extends BaseController{
    @Autowired
    private StatusChangeService statusChangeService;
    @Autowired
    private CheckorService checkorService;
    @Autowired
    private ReviewFlowInstanceService reviewFlowInstanceService;
    @Autowired
    private ReviewModelService reviewModelService;

    @RequestMapping("")
    public String checkor(){
        return "checkor";
    }

    @PostMapping(value="/agree")
    public void agree(HttpServletRequest request, HttpServletResponse response) throws SqlNumberException{
        Long id = Long.valueOf(request.getParameter("id"));
        statusChangeService.agree(id);

        JSONObject result = new JSONObject();
        result.put("message","操作成功!");
        result.put("flag",true);
        ServletUtil.createSuccessResponse(200,result,response);
    }

    @PostMapping(value="/disagree")
    public void disagree(HttpServletRequest request, HttpServletResponse response){
        Long id = Long.valueOf(request.getParameter("id"));
        statusChangeService.disagree(id);

        JSONObject result = new JSONObject();
        result.put("message","操作成功!");
        result.put("flag",true);
        ServletUtil.createSuccessResponse(200,result,response);
    }

    //展示给审签者的review_node_instance列表
    @PostMapping(value="/queryByReviewer")
    public void queryByReviewer(HttpServletRequest request, HttpServletResponse response){
        //Long userId = getUserId();
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("page",page);
        map.put("rows",rows);
        //测试用
        Long userId = 4L;
        map.put("userId",userId);

        List<CheckorPage> reviewNodeInstances = checkorService.queryByReviewer(map);
        PageInfo<CheckorPage> pageInfo = new PageInfo<CheckorPage>(reviewNodeInstances);
        JSONObject jo = new JSONObject();
        jo.put("rows", reviewNodeInstances);
        jo.put("total", pageInfo.getPages());
        jo.put("records", pageInfo.getTotal());
        ServletUtil.createSuccessResponse(200, jo, response);
    }

    //点击详细信息，可查看审签实例对应的模型信息
    @PostMapping(value="/showModelDetails")
    public void showModelDetails(HttpServletRequest request, HttpServletResponse response){
        Long instanceId = Long.valueOf(request.getParameter("instanceId"));
        ReviewFlowInstance reviewFlowInstance=reviewFlowInstanceService.queryByInstanceId(instanceId);
        Long modelId = reviewFlowInstance.getModelId();
        ReviewModel reviewModel = reviewModelService.queryByModelId(modelId);

        JSONObject js = new JSONObject();
        js.put("reviewModel",reviewModel);
        ServletUtil.createSuccessResponse(200,js,response);
    }
}
