package com.linkage.module.alarm.eoms.sch;

import it.sauronsoftware.cron4j.Scheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.linkage.module.alarm.eoms.dao.EomsProjectSyncDAO;
import com.linkage.module.alarm.eoms.serv.EomsProjectSyncService;
import com.linkage.module.alarm.eoms.serv.mo.ProjectSyncMo;
import com.linkage.system.utils.SystemProperties;
import com.linkage.system.utils.corba.CorbaMsgSupport;
import com.linkage.system.utils.corba.NodeUtil;

public class SchedulerTask
{
	private static Logger log = Logger.getLogger(EomsProjectSyncService.class);
	
	private EomsProjectSyncDAO dao;
	private CorbaMsgSupport cms;

	public void setDao(EomsProjectSyncDAO dao) {
		this.dao = dao;
	}

	public void setCms(CorbaMsgSupport cms) {
		this.cms = cms;
	}

	public void init()
	{
		// 扫描需要处理的工程
		Scheduler scheduler = new Scheduler();
		// Schedules the task, once every minute.
		scheduler.schedule(SystemProperties.get("com.linkage.alarm.eoms.cron"),
				new EomsProjectTask());
		// Starts the scheduler.
		scheduler.start();
		log.info("扫描需要处理的工程任务启动成功...");
	}
	
	class EomsProjectTask implements Runnable
	{
		@Override
		public void run() {
			log.info("扫描需要处理的工程任务启动...");
			// 查询
			long ts = System.currentTimeMillis();
			
			String proj_id = null;
			// Task1 查询可以开始的工程
			List<Map> moList = dao.schTask1(ts);
			for(Map m : moList)
			{
				// 工程编号
				proj_id = String.valueOf(m.get("proj_id"));
				ProjectSyncMo mo = new ProjectSyncMo();
				mo.setINFO_ID(proj_id);
				mo.setFLAG("1");
				// 更新工程信息表
				dao.updateStates(mo);
				dao.updateStates2(mo);
				// 更新网元表
				dao.updateMoStates(mo.getINFO_ID());
				// 发工程变更消息
				sendMsg(mo);
				log.info("确认工程信息成功...");
			}
			
			// Task2 查询可以结束的工程
			moList = dao.schTask2(ts);
			for(Map m : moList)
			{
				// 工程编号
				proj_id = String.valueOf(m.get("proj_id"));
				ProjectSyncMo mo = new ProjectSyncMo();
				mo.setINFO_ID(proj_id);
				mo.setFLAG("2");
				// 更新工程信息表
				dao.updateStates(mo);
				dao.updateStates2(mo);
				// 更新网元表
				dao.rebackMoStates(mo.getINFO_ID());
				// 发工程变更消息
				sendMsg(mo);
				// 删除工程网元表
				dao.deleteBelong(mo.getINFO_ID());
				log.info("删除工程信息成功...");
			}
			
			// Task3 清除作废工程
			moList = dao.schTask3(ts);
			for(Map m : moList)
			{
				// 工程编号
				proj_id = String.valueOf(m.get("proj_id"));
				ProjectSyncMo mo = new ProjectSyncMo();
				mo.setINFO_ID(proj_id);
				mo.setFLAG("3");
				// 更新工程信息表
				dao.updateStates(mo);
				dao.updateStates2(mo);
				// 删除工程网元表
				dao.deleteBelong(mo.getINFO_ID());
				log.info("删除工程信息成功...");
			}
			
			// Task4 清除不启用工程
			moList = dao.schTask4(ts);
			for(Map m : moList)
			{
				// 工程编号
				proj_id = String.valueOf(m.get("proj_id"));
				ProjectSyncMo mo = new ProjectSyncMo();
				mo.setINFO_ID(proj_id);
				mo.setFLAG("3");
				// 更新工程信息表
				dao.updateStates(mo);
				dao.updateStates2(mo);
				// 更新网元表
				dao.rebackMoStates(mo.getINFO_ID());
				// 发工程变更消息
				sendMsg(mo);
				// 删除工程网元表
				dao.deleteBelong(mo.getINFO_ID());
				log.info("删除工程信息成功...");
			}
			
			log.info("扫描需要处理的工程任务结束...");
		}
		
		@SuppressWarnings("unchecked")
		private void sendMsg(ProjectSyncMo mo) {
			// 查询工程网元
			Map nodeAttrs = null;
			Map tmp = null;
			List<Map> subNodes = new ArrayList<Map>();
			List<Map> moList = dao.queryMo(mo.getINFO_ID());
			if(moList==null || moList.isEmpty())
			{
				return;
			}
			for(Map temp : moList)
			{
				nodeAttrs = new HashMap();
				nodeAttrs.put("mo_id", temp.get("mo_id"));
				nodeAttrs.put("projectid", mo.getINFO_ID());
				nodeAttrs.put("work_stat", "3".equals(mo.getFLAG())?"2":mo.getFLAG());
				tmp = new HashMap();
				tmp.put("nodeAttrs", nodeAttrs);
				subNodes.add(tmp);
			}
			if(!subNodes.isEmpty())
			{
				// 发工程变更消息
				Map data = NodeUtil.createElement("", "", null, subNodes);
				log.info("发送网元变更记录" + data);
				cms.sendMsg(EomsProjectSyncService.MoWorkStatusChangeTopic, data);
			}
		}
	}
}
