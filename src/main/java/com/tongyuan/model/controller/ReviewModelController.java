package com.tongyuan.model.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.tongyuan.model.domain.ReviewFlowTemplate;
import com.tongyuan.model.domain.ReviewModel;
import com.tongyuan.model.service.ReviewModelService;
import com.tongyuan.tools.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/7/3.
 */
@Controller
@RequestMapping("/reviewModel")
public class ReviewModelController extends BaseController{
    @Autowired
    private ReviewModelService reviewModelService;

    @PostMapping(value="/queryAll",produces = "application/json;charset=UTF-8")
    public void queryAll(HttpServletRequest request, HttpServletResponse response)throws Exception{
        String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数
        String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", page);
        map.put("rows", rows);

        List<ReviewModel> reviewModels = reviewModelService.queryAll(map);
        PageInfo<ReviewModel> pageInfo = new PageInfo<ReviewModel>(reviewModels);
        JSONObject jo = new JSONObject();
        jo.put("rows", reviewModels);
        jo.put("total", pageInfo.getPages());
        jo.put("records", pageInfo.getTotal());
        ServletUtil.createSuccessResponse(200, jo, response);
    }
}
