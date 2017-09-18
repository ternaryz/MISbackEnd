package com.tongyuan.model.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.tongyuan.model.domain.*;
import com.tongyuan.model.service.*;
import com.tongyuan.tools.CurdUtil;
import com.tongyuan.tools.ErrorMsg;
import com.tongyuan.model.domain.enums.ExceptionMsg;
import com.tongyuan.model.domain.result.Response;
import com.tongyuan.model.domain.result.ResponseData;
import com.tongyuan.tools.DateUtil;
import com.tongyuan.tools.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/6/24.
 */
@Controller
@RequestMapping("/api/reviewFlowInstance")
public class ReviewFlowInstanceController extends BaseController{
    @Autowired
    private ReviewFlowInstanceService reviewFlowInstanceService;
    @Autowired
    private StatusChangeService statusChangeService;
    @Autowired
    private ReviewModelService reviewModelService;


    @RequestMapping("")
    public String reviewFlowInstance(){
        return "review-flow-instance";
    }
    /**
     * 新增一个流程实例
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/addReviewFlowInstance",method = RequestMethod.POST)
    public void add(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Long modelId = Long.valueOf(request.getParameter("modelId"));
        Long instanceId = reviewFlowInstanceService.startInstance(modelId);
        //将nodeInstance表中的第一个节点标志位从1变为2
        statusChangeService.updateNextStatus(instanceId,"1");
        JSONObject jo = new JSONObject();
        jo.put("message","新增审签流程成功！");
        jo.put("flag",true);
        ServletUtil.createSuccessResponse(200, jo, response);
    }

    /**
     * 按照实例名字和实例状态查询，返回实例对象list 的json数据
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/queryByNameAndStatus",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public void queryByNameAndStatus(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数
        String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数
        String name = request.getParameter("flowInstanceName");
        String tempStaus = request.getParameter("flowInstanceStatus");
        Byte status = null;
        if(tempStaus!=null && !(tempStaus.equals(""))){
            status = Byte.valueOf(tempStaus);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", page);
        map.put("rows", rows);
        map.put("instanceName", name);
        map.put("status", status);

        List<ReviewFlowInstance> reviewFlowInstances = reviewFlowInstanceService.queryByNameAndStatus(map);
        PageInfo<ReviewFlowInstance> pageInfo = new PageInfo<ReviewFlowInstance>(reviewFlowInstances);
        JSONObject jo = new JSONObject();
        jo.put("rows", reviewFlowInstances);
        jo.put("total", pageInfo.getPages());
        jo.put("records", pageInfo.getTotal());
        ServletUtil.createSuccessResponse(200, jo, response);
    }

    /**
     * 根据id删除流程实例
     * @param request
     * @param response
     */
    @RequestMapping(value="/deleteByInstanceId",method= RequestMethod.POST)
    @ResponseBody
    public void deleteReviewFlowInstance(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String instanceIds = request.getParameter("instanceIds");
        int index = reviewFlowInstanceService.deleteByInstanceIds(instanceIds.split(","));

        Map<String, Object> map = new HashMap<String, Object>();
        map = CurdUtil.curd(index);
        ServletUtil.createSuccessResponse(200, map, response);
    }

    /**
     * 按模型名字查询模型
     */
    @RequestMapping(value="/queryByModelName",method = RequestMethod.POST)
    @ResponseBody
    public void queryByModelName(HttpServletRequest request, HttpServletResponse response){
        String modelName = request.getParameter("modelName");
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        Map<String,Object> map =new  HashMap<String,Object>();
        map.put("page",page);
        map.put("rows",rows);
        map.put("modelName",modelName);
        List<ReviewModel> reviewModels = reviewModelService.queryByModelName(map);
        PageInfo<ReviewModel> pageInfo = new PageInfo<ReviewModel>(reviewModels);
        JSONObject jo = new JSONObject();
        jo.put("rows", reviewModels);
        jo.put("total", pageInfo.getPages());
        jo.put("records", pageInfo.getTotal());
        ServletUtil.createSuccessResponse(200, jo, response);
    }

}
