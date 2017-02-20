package com.linkage.module.alarm.customerdispatch.dao;

import java.util.List;
import java.util.Map;

import com.linkage.module.alarm.customerdispatch.serv.mo.CustomerForm;
import com.linkage.system.jdbc.jpa.JPABaseDAO;

public class CustomerDispatchDaoImp extends JPABaseDAO implements CustormerDispatchDao {

	private static final String alias = CustomerDispatchDaoImp.class.getName();
	
	@Override
	public void insert(CustomerForm mo) {
		getSqlSessionTemplate().insert(alias + ".insert", mo);
	}

	@Override
	public List<Map> selectSheet(CustomerForm mo) {
		return getSqlSessionTemplate().selectList(alias + ".selectSheet", mo);
	}
	
	@Override
	public void updateSheet(CustomerForm mo)
	{
		getSqlSessionTemplate().update(alias + ".updateSheet", mo);
	}
	
	@Override
	public void update(Map data) {
		
		getSqlSessionTemplate().update(alias + ".update", data);
	}

	@Override
	public void colse(Map data) {
		getSqlSessionTemplate().update(alias + ".colse", data);
		
	}
}