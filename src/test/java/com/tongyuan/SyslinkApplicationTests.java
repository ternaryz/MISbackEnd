package com.tongyuan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SyslinkApplicationTests {

	@Test
	public void contextLoads() {
	}

//	@Test
//	public void testFile() throws IOException {
//
//		CommonServiceImp CommonServiceImp = new CommonServiceImp();
//
//		ResourceUtil resourceUtil = new ResourceUtil();
//
//		Integer g[] = new Integer [60];
//
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		FileInputStream fin  = null;
//		try {
//			fin = new FileInputStream("D:\\syslink.zip");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		int read;
//		byte[] bytes=new byte[1024];
//		try {
//			while((read = fin.read(bytes)) >0){
//                out.write(bytes, 0, read);
//            }
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		fin.close();
//
//		bytes = out.toByteArray(); // 这就是全部的字节数组了。
//		out.close();
//        long byteslength = bytes.length;
//		System.out.println(byteslength);
//		boolean result  = CommonServiceImp.UploadFile("syslink", 0,byteslength,
//				bytes);
//		// 模型相对路径xieyx/20170620.../
//		String modelDir = resourceUtil.unzipFile("syslink", "xieyx");
//		//输出文件的目录（modelDir是解压缩到的目录）
//		System.out.println("modelDir==========" + modelDir + "*************");
//		//获取到model解压缩的路径
//		String modelPath =  resourceUtil.getModelPath(modelDir, "syslink");
//		//遍历文件，对model库进行插入
//	//	ResourceUtil.insertModelData(modelDir,"syslink",modelPath,"这是syslink项目");
//		String parentPath = ResourceUtil.getFileDriectory() + modelDir;
//		ResourceUtil.getSubFile(parentPath.substring(0,
//				parentPath.length() - 1),parentPath.substring(0,
//				parentPath.length() - 1),"这是syslink项目");
//
//	}



}
