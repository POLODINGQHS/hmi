package com.globot.hmi.attendance.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.globot.hmi.attendance.domain.Device;
import com.globot.hmi.attendance.domain.Employee;
import com.globot.hmi.attendance.domain.Leave;
import com.globot.hmi.attendance.domain.Log;
import com.globot.hmi.attendance.domain.SignInRecord;
import com.globot.hmi.attendance.enums.ResultEnum;
import com.globot.hmi.attendance.redis.JedisClientSingle;
import com.globot.hmi.attendance.service.IConfigService;
import com.globot.hmi.attendance.service.ILogService;
import com.globot.hmi.attendance.service.ILoginService;
import com.globot.hmi.attendance.util.result.WebResult;
import com.globot.hmi.attendance.vo.SignInInfoVO;

import java.io.IOException;  
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.apache.commons.io.IOUtils;  
  
import com.jcraft.jsch.ChannelExec;  
import com.jcraft.jsch.JSch;  
import com.jcraft.jsch.JSchException;  
import com.jcraft.jsch.Session;  

@Controller
@RequestMapping("/api/server")
public class ServerMangageController {
	
	@Autowired
	JedisClientSingle jedis;
	
	@Autowired
	IConfigService configService;
	
	@Autowired
	ILogService logService;
	
	@RequestMapping("/log")
	@ResponseBody
	public List<Log> getLog() {
		JSONObject json = new JSONObject();
		List<Log> log = logService.getLog();
		System.out.println(log.toString());
		return log;
	}
	
	@RequestMapping("/addlog")
	@ResponseBody
	public int addLog(@RequestBody Log log) {
		return logService.addLog(log);
	}
	
	@RequestMapping("/var")
	@ResponseBody
	public String getVariable() {
		JSONObject json = new JSONObject();
		String faceRecognition = jedis.get("FaceRecognition");
//		int language = configService.readConfig();
//		String chinese = jedis.get("chinese");
//		String japanese = jedis.get("japanese");
		if(faceRecognition == null){
			json.put("FaceRecognition", "none");
		}else{
			json.put("FaceRecognition", faceRecognition);
		}
		json.put("language", configService.readConfig());
//		if(chinese == null){
//			json.put("Chinese", "none");
//		}else{
//			json.put("Chinese", chinese);
//		}
//		if(japanese == null){
//			json.put("Japanese", "none");
//		}else{
//			json.put("Japanese", japanese);
//		}
		return json.toString();
	}
	
	@RequestMapping("/openspeech")
	@ResponseBody
	public WebResult<String> openSpeech() {
		try{
			System.out.println("openspeech");
			String url = "http://localhost:8082/speech";
			CloseableHttpClient httpClient = HttpClients.createDefault();
			//创建一个GET对象
			HttpGet get =new HttpGet(url);
			//执行请求
			CloseableHttpResponse response =httpClient.execute(get);
			//取响应的结果
			int statusCode =response.getStatusLine().getStatusCode();
			System.out.println(statusCode);
			HttpEntity entity =response.getEntity();
			String string = EntityUtils.toString(entity,"utf-8");
			System.out.println(string);
			//关闭httpclient
			response.close();
			httpClient.close();
			WebResult<String> webResut = new WebResult<>(ResultEnum.SUCCESS);
			webResut.setData(string);
			return webResut;
		}catch (Exception e){
			System.out.println(e);
			return null;
		}
		
	}
	
