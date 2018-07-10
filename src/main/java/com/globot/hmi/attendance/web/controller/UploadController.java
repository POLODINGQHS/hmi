package com.globot.hmi.attendance.web.controller;

import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.globot.hmi.attendance.domain.Picture;
import com.globot.hmi.attendance.service.ILoginService;
import com.globot.hmi.attendance.service.IPitureService;
import com.globot.hmi.attendance.util.CookieUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Ambitous on 2017/12/5.
 */
@Controller
@RequestMapping("/api/upload")
public class UploadController {
	
	@Autowired
	ILoginService loginService;
	
	@Autowired
	IPitureService pictureService;
	
    @RequestMapping("/file")
    @ResponseBody
    public String upload(MultipartFile file,HttpServletRequest request) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("upload");
        System.out.println(path);
        String fileName = file.getOriginalFilename();
        File dir = new File(path,fileName);
        if(!dir.exists()){
            dir.mkdirs();
        }
        //MultipartFile自带的解析方法
        file.transferTo(dir);
        return "ok!";
    }

    @RequestMapping("/headPhoto")
    @ResponseBody
    public Map<String, Object> updatePhoto(HttpServletRequest request, HttpServletResponse response, @RequestParam("myFile") MultipartFile myFile,@RequestParam("job_number") String job_number) {
        Map<String, Object> json = new HashMap<String, Object>();
        try {
            //输出文件后缀名称
            System.out.println(myFile.getOriginalFilename());
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            //图片名称
            String name = df.format(new Date());

            Random r = new Random();
            for (int i = 0; i < 3; i++) {
                name += r.nextInt(10);
            }
            //
            String ext = FilenameUtils.getExtension(myFile.getOriginalFilename());
            //保存图片       File位置 （全路径）   /upload/fileName.jpg
            String url = request.getSession().getServletContext().getRealPath("upload");
            //相对路径
            String path = "/" + job_number + "." + ext;
            File file = new File(url);
            if (!file.exists()) {
                file.mkdirs();
            }
            myFile.transferTo(new File(url + path));
           System.out.println(url+path);
            json.put("success", "/static/img/upload/phono" + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
    
    @RequestMapping("/photoLibrary")
    @ResponseBody
    public String updatePhotoLibrary(HttpServletRequest request, HttpServletResponse response, @RequestParam("uploadPhoto") MultipartFile uploadPhoto,@RequestParam("job_number") String job_number) {
        JSONObject json= new JSONObject();
        try {
        	
            //输出文件后缀名称
        	//String jobNumber = loginService.getUserByToken(CookieUtils.getCookieValue(request,"GLOBOT_TOKEN")).getJobNumber();
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            //图片名称
            String name = df.format(new Date());

            Random r = new Random();
            for (int i = 0; i < 3; i++) {
                name += r.nextInt(10);
            }
            //
            String ext = FilenameUtils.getExtension(uploadPhoto.getOriginalFilename());
            //保存图片       File位置 （全路径）   /upload/fileName.jpg
//            String url = request.getSession().getServletContext().getRealPath("upload")+"/"+job_number;
            String url = "/home/lab640/tomcat/apache-tomcat-7.0.79/webapps/hmi/upload/"+job_number;
            //相对路径
            String path = name + "." + ext;
            File folder = new File(url);
            if (!folder.exists()) {
            	folder.mkdirs();
            }
            uploadPhoto.transferTo(new File(url + "/"+path));
            String detectUrl = "http://localhost:8082/detectface?picpath="+url + "/"+path;
			CloseableHttpClient httpClient = HttpClients.createDefault();
			//创建一个GET对象
			HttpGet get =new HttpGet(detectUrl);
			//执行请求
			CloseableHttpResponse detectResponse =httpClient.execute(get);
			//取响应的结果
			int statusCode =detectResponse.getStatusLine().getStatusCode();
			System.out.println(statusCode);
			HttpEntity entity =detectResponse.getEntity();
			String responseInfo = EntityUtils.toString(entity,"utf-8");
			detectResponse.close();
			httpClient.close();
			if(responseInfo.equals("1")) {
//			if(true) {
				json.put("info", "上传成功");
				Picture picture = new Picture();
	            picture.setJobNumber(job_number);
	            picture.setModifyTime(new Date());
	            picture.setAddTime(new Date());
	            picture.setAvatar((byte) 0);
	            picture.setStatus((byte) 1);
	            picture.setPicUrl(path);
	            picture.setThumbnailUrl(path);
	            System.out.println(pictureService.insertFacePicRecord(picture));
			}
			else if(responseInfo.equals("0")) {
				json.put("info", "该照片未能捕捉到人脸信息，上传失败");
				
				File failedPic = new File(url+"/"+path);
				failedPic.delete();
			}else {
				json.put("info", "connected error");
			}
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json.toString();
    }
}
