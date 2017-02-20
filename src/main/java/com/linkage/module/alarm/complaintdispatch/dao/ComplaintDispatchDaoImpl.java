package com.linkage.module.alarm.complaintdispatch.dao;

import java.util.Map;

import com.linkage.module.alarm.complaintdispatch.serv.mo.DispatchSyncMo;
import com.linkage.module.alarm.complaintdispatch.serv.mo.SheetStatusChangeMo;
import com.linkage.system.jdbc.jpa.JPABaseDAO;

public class ComplaintDispatchDaoImpl extends JPABaseDAO implements ComplaintDispatchDao {

	private static final String alias = ComplaintDispatchDaoImpl.class.getName();
	@Override
	public void saveComplaintDispatch(DispatchSyncMo mo) {
		
		getSqlSessionTemplate().insert(alias + ".saveComplaintDispath", mo);
	}

	@Override
	public void updateComplaintDispatch(SheetStatusChangeMo dto) {
		
		getSqlSessionTemplate().update(alias + ".updateComplaintDispatch", dto);
	}

	@Override
	public void updateShutComplaintDispatch(SheetStatusChangeMo dto) {
		getSqlSessionTemplate().update(alias + ".updateShutComplaintDispatch", dto);
		
	}

	@Override
	public void moveComplaintDispatch(Map paramMap) {
		getSqlSessionTemplate().insert(alias + ".moveComplaintDispatch", paramMap);
	}

	@Override
	public void updateOutTimeComplaintDispatch(Map paramMap) {
		getSqlSessionTemplate().update(alias + ".updateOutTimeComplaintDispatch",paramMap);
		
	}

	@Override
	public void createHisComplaintDispatchTable(Map paramMap) {
		getSqlSessionTemplate().update(alias + ".createComplaintTable",paramMap);
	}

	@Override
	public void deleteComplaintDispatch(Map paramMap) {
		getSqlSessionTemplate().update(alias + ".deleteComplaintDispatch",paramMap);
	}
}