package com.linkage.module.alarm.customerdispatch.serv;

import org.apache.log4j.Logger;

import com.linkage.module.alarm.customerdispatch.dao.CustormerDispatchDao;
import com.linkage.module.alarm.customerdispatch.serv.mo.CustomerForm;
import com.linkage.module.service.outer.WebServiceConstant;
import com.linkage.module.service.outer.exception.XmlException;
import com.linkage.module.service.outer.mo.RtInfo;
import com.linkage.module.service.serv.Service;
import com.linkage.system.designpattern.observer.MapEventObject;

public class CusComplaintCHGService implements Service
{
	private static Logger log = Logger.getLogger(CusComplaintCHGService.class);
	
	private CustormerDispatchDao dao;
	
	public void setDao(CustormerDispatchDao dao) {
		this.dao = dao;
	}

	@Override
	public void process(MapEventObject object) {
		// 判断
		String xmlData = (String)object.getProperty(WebServiceConstant.DATA);
		
		// 处理消息
		try {
			CustomerForm mo = CustomerForm.strToObject(xmlData);
			log.debug("处理信息CustomerForm=" + mo);
			if (dao.selectSheet(mo).isEmpty()) // 不存在直接插入
			{
				log.debug("不存在直接插入+++++++");
				dao.insert(mo);
			}
			else // 直接更新
			{
				log.debug("存在直接更新+++++++");
				dao.updateSheet(mo);
			}
			log.debug("集客投诉同步成功...");
			object.putProperty(WebServiceConstant.RESULT, new RtInfo("000", "OK").toXML());
		} catch (XmlException xe) {
			log.error("异常信息", xe);
			object.putProperty(WebServiceConstant.RESULT, xe.getMessage());
		} catch (Exception e) {
			log.error("异常信息", e);
			object.putProperty(WebServiceConstant.RESULT, new RtInfo("001", "集客投诉同步处理异常").toXML());
		}
	}
}