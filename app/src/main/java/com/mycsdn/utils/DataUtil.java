package com.mycsdn.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.mycsdn.bean.CommonException;

public class DataUtil {
	
	public static String doGet(String urlStr) throws CommonException {
		StringBuffer sb = new StringBuffer();
		try{
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			if (connection.getResponseCode() == 200){
				InputStream is = connection.getInputStream();  
                InputStreamReader isr = new InputStreamReader(is,"UTF-8");  
				int len = 0;
				char[] buf = new char[1024];
				
				while((len = isr.read(buf)) != -1){
					sb.append(new String(buf, 0, len));
				}
				
				is.close();
				isr.close();
			}else{
				throw new CommonException("connection failed");
			}
			
		}catch (Exception e){
			throw new CommonException("conection failed");
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(doGet("http://www.csdn.net/headlines.html"));
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
