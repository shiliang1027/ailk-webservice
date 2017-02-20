package com.linkage.module.alarm.customerdispatch.dao;

import java.util.List;
import java.util.Map;

import com.linkage.module.alarm.customerdispatch.serv.mo.CustomerForm;

public interface CustormerDispatchDao {
	
	public List<Map> selectSheet(CustomerForm mo);
	
	public void updateSheet(CustomerForm mo);
	
	public void insert(CustomerForm mo);
	
	public void update(Map data);
	
	public void colse(Map data);
}