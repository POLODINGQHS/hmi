package com.globot.hmi.attendance.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTaskService {
	private static final SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
	
	@Scheduled(fixedRate=5000)
	public void reportCurrentTime(){
		System.out.println("每隔5秒钟执行一次"+dateFormat.format(new Date()));
	}
	
	@Scheduled(cron="0 28 11 ? * *")
	//11点28分执行
	public void fixTimeExecution() {
		System.out.println("在指定时间"+dateFormat.format(new Date())+"执行");
	}
}
