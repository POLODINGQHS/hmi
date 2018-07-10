package com.globot.hmi.attendance.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.globot.hmi.attendance.domain.Employee;
import com.globot.hmi.attendance.domain.FaceRecog;
import com.globot.hmi.attendance.domain.Picture;
import com.globot.hmi.attendance.domain.SignInRecord;
import com.globot.hmi.attendance.dto.SignInRecordDTO;
import com.globot.hmi.attendance.enums.ResultEnum;
import com.globot.hmi.attendance.service.EmployeeServiceImpl;
import com.globot.hmi.attendance.service.FaceRecogServiceImpl;
import com.globot.hmi.attendance.service.IEmployeeService;
import com.globot.hmi.attendance.service.IFaceRecogService;
import com.globot.hmi.attendance.service.ILoginService;
import com.globot.hmi.attendance.service.IPitureService;
import com.globot.hmi.attendance.service.ISignInRecordService;
import com.globot.hmi.attendance.util.result.WebResult;
import com.mysql.fabric.xmlrpc.base.Array;

@Controller
@RequestMapping("/api/picture")
public class PictureController {

	@Autowired
    ILoginService loginService;
	
	@Autowired
	IPitureService pictureService;
	
	@Autowired
	ISignInRecordService signInRecordService;
	
	@Autowired
	IFaceRecogService faceRecogService;
	
	@Autowired
	IEmployeeService employeeService;

	@RequestMapping("/facePictures/{jobNumber}")
	@ResponseBody
	public WebResult<String> getFacePictures(@PathVariable String jobNumber) {
		JSONArray array = new JSONArray();
//		Employee employee=loginService.getUserByToken(token);
//		String jobNumber = employee.getJobNumber();
		List<String> picUrl = new ArrayList<String>();
		List<Picture> pictures = pictureService.getFacePicByJobNumber(jobNumber);
		for(int i=0;i<pictures.size();i++) {
			JSONObject object = new JSONObject();
			object.put("id",pictures.get(i).getId());
			object.put("url","/hmi/upload/"+jobNumber+"/"+pictures.get(i).getPicUrl());
//			object.put(pictures.get(i).getId().toString(), "/hmi/upload/"+jobNumber+"/"+pictures.get(i).getPicUrl());
			array.add(object);
//			picUrl.add("/hmi/upload/"+jobNumber+"/"+pictures.get(i).getPicUrl());
		}
//		List<SignInRecord> signIn = signInRecordService.getRecordByJobNumber(jobNumber);
//		for(int i=0;i<signIn.size();i++) {
//			picUrl.add("/hmi/signin/"+jobNumber+"/"+signIn.get(i).getSignInPic());
//		}
		WebResult<String> webResut = new WebResult<>(ResultEnum.SUCCESS);
		webResut.setData(array.toString());
		return webResut;
	}
	
	@RequestMapping("/deletefacePictures/{id}")
	@ResponseBody
	public WebResult<String> deleteFacePictures(HttpServletRequest request, HttpServletResponse response,@PathVariable int id) {
		Picture picture = pictureService.selectByPrimaryKey(id);
		pictureService.deleteByPrimaryKey(id);
		//String url = request.getSession().getServletContext().getRealPath("upload")+"/"+picture.getJobNumber();
		String url = "/home/lab640/tomcat/apache-tomcat-7.0.79/webapps/hmi/upload/"+picture.getJobNumber();
		String path = picture.getPicUrl();
//		String url = "";
//		String path = jobNumber+"/"+picName;
		File file = new File(url+"/"+path);
		if (file.exists()) {
			file.delete();
		}else{
			
		}
		WebResult<String> webResut = new WebResult<>(ResultEnum.SUCCESS);
		webResut.setData("1");
		return webResut;
	}
	
	@RequestMapping("/openCamera/{token}")
	@ResponseBody
	public WebResult<String> openCamera(@PathVariable String token) throws Exception {
		String jobNumber = loginService.getUserByToken(token).getJobNumber();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        //图片名称
        String name = df.format(new Date());
        Random r = new Random();
        for (int i = 0; i < 3; i++) {
            name += r.nextInt(10);
        }
		String url = "http://localhost:8082/takepic?picpath=/home/lab640/tomcat/apache-tomcat-7.0.79/webapps/hmi/upload/"+jobNumber+"/"+name+".jpg";
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
	}
	
	@RequestMapping("/train/{jobNumber}")
	@ResponseBody
	public WebResult<String> train(@PathVariable String jobNumber) throws Exception {
		System.out.println(jobNumber);
		String url = "http://10.112.59.158:8082/train?name=&number="+jobNumber+"&picfoldpath=/home/lab640/tomcat/apache-tomcat-7.0.79/webapps/hmi/upload/"+jobNumber;
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
		response.close();
		httpClient.close();
		if(string.equals("success")){
			String eng_name = employeeService.getEmployeeByJobNumber(jobNumber).getMemo();
			String fileLocation = "/home/lab640/face_HP/face_file/face_encoding_"+jobNumber+".csv";
			FaceRecog face = new FaceRecog();
			face.setId(jobNumber);
			face.setFileLocation(fileLocation);
			face.setUsername(eng_name);
			faceRecogService.insertFaceRecog(face);
			WebResult<String> webResut = new WebResult<>(ResultEnum.SUCCESS);
			webResut.setData(string);
			return webResut;
		}else{
			WebResult<String> webResut = new WebResult<>(ResultEnum.UNKONW_ERROR);
			webResut.setData(string);
			return webResut;
		}
		//关闭httpclient
		
		
	}
	
}
