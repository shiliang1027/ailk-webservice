package com.linkage.module.alarm.fault.sch;

import it.sauronsoftware.cron4j.Scheduler;

import com.linkage.module.alarm.fault.dao.OverTimeDAO;
import com.linkage.system.utils.SystemProperties;

public class SchedulerTask
{
	private OverTimeDAO dao;
	
	public void setDao(OverTimeDAO dao) {
		this.dao = dao;
	}

	public void init()
	{
		// 扫描已关闭工单
		Scheduler scheduler = new Scheduler();
		// Schedules the task, once every minute.
		scheduler.schedule(SystemProperties.get("com.linkage.alarm.fault.sch.moveDispatch.cron"),
				new moveDispatch());
		// Starts the scheduler.
		scheduler.start();
		
		// 超时工单逻辑
		Scheduler scheduler2 = new Scheduler();
		// Schedules the task, once every minute.
		scheduler2.schedule(SystemProperties.get("com.linkage.alarm.fault.sch.sendOverTimeMsg.cron"),
				new sendOverTimeMsg());
		// Starts the scheduler.
		scheduler2.start();
		
		// 创建分区空间
		Scheduler scheduler3 = new Scheduler();
		// Schedules the task, once every minute.
		scheduler3.schedule(SystemProperties.get("com.linkage.alarm.fault.sch.createTableSpace.cron"),
				new createTableSpace());
		// Starts the scheduler.
		scheduler3.start();
	}
	
	class moveDispatch implements Runnable
	{
		@Override
		public void run() {
			dao.moveDispatch();
		}
	}
	
	class sendOverTimeMsg implements Runnable
	{
		@Override
		public void run() {
			dao.sendOverTimeMsg();
		}
	}
	
	class createTableSpace implements Runnable
	{
		@Override
		public void run() {
			dao.createTableSpace();
		}
	}
}
