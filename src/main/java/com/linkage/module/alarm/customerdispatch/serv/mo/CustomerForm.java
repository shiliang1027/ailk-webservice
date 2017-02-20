package com.linkage.module.alarm.customerdispatch.serv.mo;

import java.lang.reflect.Field;

import com.linkage.module.service.outer.exception.XmlException;
import com.linkage.system.utils.DateTimeUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class CustomerForm
{
	private String title;
	private String formNo;
	private String flowId;
	private String startTime;
	private String groupCode;
	private String groupName;
	private String groupLevel;
	private String groupCustomerContact;
	private String groupCustomerPhone;
	private String groupCustomerManager;
	private String groupCustomerManagerPhone;
	private String complainId;
	private String complainLevel;
	private String limitTime;
	private String acceptTime;
	private String acceptDept;
	private String acceptMan;
	private String acceptManPhone;
	private String largeScaleComplaints;
	private String complainTimes;
	private String businessType;
	private String complainCityName;
	private String countryCode;
	private String proceeLimitTime;
	private String complainDesc;
	private String dealWithDesc;
	private String createtime;
	public String getFormNo() {
		return formNo;
	}
	public void setFormNo(String formNo) {
		this.formNo = formNo;
	}
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	public String getStartTime() {
		return String.valueOf(new DateTimeUtil(startTime).getLongTime());
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupLevel() {
		return groupLevel;
	}
	public void setGroupLevel(String groupLevel) {
		this.groupLevel = groupLevel;
	}
	public String getGroupCustomerContact() {
		return groupCustomerContact;
	}
	public void setGroupCustomerContact(String groupCustomerContact) {
		this.groupCustomerContact = groupCustomerContact;
	}
	public String getGroupCustomerPhone() {
		return groupCustomerPhone;
	}
	public void setGroupCustomerPhone(String groupCustomerPhone) {
		this.groupCustomerPhone = groupCustomerPhone;
	}
	public String getGroupCustomerManager() {
		return groupCustomerManager;
	}
	public void setGroupCustomerManager(String groupCustomerManager) {
		this.groupCustomerManager = groupCustomerManager;
	}
	public String getComplainId() {
		return complainId;
	}
	public void setComplainId(String complainId) {
		this.complainId = complainId;
	}
	public String getComplainLevel() {
		if("1".equals(complainLevel))
		{
			complainLevel = "紧急";
		}
		else
		{
			complainLevel = "普通";
		}
		
		return complainLevel;
	}
	public void setComplainLevel(String complainLevel) {
		this.complainLevel = complainLevel;
	}
	public String getLimitTime() {
		return String.valueOf(new DateTimeUtil(limitTime).getLongTime());
	}
	public void setLimitTime(String limitTime) {
		this.limitTime = limitTime;
	}
	public String getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}
	public String getAcceptDept() {
		return acceptDept;
	}
	public void setAcceptDept(String acceptDept) {
		this.acceptDept = acceptDept;
	}
	public String getAcceptMan() {
		return acceptMan;
	}
	public void setAcceptMan(String acceptMan) {
		this.acceptMan = acceptMan;
	}
	public String getAcceptManPhone() {
		return acceptManPhone;
	}
	public void setAcceptManPhone(String acceptManPhone) {
		this.acceptManPhone = acceptManPhone;
	}
	public String getLargeScaleComplaints() {
		return largeScaleComplaints;
	}
	public void setLargeScaleComplaints(String largeScaleComplaints) {
		this.largeScaleComplaints = largeScaleComplaints;
	}
	public String getComplainTimes() {
		return complainTimes;
	}
	public void setComplainTimes(String complainTimes) {
		this.complainTimes = complainTimes;
	}
	public String getBusinessType() {
		if(businessType==null
				|| "".equals(businessType)
				|| "null".equals(businessType))
		{
			businessType = "语音业务";
		}
		
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getComplainCityName() {
		return complainCityName;
	}
	public void setComplainCityName(String complainCityName) {
		this.complainCityName = complainCityName;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getProceeLimitTime() {
		return proceeLimitTime;
	}
	public void setProceeLimitTime(String proceeLimitTime) {
		this.proceeLimitTime = proceeLimitTime;
	}
	public String getComplainDesc() {
		return complainDesc;
	}
	public void setComplainDesc(String complainDesc) {
		this.complainDesc = complainDesc;
	}
	public String getDealWithDesc() {
		return dealWithDesc;
	}
	public void setDealWithDesc(String dealWithDesc) {
		this.dealWithDesc = dealWithDesc;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGroupCustomerManagerPhone() {
		return groupCustomerManagerPhone;
	}
	public void setGroupCustomerManagerPhone(String groupCustomerManagerPhone) {
		this.groupCustomerManagerPhone = groupCustomerManagerPhone;
	}
	public String getCreatetime() {
		return String.valueOf(System.currentTimeMillis()/1000);
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public static CustomerForm strToObject(String str) throws XmlException
	{
		if(str.indexOf("<form>")!=-1)
		{
			str = str.substring(str.indexOf("<form>"), str.indexOf("</form>")+7);
		}
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("form", CustomerForm.class);
		// 转换成 xml 格式
		return (CustomerForm)xstream.fromXML(str);
	}
	
	public String toString()
	{
		Class<CustomerForm> c = CustomerForm.class;
		Field[] fs = c.getDeclaredFields();
		StringBuilder sb = new StringBuilder("{");
		boolean isFirst = true;
		try
		{
			for (Field f : fs)
			{
				if (!isFirst)
				{
					sb.append(",");
				}
				isFirst = false;
				sb.append(f.getName());
				sb.append(":");
				sb.append(f.get(this));
			}
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		sb.append("}");
		return sb.toString();
	}
}
