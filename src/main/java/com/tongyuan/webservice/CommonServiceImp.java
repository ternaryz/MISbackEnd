package com.tongyuan.webservice;

import com.tongyuan.model.service.DirectoryService;
import com.tongyuan.tools.StringUtil;
import com.tongyuan.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.io.IOException;

/**
 * 接口实现
 * 
 * @author leftso
 *
 */
@WebService(serviceName = "CommonService", // 与接口中指定的name一致
		targetNamespace = "http://webservice.tongyuan.com/", // 与接口中的命名空间一致,一般是接口的包名倒
		endpointInterface = "com.tongyuan.webservice.CommonService"// 接口地址
)
@Component
public class CommonServiceImp implements CommonService {
     @Autowired
     private ResourceUtil resourceUtil ;

	@Autowired
	private DirectoryService directoryService;

	@Override
	public String sayHello(String name) {

		return "Hello ," + name;
	}

	@Override
	public boolean validateUser(String userName, String passWord) {
		Boolean result =true;
        if(StringUtil.isNull(userName)){
          return false;
		}
		if(StringUtil.isNull(passWord)){
			result = false;
		}
		try{

		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return result;
	}

	/* 上传任务压缩文件,fileName为**.zip
    */
	public boolean UploadFile(String fileName, long beginPos, long length,
								  byte[] data) {
		System.out.println("starting upload the file...");
		boolean result = false;
		//获取压缩包 C:/Temp/zip/文件名
		String filePath = resourceUtil.getzipPath() + fileName;
		System.out.println("filePath==" + filePath);
		try {
			System.out.println("starting writing file...");
			resourceUtil.writeFile(filePath, beginPos, length, data);
			result = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		}
		System.out.println("上传完毕！！！");


		return result;
	}

	public  void uploadFileDate(String filename ,String username,String modelName,String description){

		// 模型相对路径xieyx/20170620.../
		String modelDir = resourceUtil.unzipFile(filename, username);
		//输出文件的目录（modelDir是解压缩到的目录）
		System.out.println("modelDir==========" + modelDir + "*************");
		//获取到model解压缩的路径
		String modelPath =  resourceUtil.getModelPath(modelDir, modelName);
		//遍历文件，对model库进行插入
	//	 modelService.insertModelData(modelDir,modelName,modelPath,description);
		//获取根目录
		//String parentPath = ResourceUtil.getFileDriectory() + modelDir;
        String parentPath = modelPath;
        resourceUtil.getSubFile(parentPath.substring(0,
				parentPath.length() ),parentPath.substring(0,
				parentPath.length() ),description);

		return ;
	}
}


