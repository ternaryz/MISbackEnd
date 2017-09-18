package com.tongyuan.model.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongyuan.model.dao.ReviewFlowInstanceMapper;
import com.tongyuan.model.domain.*;
import com.tongyuan.model.service.*;
import com.tongyuan.tools.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/6/29.
 */
@Service
@SuppressWarnings("SpringJavaAutowiringInspection")
public class ReviewFlowInstanceImpl implements ReviewFlowInstanceService{
    @Autowired
    private ReviewFlowInstanceMapper reviewFlowInstanceMapper;
    @Autowired
    private ReviewFlowTemplateService reviewFlowTemplateService;
    @Autowired
    private ReviewModelService reviewModelService;
    @Autowired
    private ReviewFlowInstanceService reviewFlowInstanceService;
    @Autowired
    private NodeService nodeService;
    @Autowired
    private NodeInstanceService nodeInstanceService;

    @Override
    public int add(ReviewFlowInstance reviewFlowInstance){
        return reviewFlowInstanceMapper.add(reviewFlowInstance);
    };

    @Override
    public ReviewFlowInstance queryByInstanceId(Long instanceId){
        return reviewFlowInstanceMapper.queryByInstanceId(instanceId);
    };

    @Override
    public int deleteByInstanceIds(String[] instanceIds){
        return reviewFlowInstanceMapper.deleteByInstanceIds(instanceIds);
    };

    @Override
    public int setStatus(Map<String,Object> map){
        return reviewFlowInstanceMapper.setStatus(map);
    }; //返回修改的数目，返回1修改成功，返回0修改失败。map的key为status和instanceId

    @Override
    public List<ReviewFlowInstance> queryByNameAndStatus(Map<String,Object> map){
        PageHelper.startPage(Integer.parseInt(map.get("page").toString()),Integer.parseInt(map.get("rows").toString()));
        return reviewFlowInstanceMapper.queryByNameAndStatus(map);
    };

    @Override
    public int updateTime(Map<String,Object> map){
        return reviewFlowInstanceMapper.updateTime(map);
    }

    /**
     * 开始审签流程
     * 返回的是开始的流程的id
     * @param modelId
     */
    @Override
    public Long startInstance(Long modelId){
        Long instanceId = fillInstance(modelId);
        CompleteNodeInstance(instanceId);
        return instanceId;
    }

    /**
     * 填充流程实例表
     * @param modelId
     * @return
     */
    public Long  fillInstance(Long modelId){
        //得到默认模板的templateId
        ReviewFlowTemplate reviewFlowTemplate = reviewFlowTemplateService.getTemplateByDefault();
        Long templateId = reviewFlowTemplate.getTemplateId();
        Timestamp timestamp = DateUtil.getCurrentTime();
        ReviewModel reviewModel = reviewModelService.queryByModelId(modelId);
        String instanceName = reviewModel.getModelName()+"的审签实例";
        String description = reviewModel.getModelName()+"的审签实例描述";

        ReviewFlowInstance reviewFlowInstance = new ReviewFlowInstance();
        reviewFlowInstance.setInstanceName(instanceName);
        reviewFlowInstance.setDescription(description);
        reviewFlowInstance.setModelId(modelId);
        reviewFlowInstance.setTemplateId(templateId);
        reviewFlowInstance.setCreateTime(timestamp);
        reviewFlowInstance.setLastUpdateTime(timestamp);
        reviewFlowInstance.setStatus(new Byte("1"));

        int index =  reviewFlowInstanceService.add(reviewFlowInstance);
        Long instanceId = reviewFlowInstance.getInstanceId();
        return instanceId;
    }

    /**
     * 填充节点实例表
     */
    public void CompleteNodeInstance(Long iinstanceId){
        Long instanceId = iinstanceId;
        //得到默认模板的templateId
        ReviewFlowTemplate reviewFlowTemplate = reviewFlowTemplateService.getTemplateByDefault();
        Long templateId = reviewFlowTemplate.getTemplateId();
        Map<String,Object> map = new HashMap<>();
        map.put("templateId",templateId);
        List<ReviewNode> reviewNodes = nodeService.queryByTemplateId(map);
        //遍历list，并填充
        Iterator<ReviewNode> iterator = reviewNodes.iterator();
        while(iterator.hasNext()){
            ReviewNodeInstance reviewNodeInstance = new ReviewNodeInstance();
            reviewNodeInstance.setNodeId(iterator.next().getNodeId());
            //填充第一步传过来的instanceId
            reviewNodeInstance.setInstanceId(instanceId);

            Timestamp timestamp = DateUtil.getCurrentTime();
            reviewNodeInstance.setCreateTime(timestamp);
            reviewNodeInstance.setLastUpdateTime(timestamp);
            reviewNodeInstance.setStatus(new Byte("1"));
            //向数据库中添加一条reviwNodeInstance
            nodeInstanceService.add(reviewNodeInstance);
        }
    }
}
