package com.tongyuan.model.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.tongyuan.model.domain.Directory;
import com.tongyuan.model.domain.Model;
import com.tongyuan.model.domain.Variable;
import com.tongyuan.model.enums.ModelClasses;
import com.tongyuan.model.enums.VariableType;
import com.tongyuan.model.service.DirectoryService;
import com.tongyuan.model.service.ModelService;
import com.tongyuan.model.service.VariableService;
import com.tongyuan.pageModel.EasyuiTreeNode;
import com.tongyuan.pageModel.ModelWeb;
import com.tongyuan.tools.ServletUtil;
import com.tongyuan.tools.StringUtil;
import com.tongyuan.util.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/**
 * model方法
 * Created by xieyx on 2017-6-21.
 */
@Controller
@RequestMapping("/api/model")
public class ModelController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ModelService modelService;
    @Autowired
    private DirectoryService directoryService;
    @Autowired
    private ResourceUtil resourceUtil;
    @Autowired
    private VariableService variableService;

    @RequestMapping(value = "/test",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void test(HttpServletRequest request , HttpServletResponse response){
        JSONObject result=new JSONObject();
    //    String name = request.getParameter("name");
        //获取到userName,password
        // 查询到userId的方法 findByNameAndpassword
        //xmlMap 把xml转化成map的格式
        Map<String, Object> xmlMap = new HashMap<String, Object>();
        //存放解析的所有xmlMap
        Map<String,Map> xmlAnalysisMap = new HashMap<>();
        //存放解析svg，info文件所在位置的Map
        Map<String,String> svgPath = new HashMap<>();
        String name = "syslink";
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("name",name);
        //查找到项目所在的位置
        List<Directory> directoryList = directoryService.queryListByName(params);
        //选取最近push的一个directory对象
        Directory directory = new Directory();
        if(!directoryList.isEmpty()){
            directory = directoryList.get(0);
        }else {
            return ;
        }
        //获取文件所在位置，寻找xml文件所在的路径，解析xml吧所需的数据插入到数据库中
        //文件所在位置
        String filePath = directory.getAbsoluteAddress();
        //获取到xml所在的文件位置
        String xmlPath = "";
        xmlPath= resourceUtil.getXmlPath(filePath,xmlPath);
        //对xml进行解析,遍历xml文件下所有文件
        if(StringUtil.isNull(xmlPath)){
            result.put("message","没有找到xml文件！");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return ;
        }
        File xmlFilePath = new File(xmlPath);
        String[] subFiles = xmlFilePath.list();
        Model model = new Model();
        model.setName(subFiles[0].split("\\.")[0]);
        model.setDirectoryId(directory.getId());
        model.setClasses(ModelClasses.Package.getKey());
        model.setModelFilePath(filePath);
        model.setScope(false);
        model.setUserId(1);
        model.setDeleted(false);
        if(modelService.queryModelByName(subFiles[0].split("\\.")[0]) == null){
            modelService.add(model);
        }
        //查找最外层空的model
        Model nullModel = modelService.queryModelByName(subFiles[0].split("\\.")[0]);
        for (int i = 0; i < subFiles.length; i++) {
            //查看文件的格式
            String [] fileNames = subFiles[i].split("\\.");
            //文件的类型
            String filePreType = fileNames[fileNames.length-2];
            String fileType = fileNames[fileNames.length-1];
            if(("xml").equals(fileType)){
                xmlMap =  resourceUtil.analysisXmlPath(xmlFilePath +"/" +subFiles[i]);
                xmlAnalysisMap.put(subFiles[i],xmlMap);
                svgPath.put(subFiles[i],xmlFilePath +"/" +subFiles[i]);
            }else if("svg".equals(fileType)){
                  if("icon".equals(filePreType)){
                      svgPath.put(subFiles[i],xmlFilePath +"/" +subFiles[i]);
                  }else if("diagram".equals(filePreType)){
                      svgPath.put(subFiles[i],xmlFilePath +"/" +subFiles[i]);
                  }
            }else if("html".equals(fileType)){
                svgPath.put(subFiles[i],xmlFilePath +"/" +subFiles[i]);
            }
        }
        //遍历xmlMap进行数据的插入
        for(Map.Entry<String,Map> entry : xmlAnalysisMap.entrySet()){
            //解析xmlmap 把数据存放到数据库
            insertData(entry,svgPath,nullModel,directory);
        }
        result.put("message","xml解析成功!");
        result.put("flag",true);
        ServletUtil.createSuccessResponse(200, result, response);
        return ;
    }


    public void insertData(Map.Entry<String,Map> entry,Map svgPath,Model nullModel,Directory directory){
        Map<String,Object> xmlMap = entry.getValue();
        Model model = new Model();
        //验证model
        Model validateModel = new Model();
        model.setDirectoryId(directory.getId());
        model.setParentId(nullModel.getId());
        model.setUserId(nullModel.getUserId());
        model.setScope(false);
        model.setCreateTime(new Date());
        model.setDeleted(false);
        analysisXmlMap(xmlMap,model,svgPath);
        validateModel = modelService.queryModelByName(model.getName());
        if( validateModel == null){
            modelService.add(model);
        }else{
            model.setLastUpdateTime(new Date());
            model.setId(validateModel.getId());
            modelService.update(model);
        }
        insertVaiable(xmlMap);
    }

    public void analysisXmlMap(Map<String,Object> xmlMap,Model model,Map<String,String> svgPath){
        for (Map.Entry<String ,Object> entry : xmlMap.entrySet()) {
            if("ModelName".equals(entry.getKey())){
                String type = "";
                //判断value是什么类型
                type = decideType(entry.getValue(),type);
                if("String".equals(type)){
                    if(!StringUtil.isNull((String) entry.getValue())){
                        model.setName((String) entry.getValue());
                        for(Map.Entry<String ,String> svgEntry : svgPath.entrySet()){
                            if (svgEntry.getKey().equals(entry.getValue()+".diagram.svg")){
                                model.setDiagramSvgPath(svgEntry.getValue());
                            }
                            if (svgEntry.getKey().equals(entry.getValue()+".icon.svg")){
                                model.setIconSvgPath(svgEntry.getValue());
                            }
                            if (svgEntry.getKey().equals(entry.getValue()+".info.html")){
                                model.setInfoTextPath(svgEntry.getValue());
                            }
                            if (svgEntry.getKey().equals(entry.getValue()+".dom.xml")){
                                model.setModelFilePath(svgEntry.getValue());
                            }
                        }
                    }
            }

            }
            if(entry.getKey().endsWith("_Model")){
               String [] modelNames = entry.getKey().split("\\_");
                 model.setType(modelNames[0]);
            }
            if("ModelDescript".equals(entry.getKey())){
                String type = "";
                //判断value是什么类型
                type = decideType(entry.getValue(),type);
                if("String".equals(type)){
                    if(!StringUtil.isNull((String) entry.getValue())){
                    model.setDiscription((String) entry.getValue());
                    }
                }
            }
            if("ModelClass".equals(entry.getKey())){
                String type = "";
                //判断value是什么类型
                type = decideType(entry.getValue(),type);
                if("String".equals(type)){
                    if(!StringUtil.isNull((String) entry.getValue())){
                        model.setClasses(ModelClasses.getValueByKey((String) entry.getValue()));
                    }
                }
            }
            if("Imports".equals(entry.getKey())){
                String type = "";
                type = decideType(entry.getValue(),type);
                //当imports 一个组件的时候，或者没有的时候
                if("String".equals(type)){
                    if(!StringUtil.isNull((String) entry.getValue())){
                        model.setImport((String) ((String) entry.getValue()).split(" ")[1]);
                    }
                }
                else if("Map".equals(type)){
                    Object secondValue =  ((Map)entry.getValue()).get("import");
                    type = decideType(secondValue,type);
                    //当import的数量等于1的时候
                    if("String".equals(type)){
                        if(!StringUtil.isNull(secondValue.toString())){
                            model.setImport((String) (secondValue.toString().split(" ")[1]));
                        }
                    }
                    else if("List".equals(type)){
                        //当import的数量大于1的时候
                        List<String> valueList = (List)secondValue;
                        String importValue="";
                        for(int i= 0;i<valueList.size();i++){
                            importValue += valueList.get(i).split(" ")[1];
                        }
                        model.setImport(importValue);
                    }
                }
            }

            if("Extendses".equals(entry.getKey())){
                String type = "";
                type = decideType(entry.getValue(),type);
                //当imports 一个组件的时候，或者没有的时候
                if("String".equals(type)){
                    if(!StringUtil.isNull((String) entry.getValue())){
                        model.setImport((String) ((String) entry.getValue()).split(" ")[1]);
                    }
                }
                else if("Map".equals(type)){
                    Object secondValue =  ((Map)entry.getValue()).get("extends");
                    type = decideType(secondValue,type);
                    //当import的数量等于1的时候
                    if("String".equals(type)){
                        if(!StringUtil.isNull(secondValue.toString())){
                            model.setImport((String) (secondValue.toString().split(" ")[1]));
                        }
                    }
                    else if("List".equals(type)){
                        //当import的数量大于1的时候
                        List<String> valueList = (List)secondValue;
                        String extendsValue="";
                        for(int i= 0;i<valueList.size();i++){
                            extendsValue += valueList.get(i);
                        }
                        model.setExtends(extendsValue);
                    }
                }
            }
        }
    }

    public String decideType(Object value,String type){
        boolean string = value instanceof  String ;
        boolean list = value instanceof  List;
        boolean map = value instanceof  Map;
        if(string){
            type = "String";
        }
        else if(list){
            type = "List";
        }
        else if(map){
            type = "Map";
        }
        return type;
    }

    public void insertVaiable(Map<String,Object> xmlMap) {
        Model model = new Model();
        for (Map.Entry<String, Object> entry : xmlMap.entrySet()) {
            if ("ModelName".equals(entry.getKey())) {
                String type = "";
                //判断value是什么类型
                type = decideType(entry.getValue(), type);
                if ("String".equals(type)) {
                    if (!StringUtil.isNull((String) entry.getValue())) {
                        model = modelService.queryModelByName((String) entry.getValue());
                    }
                }
            }
        }
        //插入到variable表中
        insertV(xmlMap,model);
      }
    public  void insertV(Map<String,Object> xmlMap,Model model) {
        for (Map.Entry<String, Object> entry : xmlMap.entrySet()) {
            if ("Components".equals(entry.getKey())) {
                String type = "";
                //判断value是什么类型
                type = decideType(entry.getValue(), type);
                if("Map".equals(type)){
                Map<String, List> compMap = (Map<String, List>) entry.getValue();
                type = decideType(compMap.get("component"),type);
                if("List".equals(type)){
                    List<Map<String, String>> compList = compMap.get("component");
                    for (int i = 0; i < compList.size(); i++) {
                        Variable variable = new Variable();
                        variable.setModelId(model.getId());
                        //判断这个组件是否需要插入到数据库
                        int isVar = 0;
                        for (Map.Entry<String, String> entryComp : compList.get(i).entrySet()) {
                            if ("IsVariable".equals(entryComp.getKey())) {
                                if ("False".equals(entryComp.getValue())) {
                                    isVar =1;
                                    continue;
                                }
                            }
                            if ("Name".equals(entryComp.getKey())) {
                                variable.setName(entryComp.getValue());
                            }
                            if ("Type".equals(entryComp.getKey())) {
                                if ("True".equals(compList.get(i).get("IsArray"))) {
                                    variable.setType(VariableType.getValueByKey(entryComp.getValue()+"[]"));
                                }else {
                                    variable.setType(VariableType.getValueByKey(entryComp.getValue()));
                                }
                            }
                            if ("Value".equals(entryComp.getKey())) {
                                variable.setDefaultValue(entryComp.getValue());
                            }
                            if ("Unit".equals(entryComp.getKey())) {
                                variable.setUnits(entryComp.getValue());
                            }
                            if ("Min".equals(entryComp.getKey())) {
                                variable.setLowerBound(entryComp.getValue());
                            }
                            if ("Max".equals(entryComp.getKey())) {
                                variable.setUpperBound(entryComp.getValue());
                            }
                            if ("IsParameter".equals(entryComp.getKey())) {
                                if ("True".equals(entryComp.getValue())) {
                                    variable.setIsParam(1);
                                } else {
                                    variable.setIsParam(0);
                                }
                            }
/*                            if ("IsArray".equals(entryComp.getKey())) {
                                if ("True".equals(entryComp.getValue())) {
                                    variable.setIsInput(1);
                                } else {
                                    variable.setIsInput(0);
                                }
                            }*/
                        }
                        if ( 0 == isVar){
                            variableService.add(variable);
                        }
                    }
                }else if ("Map".equals(type)){
                    Map<String,String> compotentMap = (Map<String, String>) (Map<String, String>) compMap.get("component");
                    Variable variable = new Variable();
                    variable.setModelId(model.getId());
                    if(compotentMap.get("IsVariable").equals("False")){
                         return;
                    }
                    variable.setName(compotentMap.get("Name"));
                    variable.setType(VariableType.getValueByKey(compotentMap.get("Type")));
                    variable.setDefaultValue(compotentMap.get("Value"));
                    variable.setUnits(compotentMap.get("Unit"));
                    variable.setLowerBound(compotentMap.get("Min"));
                    variable.setUpperBound(compotentMap.get("Max"));
                    if(compotentMap.get("IsParameter").equals("True")){
                        variable.setIsParam(1);
                    }
                    else {
                        variable.setIsParam(0);
                    }
                    if(compotentMap.get("IsArray").equals("True")){
                        variable.setIsInput(1);
                    }
                    else {
                        variable.setIsInput(0);
                    }
                    variableService.add(variable);
                 }
                }
            }
        }
    }

    //获取模型树状图
    @RequestMapping(value = "/getModelTree",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,List<EasyuiTreeNode>> getModelTree(HttpServletRequest request , HttpServletResponse response){
        String name = request.getParameter("name");
        //获取到userName,password
        // 查询到userId的方法 findByNameAndpassword
        long userId = 1;
        Map<String ,List<EasyuiTreeNode>> modelTree = new HashMap<>();
        List<Model> allModelList = modelService.findAllModel();
        List<Model> rootModelList = modelService.findRootModel();
        //公有库模型的树
        List<EasyuiTreeNode> publicModelTree = new ArrayList<EasyuiTreeNode>();
        //个人库模型树
        List<EasyuiTreeNode> privateModelTree = new ArrayList<EasyuiTreeNode>();
        for (Model model: rootModelList) {
            if(!model.getScope()){
                if(model.getUserId() == userId){
                    privateModelTree.add(tree(model,allModelList,true));
                }
            }
            publicModelTree.add(tree(model,allModelList,true));
        }
        modelTree.put("publicModelTree",publicModelTree);
        modelTree.put("privateModelTree",privateModelTree);
        return modelTree;
    }


    /**
     * 递归
     * @param model
     * @param recursive
     * @return
     */
    private EasyuiTreeNode tree(Model model,List<Model> allModelList, boolean recursive) {
        EasyuiTreeNode node = new EasyuiTreeNode();
        Map<String, Object> attributes = new HashMap<String, Object>();
        //    attributes.put("taskId", directory.getTask().getTaskId());
        if(model != null){
            node.setId(model.getModelFilePath());
            node.setText(model.getName());
        }
        //    node.setAttributes(attributes);
        if (model != null
                && allModelList.size() > 0) {
            if (recursive) {
                List<Model> modelList = new ArrayList<Model>();
                for (Model  fileDir: allModelList) {
                    if(fileDir.getParentId() == model.getId()){
                        modelList.add(fileDir);
                    }
                }
                //Collections.sort(directoryList, new FilelibraryComparator());
                List<EasyuiTreeNode> children = new ArrayList<EasyuiTreeNode>();
                for (Model m : modelList) {
                    node.setState("closed");
                    EasyuiTreeNode t = tree(m,allModelList, true);
                    children.add(t);

                }
                node.setChildren(children);
            }
        }
        return node;
    }

    //获取模型列表
    @RequestMapping(value = "/getModelList",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void getModelList(HttpServletRequest request , HttpServletResponse response){
        //获取到userName,password
        // 查询到userId的方法 findByNameAndpassword
        JSONObject jo=new JSONObject();
        Map<String,List<Model>> modelListMap = new HashMap<>();
        List<Model> allModelList = modelService.findAllModel();
        //所有的公有模型列表
        List<Model> publicModelList = new ArrayList<>();
        //个人私有模型列表
        List<Model> privateModelList = new ArrayList<>();
        //所有私有的模型列表
        List<Model> allPrivateModelLsit = new ArrayList<>();
        for (Model allmodel: allModelList) {
            if(0 != allmodel.getParentId()){
                publicModelList.add(allmodel);
                if(false == allmodel.getScope()){
                    allPrivateModelLsit.add(allmodel);
   //                 if(user.getId() != null && user.getId() == allmodel.getUserId()){
   //                 privateModelList.add(allmodel);
 //                   }
                }
            }
        }
//        modelListMap.put("publicModelList",publicModelList);
//        modelListMap.put("privateModelList",privateModelList);
//        modelListMap.put("allPrivateModelLsit",allPrivateModelLsit);
        jo.put("publicModelList",publicModelList);
        jo.put("privateModelList",privateModelList);
        jo.put("allPrivateModelLsit",allPrivateModelLsit);
        jo.put("message","查询成功!");
        jo.put("flag",true);
        ServletUtil.createSuccessResponse(200, jo, response);
        return;
  //      return modelListMap;
    }

    //模糊查询model列表
    @RequestMapping(value = "/vagueSearchModelList",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void vagueSearchModelList(HttpServletRequest request , HttpServletResponse response){
        Map<String,Object> params = new HashMap<String,Object>();
        JSONObject jo=new JSONObject();
        String name = request.getParameter("name");
        String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数
        String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数
        params.put("name",name);
        params.put("page", page);
        params.put("rows", rows);
        List<Model> modelList = modelService.vagueSearchByName(params);
        PageInfo<Model> pageInfo =new PageInfo<Model>(modelList);
        jo.put("message","查询成功!");
        jo.put("flag",true);
        jo.put("rows", modelList);
        jo.put("total", pageInfo.getPages());//总页数
        jo.put("records",pageInfo.getTotal());//查询出的总记录数
        ServletUtil.createSuccessResponse(200, jo, response);
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject list(HttpServletRequest request , HttpServletResponse response){
        JSONObject jo=new JSONObject();
        List<ModelWeb>  repositoryModelList = new ArrayList<>();
        try {
            List<Model> allModelList = modelService.findAllModel();
            for (int i = 0; i <= allModelList.size() -1; i++) {
                ModelWeb modelWeb = new ModelWeb();
                modelWeb.setIndex(allModelList.get(i).getId());
                modelWeb.setName(allModelList.get(i).getName());
                modelWeb.setImageUrl("../../assets/test1.png");
    //            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(modelWeb);
                repositoryModelList.add(modelWeb );
            }
        }catch(Exception e){
            e.printStackTrace();
            jo.put("status","1");
            jo.put("code",0);
            jo.put("msg","ok");
            return jo;
        }
        jo.put("status",1);
        jo.put("code",0);
        jo.put("msg","ok");
        jo.put("repositories",repositoryModelList);
        return (JSONObject) JSONObject.toJSON(jo);
    }
}
