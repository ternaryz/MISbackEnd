package com.tongyuan.model.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.tongyuan.model.domain.ReviewFlowTemplate;
import com.tongyuan.model.domain.ReviewNode;
import com.tongyuan.model.domain.User;
import com.tongyuan.model.service.NodeHistoryService;
import com.tongyuan.model.service.NodeService;
import com.tongyuan.model.service.ReviewFlowTemplateService;
import com.tongyuan.model.service.UserService;
import com.tongyuan.tools.CurdUtil;
import com.tongyuan.tools.DateUtil;
import com.tongyuan.tools.ServletUtil;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/6/30.
 */
@Controller
@RequestMapping("/api/reviewNode")
public class ReviewNodeController extends BaseController{
    @Autowired
    private NodeService nodeService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReviewFlowTemplateService reviewFlowTemplateService;
    @Autowired
    private NodeHistoryService nodeHistoryService;

    /**
     * 用batchAdd方法替换掉addReviewNode方法
     * @param request
     * @param response
     */
    @PostMapping("/batchAdd")
    public void batchAdd(HttpServletRequest request, HttpServletResponse response){
        String reviewNodeNamesStr = request.getParameter("reviewNodeName");
        String descriptionStr = request.getParameter("description");
        String userNameStr = request.getParameter("userName");
        Long templateId = Long.valueOf(request.getParameter("templateId"));
        String[] reviewNodeNames = reviewNodeNamesStr.split(",");
        String[] description = descriptionStr.split(",");
        String[] userName = userNameStr.split(",");
        int length = reviewNodeNames.length;
        for(int i=0;i<length;i++){
            //循环添加
            ReviewNode reviewNode = new ReviewNode();
            reviewNode.setNodeName(reviewNodeNames[i]);
            reviewNode.setDescription(description[i]);
            User user = userService.getUserByName(userName[i]);
            Long userId = user.getId();
            reviewNode.setUserId(userId);
            reviewNode.setTemplateId(templateId);
            reviewNode.setSequence((i+1)+"");
            nodeService.add(reviewNode);

            //修改template的最后修改时间
            Map<String,Object> map1 = new HashMap<>();
            map1.put("templateId",templateId);
            map1.put("lastUpdateTime", DateUtil.getCurrentTime());
            reviewFlowTemplateService.updateTime(map1);
        }
    }

    @PostMapping("/addReviewNode")
    public void addReviewNode(HttpServletRequest request, HttpServletResponse response){
        String reviewNodeName = request.getParameter("reviewNodeName");
        String description = request.getParameter("description");
        String userName = request.getParameter("userName");
        User user = userService.getUserByName(userName);
        Long userId = user.getId();
        Long templateId = Long.valueOf(request.getParameter("templateId"));
        String sequence = request.getParameter("sequence");


        ReviewNode reviewNode = new ReviewNode();
        reviewNode.setNodeName(reviewNodeName);
        reviewNode.setDescription(description);
        reviewNode.setUserId(userId);
        reviewNode.setTemplateId(templateId);
        reviewNode.setSequence(sequence);

        int index = nodeService.add(reviewNode);
        String alreadyConfig = request.getParameter("alreadyConfig");
        if(alreadyConfig.equals("true")){
            reviewFlowTemplateService.changeAlreadyConfig(templateId);
        }

        Map<String,Object> map1 = new HashMap<>();
        map1.put("templateId",templateId);
        map1.put("lastUpdateTime", DateUtil.getCurrentTime());
        reviewFlowTemplateService.updateTime(map1);

        Map<String, Object> map = new HashMap<String, Object>();
        map = CurdUtil.curd(index);
        ServletUtil.createSuccessResponse(200, map, response);
    }

    @PostMapping (value="/queryReviewNode",produces="application/json;charset=UTF-8")
    public void queryByTemplateId(HttpServletRequest request, HttpServletResponse response){
        Long templateId = Long.valueOf(request.getParameter("templateId"));
        String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数
        String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", page);
        map.put("rows", rows);
        map.put("templateId",templateId);

        List<ReviewNode> reviewNodes = nodeService.queryByTemplateId(map);
        PageInfo<ReviewNode> pageInfo = new PageInfo<ReviewNode>(reviewNodes);
        JSONObject jo = new JSONObject();
        jo.put("rows", reviewNodes);
        jo.put("total", pageInfo.getPages());
        jo.put("records", pageInfo.getTotal());
        ServletUtil.createSuccessResponse(200, jo, response);
    }

    /**
     *1.查询出来node  2.将信息加上当前时间放入nodeHistory  3.删除node
     */
    @Transactional
    @PostMapping(value="/reset")
    public void reset(HttpServletRequest request){
        String templateIdd = request.getParameter("templateId");
        Long templateId = Long.valueOf(templateIdd);
        //1.
        Map<String,Object> map = new HashMap<>();
        map.put("templateId",templateId);
        List<ReviewNode> nodeList = nodeService.queryByTemplateId(map);
        //2.
        Timestamp timestamp = DateUtil.getCurrentTime();
        Iterator<ReviewNode> reviewNodeIterator = nodeList.iterator();
        while(reviewNodeIterator.hasNext()){
            Map<String,Object> nodeHistoryMap = new HashMap<>();
            ReviewNode reviewNode = reviewNodeIterator.next();
            map.put("nodeName",reviewNode.getNodeName());
            map.put("description",reviewNode.getDescription());
            map.put("templateId",reviewNode.getTemplateId());
            map.put("userId",reviewNode.getUserId());
            map.put("sequence",reviewNode.getSequence());
            map.put("updateTime",timestamp);
            nodeHistoryService.add(map);
        }
        //3.
        nodeService.reset(templateId);
    }

    @PostMapping("addReviewNodes")
    public void addReviewNodes(HttpServletRequest request, HttpServletResponse response) {

    }
}
