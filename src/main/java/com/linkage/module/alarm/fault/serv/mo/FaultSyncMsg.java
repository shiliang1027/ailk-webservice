package com.linkage.module.alarm.fault.serv.mo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class FaultSyncMsg implements Msg
{
	private String operatorName;
	
	private String operatorDept;
	
	private String operatorRole;
	
	private String operatorPhone;
	
	private String formNo;
	
	private String state;
	
	private String operateContentOne;
	
	private String sheetstatus;
	
	private String sheetpart;
	
	private String warnFlowId;
	
	public String getWarnFlowId() {
		return warnFlowId;
	}

	public void setWarnFlowId(String warnFlowId) {
		this.warnFlowId = warnFlowId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorDept() {
		return operatorDept;
	}

	public void setOperatorDept(String operatorDept) {
		this.operatorDept = operatorDept;
	}

	public String getOperatorRole() {
		return operatorRole;
	}

	public void setOperatorRole(String operatorRole) {
		this.operatorRole = operatorRole;
	}

	public String getOperatorPhone() {
		return operatorPhone;
	}

	public void setOperatorPhone(String operatorPhone) {
		this.operatorPhone = operatorPhone;
	}

	public String getFormNo() {
		return formNo;
	}

	public void setFormNo(String formNo) {
		this.formNo = formNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOperateContentOne() {
		return operateContentOne;
	}

	public void setOperateContentOne(String operateContentOne) {
		this.operateContentOne = operateContentOne;
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

	public FaultSyncMsg strToObject(String str)
	{
		str = str.substring(str.indexOf("<form>"), str.indexOf("</form>")+7);
		
		XStream xstream = new XStream(new DomDriver());
		// 设置节点名
		xstream.alias("form", FaultSyncMsg.class);
		return (FaultSyncMsg) xstream.fromXML(str);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map> objectToMap() {
		List<Map> list = new ArrayList<Map>();
		try {
			Map temp = PropertyUtils.describe(this);
			if(this.warnFlowId!=null)
			{
				temp.put("alarmuniqueid", this.warnFlowId);
			}
			temp.put("sheetno", this.formNo);
			temp.put("sheetstatus", getSheetstatus());
			temp.put("sheetpart", getSheetpart());
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
