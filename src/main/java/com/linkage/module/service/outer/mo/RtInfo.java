package com.linkage.module.service.outer.mo;

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
public class RtInfo
{
	private static final String head = "<?xml version=\"1.0\" encoding=\"gbk\" ?>\n";
	/** 返回码*/
	private String rtCode;
	/** 返回信息*/
	private String rtMessage;
	/** 厂家标识*/
	private String systemName = "AsiaInfo";

	public RtInfo()
	{
		
	}
	
	public RtInfo(String rtCode_,String rtMessage_)
	{
		this.rtCode = rtCode_;
		this.rtMessage = rtMessage_;
	}
	
	public String getRtCode()
	{
		return rtCode;
	}

	public void setRtCode(String rtCode)
	{
		this.rtCode = rtCode;
	}

	public String getRtMessage()
	{
		return rtMessage;
	}

	public void setRtMessage(String rtMessage)
	{
		this.rtMessage = rtMessage;
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
		xstream.alias("rtInfo", RtInfo.class);
		// 转换成 xml 格式
		return head + xstream.toXML(this);
	}
}