package com.linkage.module.alarm.complaintdispatch.serv.sheduler;

import it.sauronsoftware.cron4j.Scheduler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.linkage.module.alarm.complaintdispatch.dao.ComplaintDispatchDao;
import com.linkage.system.utils.DateTimeUtil;
import com.linkage.system.utils.SystemProperties;

public class ComplaintShedulerTask {
	
	private ComplaintDispatchDao complaintDispatchDao;
	
	public void init(){
		
		//启动扫描投诉工单表调度器，每小时执行一次
		Scheduler scheduler = new Scheduler();
		// Schedules the task, once every hour.
		scheduler.schedule(SystemProperties.get("com.linkage.alarm.complaintDispatch.scanTable.cron"),
				new ScanComplaintDispatchTable());
		scheduler.start();
		
		//启动投诉工单创建历史表调度器,每月28日执行
		Scheduler scheduler2 = new Scheduler();
		scheduler2.schedule(SystemProperties.get("com.linkage.alarm.complaintDispatch.createTable.cron"),
				new CreateTable());
		scheduler2.start();
	}

	public void setComplaintDispatchDao(ComplaintDispatchDao complaintDispatchDao) {
		this.complaintDispatchDao = complaintDispatchDao;
	}
	
	class ScanComplaintDispatchTable implements Runnable{
		
		@Override
		public void run(){
			
			
			Date date = new Date();
			Long currTime = date.getTime()/1000;
			Map paramMap = new HashMap();
			paramMap.put("currTime", currTime);
			
			Long moveTime = currTime-24*60*60;
			paramMap.put("time", moveTime);
			
			DateTimeUtil dt = new DateTimeUtil(date.getTime());
			String dateTime = dt.getLongDate();
			String tablePrefix = dateTime.substring(0,7);
			tablePrefix = tablePrefix.replace("-", "_");
			String tableName = "ta_hiscomplaint_"+tablePrefix;
			paramMap.put("tableName", tableName);
			
			//转移已经关闭的工单至投诉工单历史表
			//删除在投诉工单表中已经关闭一天以上的投诉工单
			complaintDispatchDao.moveComplaintDispatch(paramMap);
			complaintDispatchDao.deleteComplaintDispatch(paramMap);
			
			//更新投诉工单超时状态，一小时执行一次
			complaintDispatchDao.updateOutTimeComplaintDispatch(paramMap);
		}
		
	}
	
	class CreateTable implements Runnable{
		
		@Override
		public void run(){
			
			DateTimeUtil dt = new DateTimeUtil(new Date().getTime());
			String date = dt.getNextDate("month", 1);
			String tableprefix = date.substring(0,7);
			tableprefix = tableprefix.replace("-", "_");
			String tableName = "TA_HISCOMPLAINT_"+tableprefix;
			String pkTableName = "PK_TA_HISCOMPLAINT_"+tableprefix;
			Map paramMap = new HashMap();
			paramMap.put("tableName", tableName);
			paramMap.put("pkTableName", pkTableName);
			
			complaintDispatchDao.createHisComplaintDispatchTable(paramMap);
		}
	}
}