	@RequestMapping("/openface")
	@ResponseBody
	public WebResult<String> openFace() {
		try{
			System.out.println("openface");
			String url = "http://localhost:8082/face/?control=open";
			CloseableHttpClient httpClient = HttpClients.createDefault();
			//创建一个GET对象
			HttpGet get =new HttpGet(url);
			//执行请求
			CloseableHttpResponse response =httpClient.execute(get);
			//取响应的结果
			int statusCode =response.getStatusLine().getStatusCode();
			System.out.println(statusCode);
			HttpEntity entity =response.getEntity();
			String string = EntityUtils.toString(entity,"utf-8");
			System.out.println(string);
			//关闭httpclient
			response.close();
			httpClient.close();
			WebResult<String> webResut = new WebResult<>(ResultEnum.SUCCESS);
			webResut.setData(string);
			return webResut;
		}catch (Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	@RequestMapping("/closefacespeech")
	@ResponseBody
	public WebResult<String> closeFaceSpeech() {
		try{
			System.out.println("closefacespeech");
			String url = "http://localhost:8082/face/?control=close";
			CloseableHttpClient httpClient = HttpClients.createDefault();
			//创建一个GET对象
			HttpGet get =new HttpGet(url);
			//执行请求
			CloseableHttpResponse response =httpClient.execute(get);
			//取响应的结果
			int statusCode =response.getStatusLine().getStatusCode();
			System.out.println(statusCode);
			HttpEntity entity =response.getEntity();
			String string = EntityUtils.toString(entity,"utf-8");
			System.out.println(string);
			//关闭httpclient
			response.close();
			httpClient.close();
			WebResult<String> webResut = new WebResult<>(ResultEnum.SUCCESS);
			webResut.setData(string);
			return webResut;
		}catch (Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	@RequestMapping("/restartdjango")
	@ResponseBody
	public WebResult<String> restartDjango(){
//		try{
//			String host = "10.112.59.158";  
//			int port = 22;  
//			String user = "lab640";  
//			String password = "lab640";  
//			String command = "echo lab640 | sudo sh /home/lab640/faceRecog/restart_django.sh";  
//			String res = exeCommand(host,port,user,password,command);  
//			
//			System.out.println(res); 
//		}catch (Exception e){
//			System.out.println(e);
//		}
		try{
			//String[] cmds = {"/bin/bash", "-c" ,  "echo \"gglobot2016\" | sudo -S sh /home/lab640/faceRecog/restart_django.sh"}; 
			String[] cmds = {"/bin/bash", "-c" ,"sh /home/lab640/faceRecog/restart_django.sh >/home/lab640/faceRecog/log.txt 2>&1 &"}; 
			run(cmds);  
		}catch (Exception e){
			System.out.println(e);
		}
		return null;
	}
	
//	@RequestMapping("/tochinese")
//	@ResponseBody
//	public WebResult<String> changeToChinese(){
//		try{
//			configService.setConfig(0);
//		}catch (Exception e){
//			System.out.println(e);
//		}
//		return null;
//	}
	
//	@RequestMapping("/tojapanese")
//	@ResponseBody
//	public WebResult<String> changeToJapanese(){
//		try{
//			configService.setConfig(1);
//		}catch (Exception e){
//			System.out.println(e);
//		}
//		return null;
//	}
	
	@RequestMapping("/switchlanguage")
	@ResponseBody
	public int switchLanguage(){
		try{
			configService.setConfig();
			return 1;
		}catch (Exception e){
			System.out.println(e);
		}
		return 0;
	}
	
	public String exeCommand(String host, int port, String user, String password, String command) throws JSchException, IOException {  
        
        JSch jsch = new JSch();  
        Session session = jsch.getSession(user, host, port);  
        session.setConfig("StrictHostKeyChecking", "no");  
    //    java.util.Properties config = new java.util.Properties();  
     //   config.put("StrictHostKeyChecking", "no");  
          
        session.setPassword(password);  
        session.connect();  
          
        ChannelExec channelExec = (ChannelExec) session.openChannel("exec");  
        InputStream in = channelExec.getInputStream();  
        channelExec.setCommand(command);  
        channelExec.setErrStream(System.err);  
        channelExec.connect();  
        String out = IOUtils.toString(in, "UTF-8");  
          
        channelExec.disconnect();  
        session.disconnect();  
          
        return out;  
    }  
	
	public void run(String[] cmds) throws IOException, InterruptedException  {  
		for(String cmd : cmds)  
	      {  
	         System.out.print(cmd);  
	         System.out.print(' ');  
	      }  
	      //      */  
	      Process process = Runtime.getRuntime().exec(cmds);  
	      process.waitFor();
    }  
}
