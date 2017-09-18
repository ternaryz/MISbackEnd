package com.tongyuan.model.DownLoad;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 实现下载的类，需要传入url、目的文件夹、开始点、终止点
 *
 */
public class DownThread implements Runnable{
	public DownThread(URL url, File dest, long startPos, long endPos) {
		super();
		this.url = url;
		this.dest = dest;
		this.startPos = startPos;
		this.endPos = endPos;
	}


	private URL url;
	private File dest;
	private long startPos;
	private long endPos;
	private String fileName;
	
	
	public void run(){
		try{
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestProperty("User-Agent","NetFox");
			String sProperty = "bytes="+startPos+"-"+endPos;
			con.setRequestProperty("RANGE",sProperty);
			
			RandomAccessFile dst = new RandomAccessFile(dest,"rw");
			dst.seek(startPos);
			
			BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
			byte[] data = new byte[1024];
			int size = -1;
			while((size=bis.read(data))>0){
				dst.write(data,0,size);
			}
			
			con.disconnect();
			bis.close();
			dst.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
