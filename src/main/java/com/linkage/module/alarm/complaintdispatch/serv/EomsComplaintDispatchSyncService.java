package com.linkage.module.alarm.complaintdispatch.serv;

import org.apache.log4j.Logger;

import com.linkage.module.alarm.complaintdispatch.dao.ComplaintDispatchDao;
import com.linkage.module.alarm.complaintdispatch.serv.mo.DispatchSyncMo;
import com.linkage.module.service.outer.WebServiceConstant;
import com.linkage.module.service.serv.Service;
import com.linkage.system.designpattern.observer.MapEventObject;
import com.linkage.system.utils.DateTimeUtil;

public class EomsComplaintDispatchSyncService implements Service {
	
	private Logger logger = Logger.getLogger(EomsComplaintDispatchSyncService.class);
	
	private ComplaintDispatchDao complaintDispatchDao;
	
	@Override
	public void process(MapEventObject object) {
		try {
			logger.info("处理建单事件.........");
			String xml = (String) object.getProperty(WebServiceConstant.DATA);
			DispatchSyncMo dto = DispatchSyncMo.fromStr(xml);
			String prosecuteType = dto.getProsecute_type_name();
			logger.info("投诉类别是： "+prosecuteType);
			
			//投诉申告->基础通信->话音基本业务->网络覆盖->住宅室内->曾有覆盖现在无信号
			String[] typeArray = prosecuteType.split("->");
			String firstclass = "";
			String secondclass = "";
			if(typeArray.length>4){
				firstclass = typeArray[2];
				secondclass = typeArray[3];
			}
			dto.setFirstclass(firstclass);
			dto.setSecondclass(secondclass);
			
			String limitTime = dto.getDeal_time_limit();
			DateTimeUtil dt = new DateTimeUtil(limitTime);
			Long limitTimeLong = dt.getLongTime();
			dto.setDealTimeLimit(limitTimeLong);
			
			String acceptTime = dto.getAccept_time();
			DateTimeUtil dt2 = null; 
			if(acceptTime == null)
			{
				dt2 = new DateTimeUtil();
			}
			else
			{
				dt2 = new DateTimeUtil(acceptTime);
			}
			Long acceptTimeLong = dt2.getLongTime();
			
			dto.setSheetStatus("2");
			dto.setAcceptTime(acceptTimeLong);
			
			complaintDispatchDao.saveComplaintDispatch(dto);
			logger.info("工单保存成功");
		} catch (RuntimeException e) {
			logger.error("保存工单失败......",e);
		}
	}

	public void setComplaintDispatchDao(ComplaintDispatchDao complaintDispatchDao) {
		this.complaintDispatchDao = complaintDispatchDao;
	}
}