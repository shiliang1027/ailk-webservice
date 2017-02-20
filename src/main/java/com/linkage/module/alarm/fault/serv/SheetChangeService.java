package com.linkage.module.alarm.fault.serv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.linkage.module.alarm.fault.dao.OverTimeDAO;
import com.linkage.module.alarm.fault.serv.mo.Msg;
import com.linkage.module.alarm.fault.serv.mo.QueueMsg;
import com.linkage.module.service.outer.WebServiceConstant;
import com.linkage.module.service.outer.mo.RtInfo;
import com.linkage.module.service.serv.Service;
import com.linkage.system.designpattern.observer.MapEventObject;
import com.linkage.system.utils.corba.CorbaMsgSupport;
import com.linkage.system.utils.corba.NodeUtil;

public class SheetChangeService<T extends Msg> implements Service
{
	private static Logger log = Logger.getLogger(SheetChangeService.class);
	
	// 网元状态变更Topic
	private static String SheetStatusChangeTopic = "/Alarm/Abims/AlarmChange/SheetStatusChange";

	private OverTimeDAO dao;
	
	private CorbaMsgSupport ac;
	
	private Class<T> targetInterface;
	
	private BlockingQueue<QueueMsg> bkqueue = null;
	
	private long lazyTime = 30000;
	
	public void setDao(OverTimeDAO dao) {
		this.dao = dao;
	}

	public void setAc(CorbaMsgSupport ac) {
		this.ac = ac;
	}

	public void setLazyTime(long lazyTime)
	{
		this.lazyTime = lazyTime;
	}
	
	public void setTargetInterface(Class<T> targetInterface) {
		this.targetInterface = targetInterface;
	}

	public SheetChangeService()
	{
		bkqueue = new LinkedBlockingQueue<QueueMsg>();
		new SendThread().start();
		new Thread()
		{

			public void run()
			{
				QueueMsg qmg = null;
				long minusTime = 0;
				while(true)
				{
					try
					{
						qmg = bkqueue.take();
						minusTime = qmg.getQueueTime() + lazyTime - System.currentTimeMillis();
						if(minusTime > 0)
						{
							sleep(minusTime);
						}
						dealSheetMsg(qmg);
					}
					catch (InterruptedException ie)
					{
						ie.printStackTrace();
						log.debug("线程正常退出");
						break;
					}
					catch (Exception e)
					{
						log.debug("线程出现异常");
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	@Override
	public void process(MapEventObject object) {
		log.debug("工单变更业务类收到信息缓存，等待线程处理");
		bkqueue.offer(new QueueMsg(object));
	}

	private void dealSheetMsg(QueueMsg qmg)
	{
		// 判断
		String xmlData = (String)qmg.getMsgObject().getProperty(WebServiceConstant.DATA);
		
		// 处理消息
		try {
			Msg mo = targetInterface.newInstance().strToObject(xmlData);
			if(!"-1".equals(mo.getStatus()))
			{
				// 发消息
				sendMsg(mo.objectToMap());
				// 更新数据库
				dao.syncSheetStatus(mo.objectToMap());
				log.debug("故障工单回复成功...");
			}
			qmg.getMsgObject().putProperty(WebServiceConstant.RESULT, new RtInfo("000", "OK").toXML());
		} catch (Exception e) {
			log.error("异常,", e);
			qmg.getMsgObject().putProperty(WebServiceConstant.RESULT, new RtInfo("001", "普通工单同步处理异常").toXML());
		}
	}
	
	@SuppressWarnings("unchecked")
	private void sendMsg(List<Map> moList) {
		log.debug("接受工单变更记录" + moList);
		List<Map> subNodes = null;
		String sheetpart = null;
		for(Map mo : moList)
		{
			subNodes = new ArrayList<Map>();
			// 查询工单的告警序列号
			List<Map> data = dao.querySheet(mo);
			if (data == null || data.isEmpty())
			{
				continue;
			}
			sheetpart = String.valueOf(mo.get("sheetpart"));
			for (Map map : data)
			{
				log.debug("工单变更记录:sheetno=" + mo.get("sheetno") + ",sheetpart=" + map.get("sheetpart"));
				if(!sheetpart.equals(String.valueOf(map.get("sheetpart"))))
				{
					Map nodeAttrs = new HashMap();
					nodeAttrs.put("sheetpart", sheetpart);
					nodeAttrs.put("alarmuniqueid", map.get("serialno"));
					nodeAttrs.put("sheetstatus", String.valueOf(mo.get("sheetstatus")));
					nodeAttrs.put("sheetno",  String.valueOf(mo.get("sheetno")));
					nodeAttrs.put("sheetstarttime",  String.valueOf(mo.get("sheetstarttime")));
					Map tmp = new HashMap();
					tmp.put("nodeAttrs", nodeAttrs);
					subNodes.add(tmp);
				}
			}
			if (!mo.containsKey("sheettime"))
			{
				mo.put("sheettime", "0");
			}
			
			synchronized (sendMap) {
				sendMap.put(String.valueOf(mo.get("sheetno")), subNodes);
			}
		}
	}
	

	private Map<String,List<Map>> sendMap = new ConcurrentHashMap<String, List<Map>>();
	class SendThread extends Thread
	{
		public void run()
		{
			Map<String,List<Map>> map = new ConcurrentHashMap<String, List<Map>>();
			while(true)
			{
				try {
					map.clear();
					synchronized (sendMap) {
						map.putAll(sendMap);
						sendMap.clear();
					}
					
					Iterator<List<Map>>  it = map.values().iterator();
					while(it.hasNext())
					{
						List<Map> subNodes = it.next();
						if(!subNodes.isEmpty())
						{
							// 发送工单变更记录
							Map data = NodeUtil.createElement("", "", null, subNodes);
							log.info("发送工单变更记录" + data);
							ac.sendMsg(SheetStatusChangeTopic, data);
							Thread.sleep(50);
						}
					}
					
					
					Thread.sleep(2000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}