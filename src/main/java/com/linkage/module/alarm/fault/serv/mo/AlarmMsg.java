package com.linkage.module.alarm.fault.serv.mo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class AlarmMsg implements Msg
{
	private String systemName;
	
	private String serCaller;
	
	private String callerPwd;
	
	private String callUserId;
	
	private String callUserName;
	
	private String serialno;
	
	private String flowId;
	
	private String formNo;
	
	private String formType;
	
	private String clearTime;

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getSerCaller() {
		return serCaller;
	}

	public void setSerCaller(String serCaller) {
		this.serCaller = serCaller;
	}

	public String getCallerPwd() {
		return callerPwd;
	}

	public void setCallerPwd(String callerPwd) {
		this.callerPwd = callerPwd;
	}

	public String getCallUserId() {
		return callUserId;
	}

	public void setCallUserId(String callUserId) {
		this.callUserId = callUserId;
	}

	public String getCallUserName() {
		return callUserName;
	}

	public void setCallUserName(String callUserName) {
		this.callUserName = callUserName;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getFormNo() {
		return formNo;
	}

	public void setFormNo(String formNo) {
		this.formNo = formNo;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getClearTime() {
		return clearTime;
	}

	public void setClearTime(String clearTime) {
		this.clearTime = clearTime;
	}

	public Msg strToObject(String str)
	{
		XStream xstream = new XStream(new DomDriver());
		// 设置节点名
		xstream.alias("req", AlarmMsg.class);
		return (AlarmMsg) xstream.fromXML(str);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map> objectToMap() {
		List<Map> list = new ArrayList<Map>();
		try {
			Map temp = PropertyUtils.describe(this);
			temp.put("alarmuniqueid", this.serialno);
			list.add(temp);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public String getStatus() {
		return null;
	}
}
