package com.tongyuan.model.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.tongyuan.model.domain.SimulationResult;
import com.tongyuan.model.service.SimulationService;
import com.tongyuan.tools.ResultUtil;
import com.tongyuan.tools.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/7/12.
 */
@RequestMapping("/simulation")
@Controller
public class SimulationResultController extends BaseController{
    @Autowired
    private SimulationService simulationService;

    @RequestMapping
    public String simulationResult(){
        return "simulation-result";
    }

    /**
     *本方法是提供的接口，上传xml文件后可以调用这个接口完成存入数据库库的功能
     */
    @PostMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response) {
        String algorithm = request.getParameter("algorithm");
        String descripition = request.getParameter("description");
        String endTimeTemp = request.getParameter("endTime");
        Timestamp endTime = Timestamp.valueOf(endTimeTemp);
        String modelIdTemp = request.getParameter("modelId");
        Long modelId = Long.valueOf(modelIdTemp);
        String modelVersion = request.getParameter("modelVersion");
        String simStepTemp = request.getParameter("simStep");
        Double simStep = Double.valueOf(simStepTemp);
        String simuName = request.getParameter("simuName");
        String startTimeTemp = request.getParameter("startTime");
        Timestamp startTime = Timestamp.valueOf(startTimeTemp);
        Long userId = getUserId();

        SimulationResult simulationResult = new SimulationResult();
        simulationResult.setAlgorithm(algorithm);
        simulationResult.setDescription(descripition);
        simulationResult.setEndTime(endTime);
        simulationResult.setUserId(userId);
        simulationResult.setModelVersion(modelVersion);
        simulationResult.setSimStep(simStep);
        simulationResult.setStartTime(startTime);
        simulationResult.setModelId(modelId);
        simulationResult.setSimuName(simuName);

        int index = simulationService.add(simulationResult);
        JSONObject jo = new JSONObject();
        if(index>0){
            jo.put("message","增加仿真结果成功！");
            jo.put("flag",true);
        }else{
            jo.put("message","增加仿真结果失败！");
            jo.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200,jo,response);
    }

    @PostMapping("/deleteByIds")
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response){
        String ids = request.getParameter("ids");
        int index = simulationService.deleteByIdS(ids.split(","));
        JSONObject jo = new JSONObject();
        if(index>0){
            jo.put("flag",true);
            jo.put("message","删除仿真结果成功！");
        }else{
            jo.put("message","删除仿真结果失败！");
            jo.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200,jo,response);
    }

    @PostMapping("/queryByName")
    @ResponseBody
    public void queryByName(HttpServletRequest request, HttpServletResponse response){
        String simuName = request.getParameter("simuName");
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        Map<String,Object> map = new HashMap<>();
        map.put("simuName",simuName);
        map.put("page",page);
        map.put("rows",rows);
        List<SimulationResult> simulationResults = simulationService.queryByName(map);
        PageInfo<SimulationResult> pageInfo = new PageInfo<>(simulationResults);
        JSONObject jo = new JSONObject();
        jo.put("rows",simulationResults);
        jo.put("total",pageInfo.getPages());
        jo.put("records",pageInfo.getTotal());
        ServletUtil.createSuccessResponse(200,jo,response);
    }

}
