package com.tongyuan.model.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.tongyuan.model.domain.ReviewFlowInstance;
import com.tongyuan.model.domain.ReviewFlowTemplate;
import com.tongyuan.model.domain.enums.ExceptionMsg;
import com.tongyuan.model.domain.result.Response;
import com.tongyuan.model.domain.result.ResponseData;
import com.tongyuan.model.service.ReviewFlowTemplateService;
import com.tongyuan.tools.CurdUtil;
import com.tongyuan.tools.DateUtil;
import com.tongyuan.tools.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/6/26.
 */
@Controller
@RequestMapping("/api/reviewFlowTemplate")
public class ReviewFlowTemplateController extends BaseController{
    @Autowired
    private ReviewFlowTemplateService reviewFlowTemplateService;

    @RequestMapping("")
    public String reviewFlowTemplate(){
        return "review-flow-template";
    }

    /**
     * 新增审签模板
     * @param request
     * @param response
     */
    @PostMapping("/addReviewFlowTemplate")
    public void add(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String templateName = request.getParameter("templateName");
        String description = request.getParameter("description");
        Long userId = getUserId();
        Boolean defaultTemplate = Boolean.valueOf(request.getParameter("defaultTemplate"));
        Timestamp timestamp = DateUtil.getCurrentTime();

        ReviewFlowTemplate reviewFlowTemplate = new ReviewFlowTemplate();
        reviewFlowTemplate.setTemplateName(templateName);
        reviewFlowTemplate.setDescription(description);
        reviewFlowTemplate.setUserId(userId);
        reviewFlowTemplate.setDefaultTemplate(defaultTemplate);
        reviewFlowTemplate.setCreateTime(timestamp);
        reviewFlowTemplate.setLastUpdateTime(timestamp);
        reviewFlowTemplate.setAlreadyConfig(false);

        int index = reviewFlowTemplateService.add(reviewFlowTemplate);
        Map<String, Object> map = new HashMap<String, Object>();
        map = CurdUtil.curd(index);
        ServletUtil.createSuccessResponse(200, map, response);
    }

    /**
     * 根据模板名字查询
     * @param request
     * @param response
     * @return
     */
    @PostMapping ("/queryReviewFlowTemplateByName")
    public void queryReviewFlowTemplateByName(
            HttpServletRequest request, HttpServletResponse response) throws  Exception{
        String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数
        String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数
        String name = request.getParameter("templateName");
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("rows", rows);
        map.put("templateName", name);

        List<ReviewFlowTemplate> reviewFlowTemplates = reviewFlowTemplateService.queryByName(map);
        PageInfo<ReviewFlowTemplate> pageInfo = new PageInfo<ReviewFlowTemplate>(reviewFlowTemplates);
        JSONObject jo = new JSONObject();
        //records 结果 pages总页数  total总个数
        jo.put("records", reviewFlowTemplates);
        jo.put("pages", pageInfo.getPages());
        jo.put("total", pageInfo.getTotal());
        ServletUtil.createSuccessResponse(200, jo, response);

    }

    /**
     * 根据模板id删除模板
     * @param request
     * @param response
     */
    @PostMapping("/deleteByTemplateId")
    public void  delete(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String templateIds = request.getParameter("templateIds");
        int index = reviewFlowTemplateService.delete(templateIds.split(","));
        Map<String, Object> map = new HashMap<String, Object>();
        map = CurdUtil.curd(index);
        ServletUtil.createSuccessResponse(200, map, response);
    }
}
