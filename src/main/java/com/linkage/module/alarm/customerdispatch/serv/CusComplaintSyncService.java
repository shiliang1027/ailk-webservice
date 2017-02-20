package com.linkage.module.alarm.customerdispatch.serv;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.linkage.module.alarm.customerdispatch.dao.CustormerDispatchDao;
import com.linkage.module.service.outer.WebServiceConstant;
import com.linkage.module.service.outer.mo.RtInfo;
import com.linkage.module.service.serv.Service;
import com.linkage.system.designpattern.observer.MapEventObject;

public class CusComplaintSyncService implements Service
{
	private static Logger log = Logger.getLogger(CusComplaintSyncService.class);

	private CustormerDispatchDao dao;
	
	public void setDao(CustormerDispatchDao dao) {
		this.dao = dao;
	}

	@Override
	public void process(MapEventObject object) {
		// 判断
		String xmlData = (String)object.getProperty(WebServiceConstant.DATA);
		if(xmlData.indexOf("<form>")!=-1)
		{
			xmlData = xmlData.substring(xmlData.indexOf("<form>"), xmlData.indexOf("</form>")+7);
		}
		// 处理消息
		try {
			SAXReader reader = new SAXReader(); 
			Document doc = reader.read(new ByteArrayInputStream(new StringBuilder("<?xml version=\"1.0\" encoding=\"gbk\"?>")
				.append(xmlData).toString().getBytes())); 
			Element root = doc.getRootElement();
			
			Map data = new HashMap();
			data.put("flowId", root.elementText("flowId"));
			String htName = root.elementText("htName");
			String isSolve = root.elementText("isSolve");
			// 理解为归档
			if("Y".equals(isSolve))
			{
				data.put("closetime", System.currentTimeMillis()/1000);
				dao.colse(data);
				log.debug("集客投诉归档成功...");
			}
			else
			{
				if("rwcd".equals(htName))
				{
					data.put("sheetPart", "任务拆单");
				}
				else if("qtzyzcl".equals(htName))
				{
					data.put("sheetPart", "其他专业组处理");
				}
				else if("fxycl".equals(htName))
				{
					data.put("sheetPart", "分析预处理");
				}
				else if("jtzhdwcl".equals(htName))
				{
					data.put("sheetPart", "集团综合代维处理组");
				}
				else if("csggwgzcl".equals(htName))
				{
					data.put("sheetPart", "传输骨干网");
				}
				else
				{
					data.put("sheetPart", "未知");
				}
				dao.update(data);
				log.debug("集客投诉更新成功...");
			}
			object.putProperty(WebServiceConstant.RESULT, new RtInfo("000", "OK").toXML());
		} catch (Exception e) {
			log.error("异常信息", e);
			object.putProperty(WebServiceConstant.RESULT, new RtInfo("001", "集客投诉状态同步处理异常").toXML());
		}
	}
}