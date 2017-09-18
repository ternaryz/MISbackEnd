package com.tongyuan.model.DownLoad;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *用来根据传入的线程数目，分割，并启动下载线程
 *
 */
public class SplitNumber {
	public SplitNumber(URL url, String dest, int threadNum,String fileName) {
		super();
		this.url = url;
		this.dest = dest;
		this.threadNum = threadNum;
		this.fileName = fileName;
	}
	private URL url;
	private String dest;
	private int threadNum;
	private String fileName;
	
	public void splitProcess() throws IOException{
		long length = getLength();
		long size = length/threadNum+1;
		long startPos = 0;
		long endPos = size;
		
		File destDir = new File(dest);
		if(!destDir.exists()){
			destDir.mkdirs();
		}
		File destFile = new File(dest+File.separator+fileName);
		
		for(int i=0;i<threadNum-1;i++){
			DownThread dt = new DownThread(url,destFile,startPos,endPos);
			Thread thread = new Thread(dt);
			thread.start();
			startPos+=size;
			endPos+=size;
		}
		DownThread dt = new DownThread(url,destFile,startPos,length);
		Thread thread = new Thread(dt);
		thread.start();
	}
	public long getLength() throws IOException{
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		int length = -1;
		if(200==con.getResponseCode()){
			length = con.getContentLength();
		}
		con.disconnect();
		return length;
	}
}
