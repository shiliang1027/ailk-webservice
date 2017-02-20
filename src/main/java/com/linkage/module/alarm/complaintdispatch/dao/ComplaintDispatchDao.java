package com.linkage.module.alarm.complaintdispatch.dao;

import java.util.Map;

import com.linkage.module.alarm.complaintdispatch.serv.mo.DispatchSyncMo;
import com.linkage.module.alarm.complaintdispatch.serv.mo.SheetStatusChangeMo;

public interface ComplaintDispatchDao {
	
	public void saveComplaintDispatch(DispatchSyncMo mo);
	
	public void updateShutComplaintDispatch(SheetStatusChangeMo dto);
	
	public void updateComplaintDispatch(SheetStatusChangeMo dto);
	
	public void updateOutTimeComplaintDispatch(Map paramMap);
	
	public void moveComplaintDispatch(Map paramMap);
	
	public void createHisComplaintDispatchTable(Map paramMap);
	
	public void deleteComplaintDispatch(Map paramMap);
	
}
