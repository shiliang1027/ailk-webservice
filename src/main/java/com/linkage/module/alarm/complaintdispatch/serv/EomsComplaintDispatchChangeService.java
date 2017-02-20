package com.linkage.module.alarm.complaintdispatch.serv;

import org.apache.log4j.Logger;

import com.linkage.module.alarm.complaintdispatch.dao.ComplaintDispatchDao;
import com.linkage.module.alarm.complaintdispatch.serv.mo.SheetStatusChangeMo;
import com.linkage.module.service.outer.WebServiceConstant;
import com.linkage.module.service.serv.Service;
import com.linkage.system.designpattern.observer.MapEventObject;
import com.linkage.system.utils.DateTimeUtil;

public class EomsComplaintDispatchChangeService implements Service {

	private static final Logger logger = Logger.getLogger(EomsComplaintDispatchChangeService.class);
	
	private ComplaintDispatchDao complaintDispatchDao;
	
	@Override
	public void process(MapEventObject object) {
		logger.info("处理投诉工单状态变更事件......");
		String xml = (String) object.getProperty(WebServiceConstant.DATA);
		SheetStatusChangeMo dto = SheetStatusChangeMo.fromStr(xml);
		String htDisc = dto.getHt_description().trim();
		String col2 = dto.getCommon_col2().trim();
		String sheetPart = htDisc+"-"+col2;
		dto.setSheetPart(sheetPart);
		String sheetStatus = getSheetStatus(sheetPart);
		dto.setSheetStatus(sheetStatus);
		if(sheetStatus.contains("7") || sheetStatus.equals("8")){
			logger.info("更新关闭投诉工单");
			String closeTime = dto.getDeal_time();
			DateTimeUtil dt = new DateTimeUtil(closeTime);
			Long dealTimeLong = dt.getLongTime();
			dto.setDealTimeLong(dealTimeLong);
			complaintDispatchDao.updateShutComplaintDispatch(dto);
			logger.info("更新关闭投诉工单成功.....");
		}else{
			complaintDispatchDao.updateComplaintDispatch(dto);
			logger.info("更新投诉工单状态成功.....");
		}
	}
	
	private String getSheetStatus(String sheetPart){
		String sheetStatus = "0";
		if(sheetPart.contains("一级投诉处理-挂起") || sheetPart.contains("二级投诉处理-挂起")){
			sheetStatus = "3";
		}else if(sheetPart.contains("一级投诉处理-移交T2") || sheetPart.contains("二级投诉处理-移交T2")){
			sheetStatus = "2";
		}else if(sheetPart.contains("二级投诉处理-解挂") || sheetPart.contains("一级投诉处理-解挂")){
			sheetStatus = "4";
		}else if(sheetPart.contains("挂起审核")){
			sheetStatus = "5";
		}else if(sheetPart.contains("质检")){
			sheetStatus = "6";
		}else if(sheetPart.contains("归档")){
			sheetStatus = "7";
		}else if(sheetPart.contains("一级投诉处理-回复") || sheetPart.contains("二级投诉处理-回复")){
			sheetStatus = "4";
		}else if(sheetPart.contains("一级投诉处理-驳回")){
			sheetStatus = "8";
		}else {
			logger.error("没有找到该工单对应的状态————"+sheetPart);
		}
		return sheetStatus;
	}
	
	public void setComplaintDispatchDao(ComplaintDispatchDao complaintDispatchDao) {
		this.complaintDispatchDao = complaintDispatchDao;
	}
	
}
