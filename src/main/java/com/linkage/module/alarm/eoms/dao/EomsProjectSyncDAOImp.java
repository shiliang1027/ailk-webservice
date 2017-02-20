package com.linkage.module.alarm.eoms.dao;

import java.util.List;
import java.util.Map;

import com.linkage.module.alarm.eoms.serv.mo.NETADJUSTINFO;
import com.linkage.module.alarm.eoms.serv.mo.ProjectSyncMo;
import com.linkage.system.jdbc.jpa.JPABaseDAO;

public class EomsProjectSyncDAOImp extends JPABaseDAO implements
		EomsProjectSyncDAO
{
	private static String alias = EomsProjectSyncDAOImp.class.getName();

	@Override
	public List<Map> query(String sequId) {
		return getSqlSessionTemplate().selectList(alias+".query", sequId);
	}

	@Override
	public void deleteBelong(String sequId) {
		getSqlSessionTemplate().delete(alias+".deleteBelong", sequId);
	}

	@Override
	public void insert(ProjectSyncMo mo) {
		getSqlSessionTemplate().insert(alias+".insert", mo);
	}

	@Override
	public void insertBelong(List<NETADJUSTINFO> info) {
		getSqlSessionTemplate().batchUpdate(alias+".insertBelong", info);
	}

	@Override
	public void update(ProjectSyncMo mo) {
		getSqlSessionTemplate().update(alias+".update", mo);
	}

	@Override
	public void updateStates(ProjectSyncMo mo) {
		getSqlSessionTemplate().update(alias+".updateStates", mo);		
	}

	@Override
	public void updateMoStates(String sequId) {
		getSqlSessionTemplate().update(alias+".updateMoStates", sequId);
	}

	@Override
	public void rebackMoStates(String sequId) {
		getSqlSessionTemplate().update(alias+".rebackMoStates", sequId);		
	}

	@Override
	public List<Map> queryMoInfo(String moName) {
		return getSqlSessionTemplate().selectList(alias+".queryMoInfo", moName);
	}

	@Override
	public List<Map> queryMo(String sequId) {
		return getSqlSessionTemplate().selectList(alias+".queryMo", sequId);
	}

	@Override
	public List<Map> schTask1(long ts) {
		return getSqlSessionTemplate().selectList(alias+".schTask1", ts);	
	}

	@Override
	public List<Map> schTask2(long ts) {
		return getSqlSessionTemplate().selectList(alias+".schTask2", ts);
	}

	@Override
	public List<Map> schTask3(long ts) {
		return getSqlSessionTemplate().selectList(alias+".schTask3", ts);		
	}

	@Override
	public List<Map> schTask4(long ts) {
		return getSqlSessionTemplate().selectList(alias+".schTask4", ts);		
	}

	@Override
	public void updateStates2(ProjectSyncMo mo) {
		getSqlSessionTemplate().update(alias+".updateStates2", mo);
	}
}
