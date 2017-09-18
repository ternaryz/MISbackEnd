package com.tongyuan.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * 接口
 * 
 * @author leftso
 *
 */
@WebService(name = "CommonService", // 暴露服务名称
		targetNamespace = "http://webservice.tongyuan.com/"// 命名空间,一般是接口的包名倒序
)
public interface CommonService {
	@WebMethod
	@WebResult(name = "String", targetNamespace = "")
	public String sayHello(@WebParam(name = "userName") String name);

	@WebMethod
	@WebResult(name = "String",targetNamespace = "")
	public boolean validateUser(@WebParam(name = "userName") String userName,@WebParam(name = "passWord") String passWord);

	@WebMethod
	@WebResult(name = "String",targetNamespace = "")
	public boolean UploadFile(@WebParam(name = "fileName") String fileName,@WebParam(name = "beginPos") long beginPos,@WebParam(name = "length") long length,@WebParam(name = "data") byte[] data);

}
