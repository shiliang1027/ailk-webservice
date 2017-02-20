package com.linkage.module.alarm.eoms.serv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.linkage.module.alarm.eoms.dao.EomsProjectSyncDAO;
import com.linkage.module.alarm.eoms.serv.mo.NETADJUSTINFO;
import com.linkage.module.alarm.eoms.serv.mo.ProjectSyncMo;
import com.linkage.module.service.outer.WebServiceConstant;
import com.linkage.module.service.outer.exception.XmlException;
import com.linkage.module.service.outer.mo.RtInfo;
import com.linkage.module.service.serv.Service;
import com.linkage.system.designpattern.observer.MapEventObject;
import com.linkage.system.utils.corba.CorbaMsgSupport;
import com.linkage.system.utils.corba.NodeUtil;

public class EomsProjectSyncService implements Service
{
	private static Logger log = Logger.getLogger(EomsProjectSyncService.class);
	
	// 网元状态变更Topic
	public static String MoWorkStatusChangeTopic = "/Resource/abims/ResourceChange/MoWorkStatusChange";

	private EomsProjectSyncDAO dao;
	
	private CorbaMsgSupport cms;
	
	public void setDao(EomsProjectSyncDAO dao) {
		this.dao = dao;
	}

	public void setCms(CorbaMsgSupport cms) {
		this.cms = cms;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void process(MapEventObject object) {
		// 判断
		String xmlData = (String)object.getProperty(WebServiceConstant.DATA);
		
		// 处理消息
		try {
			ProjectSyncMo mo = ProjectSyncMo.strToObject(xmlData);
			
			switch (Integer.parseInt(mo.getFLAG())) {
				// 信息发布
				case 0:
					// 查询是否含有工程编号
					List<Map> data = dao.query(mo.getINFO_ID());
					
					List<Map> moInfo = null;
					// 查询网元对应系统中的编码
					if(mo.getNetadjust()!=null && !mo.getNetadjust().isEmpty())
					{
						for(NETADJUSTINFO info : mo.getNetadjust())
						{
							moInfo = dao.queryMoInfo(info.getNENAME());
							if(moInfo==null || moInfo.isEmpty())
							{
								info.setMO_ID("");
								continue;
							}
							info.setMO_ID((String)moInfo.get(0).get("mo_id"));
						}
					}
					if(mo.getAffect()!=null && !mo.getAffect().isEmpty())
					{
						for(NETADJUSTINFO info : mo.getAffect())
						{
							moInfo = dao.queryMoInfo(info.getNENAME());
							if(moInfo==null || moInfo.isEmpty())
							{
								info.setMO_ID("");
								continue;
							}
							info.setMO_ID((String)moInfo.get(0).get("mo_id"));
						}
					}
					
					// 没有
					if(data==null
							|| data.isEmpty())
					{
						// 插入工程信息表
						dao.insert(mo);
						// 插入工程网元表
						if(mo.getNetadjust()!=null && !mo.getNetadjust().isEmpty())
						{
							dao.insertBelong(mo.getNetadjust());
						}
						if(mo.getAffect()!=null && !mo.getAffect().isEmpty())
						{
							dao.insertBelong(mo.getAffect());
						}
						log.debug("新增工程信息成功...");
					}
					// 有
					else
					{
						// 工程状态已经开始实施
						if(!"2".equals(data.get(0).get("is_cancel")))
						{
							object.putProperty(WebServiceConstant.RESULT, new RtInfo("001", mo.getINFO_ID()+"工程已经开始实施").toXML());
							return;
						}
						
						// 更新工程信息表
						dao.update(mo);
						// 先删除
						dao.deleteBelong(mo.getINFO_ID());
						// 更新工程网元表
						if(mo.getNetadjust()!=null && !mo.getNetadjust().isEmpty())
						{
							dao.insertBelong(mo.getNetadjust());
						}
						if(mo.getAffect()!=null && !mo.getAffect().isEmpty())
						{
							dao.insertBelong(mo.getAffect());
						}
						log.debug("修改工程信息成功...");
					}
					break;
				// 开始确认
				case 1:
					// 更新工程信息表
					dao.updateStates(mo);
					// 更新网元表
					dao.updateMoStates(mo.getINFO_ID());
					// 发工程变更消息
					sendMsg(mo);
					log.debug("开始确认工程信息成功...");
					break;
				// 结束确认
				case 2:
					// 更新工程信息表
					dao.updateStates(mo);
					// 更新网元表
					dao.rebackMoStates(mo.getINFO_ID());
					// 发送消息
					sendMsg(mo);
					// 删除工程网元表
					dao.deleteBelong(mo.getINFO_ID());
					log.debug("删除工程信息成功...");
					break;
				default:
					break;
			}
			object.putProperty(WebServiceConstant.RESULT, new RtInfo("000", "OK").toXML());
		} catch (XmlException xe) {
			log.debug("解析文档出错", xe);
			object.putProperty(WebServiceConstant.RESULT, xe.getMessage());
		} catch (Exception e) {
			log.debug("工程预约处理异常", e);
			object.putProperty(WebServiceConstant.RESULT, new RtInfo("001", "工程预约处理异常").toXML());
		}
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
			nodeAttrs.put("work_stat", mo.getFLAG());
			tmp = new HashMap();
			tmp.put("nodeAttrs", nodeAttrs);
			subNodes.add(tmp);
		}
		if(!subNodes.isEmpty())
		{
			// 发工程变更消息
			Map data = NodeUtil.createElement("", "", null, subNodes);
			log.debug("发送网元变更记录" + data);
			cms.sendMsg(MoWorkStatusChangeTopic, data);
		}
	}
}