package com.linkage.module.alarm.fault.serv.mo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import com.linkage.commons.util.DateTimeUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class FaultMsg implements Msg
{
	private String title;
	
	private String formNo;
	
	private String startTime;
	
	private String state;
	
	private String acceptLimitTime;
	
	private String dealLimitTime;
	
	private String warmFlowId;
	
	private String netClassOne;
	
	private String netClassTwo;
	
	private String netClassThree;
	
	private String t1DelaLimitTime;
	
	private String t2DelaLimitTime;
	
	private String t3DelaLimitTime;
	
	private String faultAnswdist;
	
	private String dealDept;
	
	private String sheetstatus;
	
	private String sheetpart;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFormNo() {
		return formNo;
	}

	public void setFormNo(String formNo) {
		this.formNo = formNo;
	}

	public String getStartTime() {
		return String.valueOf(new DateTimeUtil(startTime).getLongTime());
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getAcceptLimitTime() {
		return acceptLimitTime;
	}

	public void setAcceptLimitTime(String acceptLimitTime) {
		this.acceptLimitTime = acceptLimitTime;
	}

	public String getDealLimitTime() {
		return String.valueOf(new DateTimeUtil(dealLimitTime).getLongTime());
	}

	public void setDealLimitTime(String dealLimitTime) {
		this.dealLimitTime = dealLimitTime;
	}

	public String getWarmFlowId() {
		return warmFlowId;
	}

	public void setWarmFlowId(String warmFlowId) {
		this.warmFlowId = warmFlowId;
	}

	public String getNetClassOne() {
		return netClassOne;
	}

	public void setNetClassOne(String netClassOne) {
		this.netClassOne = netClassOne;
	}

	public String getNetClassTwo() {
		return netClassTwo;
	}

	public void setNetClassTwo(String netClassTwo) {
		this.netClassTwo = netClassTwo;
	}

	public String getNetClassThree() {
		return netClassThree;
	}

	public void setNetClassThree(String netClassThree) {
		this.netClassThree = netClassThree;
	}

	public String getT1DelaLimitTime() {
		return t1DelaLimitTime;
	}

	public void setT1DelaLimitTime(String t1DelaLimitTime) {
		this.t1DelaLimitTime = t1DelaLimitTime;
	}

	public String getT2DelaLimitTime() {
		return t2DelaLimitTime;
	}

	public void setT2DelaLimitTime(String t2DelaLimitTime) {
		this.t2DelaLimitTime = t2DelaLimitTime;
	}

	public String getT3DelaLimitTime() {
		return t3DelaLimitTime;
	}

	public void setT3DelaLimitTime(String t3DelaLimitTime) {
		this.t3DelaLimitTime = t3DelaLimitTime;
	}

	public String getFaultAnswdist() {
		return faultAnswdist;
	}

	public void setFaultAnswdist(String faultAnswdist) {
		this.faultAnswdist = faultAnswdist;
	}

	public String getDealDept() {
		return dealDept;
	}

	public void setDealDept(String dealDept) {
		this.dealDept = dealDept;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getSheetstatus() {
		if(state.contains("T1")
				|| state.contains("T2")
				|| state.contains("T3"))
		{
			sheetstatus = "3";
		}
		else if(state.contains("待受理"))
		{
			sheetstatus = "2";
		}
		else if(state.contains("待归档"))
		{
			sheetstatus = "6";
		}
		else if(state.contains("延期解决审核中"))
		{
			sheetstatus = "5";
		}
		else if(state.contains("完成"))
		{
			sheetstatus = "7";
		}
		else if(state.contains("告警验证"))
		{
			sheetstatus = "11";
		}
		else
		{
			sheetstatus = "-1";
		}
		
		return sheetstatus;
	}

	public void setSheetstatus(String sheetstatus) {
		this.sheetstatus = sheetstatus;
	}

	public String getSheetpart() {
		if(state.contains("待归档"))
		{
			sheetpart = "待归档";
		}
		else if(state.contains("延期解决审核中"))
		{
			sheetpart = "延期解决审核中";
		}
		else if(state.contains("完成"))
		{
			sheetpart = "已归档";
		}
		else if(state.contains("待受理"))
		{
			sheetpart = "待受理";
		}
		else
		{
			sheetpart = state;
		}
		
		return sheetpart;
	}

	public void setSheetpart(String sheetpart) {
		this.sheetpart = sheetpart;
	}

	public Msg strToObject(String str)
	{
		str = str.substring(str.indexOf("<form>"), str.indexOf("</form>")+7);
		
		XStream xstream = new XStream(new DomDriver());
		// 设置节点名
		xstream.alias("form", FaultMsg.class);
		return (FaultMsg) xstream.fromXML(str);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map> objectToMap() {
		List<Map> list = new ArrayList<Map>();
		try {
			Map temp = PropertyUtils.describe(this);
			temp.put("alarmuniqueid", this.warmFlowId);
			temp.put("sheetno", this.formNo);
			temp.put("sheetstatus", getSheetstatus());
			temp.put("sheetpart", getSheetpart());
			temp.put("sheettime", getDealLimitTime());
			temp.put("sheetstarttime", getStartTime());
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
		return getSheetstatus();
	}
}
