package com.tongyuan.model.service.impl;

import com.tongyuan.exception.SqlNumberException;
import com.tongyuan.model.domain.ReviewFlowInstance;
import com.tongyuan.model.domain.ReviewNode;
import com.tongyuan.model.domain.ReviewNodeInstance;
import com.tongyuan.model.service.*;
import com.tongyuan.tools.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Y470 on 2017/7/9.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
@Transactional
public class StatusChangeServiceImpl implements StatusChangeService{
    @Autowired
    private NodeInstanceService nodeInstanceService;
    @Autowired
    private NodeService nodeService;
    @Autowired
    private CheckorService checkorService;
    @Autowired
    private ReviewFlowInstanceService reviewFlowInstanceService;

    @Override
    public void agree(Long id) throws SqlNumberException{
        //1.将实例表中的status由2变为3, 调整其最后修改时间
        ReviewNodeInstance reviewNodeInstance = step1(id,"3");
        //2.查找下一个节点实例对应节点的sequence

        String sequence = step2(reviewNodeInstance);
        //3.多表联合查找，将下一个节点实例的status由1变成2
        Long instanceId = reviewNodeInstance.getInstanceId();
        updateNextStatus(instanceId,sequence);

        //4.对审签流程实例的 最后修改时间做调整
        Map<String,Object> map = new HashMap<>();
        map.put("instanceId",instanceId);
        map.put("lastUpdateTime",DateUtil.getCurrentTime());
        reviewFlowInstanceService.updateTime(map);

    }

    @Override
    public void disagree(Long id) {
        //1.本节点实例状态变化
        ReviewNodeInstance reviewNodeInstance = step1(id,"4");
        //2.本流程实例状态变化,复用以前所写的successAll
        Long instanceId = reviewNodeInstance.getInstanceId();
        sucessAll(instanceId,"2");
        //3.同上，对审签流程实例的 最后修改时间做调整
        Map<String,Object> map = new HashMap<>();
        map.put("instanceId",instanceId);
        map.put("lastUpdateTime",DateUtil.getCurrentTime());
        reviewFlowInstanceService.updateTime(map);
    }

    public ReviewNodeInstance step1(Long id,String setStatus){
        ReviewNodeInstance reviewNodeInstance = nodeInstanceService.queryById(id);
        Byte status = reviewNodeInstance.getStatus();
        if(status.toString().equals("2")){
            Map<String,Object> map = new HashMap<>();
            map.put("id",id);
            map.put("status",new Byte(setStatus));
            nodeInstanceService.updateStatus(map);

            Map<String,Object> map1 = new HashMap<>();
            map1.put("id",id);
            map1.put("lastUpdateTime", DateUtil.getCurrentTime());
            nodeInstanceService.updateTime(map1);
        }
        return reviewNodeInstance;
    }

    public String step2(ReviewNodeInstance reviewNodeInstance){
        Long nodeId = reviewNodeInstance.getNodeId();
        ReviewNode reviewNode = nodeService.queryByNodeId(nodeId);
        String sequence = reviewNode.getSequence();
        Integer s = Integer.parseInt(sequence)+1;
        sequence = s.toString();
        return sequence;
    }

    @Override
    public void updateNextStatus(Long instanceId,String sequence) throws SqlNumberException{
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("sequence",sequence);
        map.put("instanceId",instanceId);
        List<ReviewNodeInstance> reviewNodeInstances = checkorService.queryAfterAgree(map);
        if(reviewNodeInstances.size()==1){
            ReviewNodeInstance reviewNodeInstance2 = reviewNodeInstances.get(0);
            Long id=reviewNodeInstance2.getId();
            Byte status2 = reviewNodeInstance2.getStatus();
            if(status2.toString().equals("1")){
                Map<String,Object> map2 = new HashMap<String,Object>();
                map2.put("status",new Byte("2"));
                map2.put("id",id);
                nodeInstanceService.updateStatus(map2);
            }
        }else if(reviewNodeInstances.size()==0) {      //已经成功完成审签流程
            sucessAll(instanceId,"3");
        }else{
            throw new SqlNumberException("查询结果数目应该唯一！");
        }
    }

    //成功完成，将reviewFlowInstance中的status由1变成3
    public void sucessAll(Long instanceId,String setStatus){
        ReviewFlowInstance reviewFlowInstance = reviewFlowInstanceService.queryByInstanceId(instanceId);
        Byte status = reviewFlowInstance.getStatus();
        if(status.toString().equals("1")){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("status",new Byte(setStatus));
            map.put("instanceId",instanceId);
            reviewFlowInstanceService.setStatus(map);
        }
    }
}
