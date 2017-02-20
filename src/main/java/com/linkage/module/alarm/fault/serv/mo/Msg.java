package com.linkage.module.alarm.fault.serv.mo;

import java.util.List;
import java.util.Map;

public interface Msg
{
	Msg strToObject(String str);
	
	@SuppressWarnings("unchecked")
	List<Map> objectToMap();
	
	String getStatus();
}