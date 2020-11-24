package com.example.demo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class Klog {

	private static String logUrl = "/home/uadmin/logs";
	private static String logFile = "/privLog.log";
	
	
	Klog() throws IOException{
		File file = new File(logUrl);
		File filename = new File(logUrl + logFile);
		if(file.exists() == false) {
			file.mkdirs();
		}
		if(filename.exists() == false) {
			filename.createNewFile();
		}
	}
	
	private static Klog klog = null;
	
	public synchronized static Klog getInstance() throws IOException {
		
		if(klog == null) {
			klog = new Klog();
			System.out.println("klog started");
		}
		return klog;
	}
	
	
	
	
	public void log(HttpServletRequest request, String string) throws IOException {
		
		OutputStream output = new FileOutputStream(logUrl + logFile, true);
		
		Date date = new Date();
		
		String log = date + " : "+  getIp(request) + " : " + string+ "\n";
		output.write(log.getBytes());
		
	}
	
	


    private String getIp(HttpServletRequest request) {
 
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
           
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
            
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
               
       return ip;
    }
    
    



	
	
}
