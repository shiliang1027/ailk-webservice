package com.linkage.module.alarm.fault.serv.mo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * 告警状态统一返回信息BEAN(EOMS->ALARM)
 * 
 * @author xief (6150)
 * @version 1.0
 * @since 2010-12-5
 * @category com.linkage.module.ims.alarm.server.serv.mo<br>
 *           版权：南京联创科技 网管科技部
 */
public class ReturnInfo
{
	private static final String head = "<?xml version=\"1.0\" encoding=\"gbk\" ?>\n";
	/** 返回码*/
	private String returnCode;
	/** 返回信息*/
	private String description;
	/** 返回信息*/
	private String clearTime;
	/** 厂家标识*/
	private String systemName = "AsiaInfo";

	public ReturnInfo()
	{
		
	}
	
	public ReturnInfo(String rtCode_,String rtMessage_,String clearTime_)
	{
		this.returnCode = rtCode_;
		this.description = rtMessage_;
		this.clearTime = clearTime_;
	}
	
	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClearTime() {
		return clearTime;
	}

	public void setClearTime(String clearTime) {
		this.clearTime = clearTime;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String toXML()
	{
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("returnInfo", ReturnInfo.class);
		// 转换成 xml 格式
		return head + xstream.toXML(this);
	}
}
