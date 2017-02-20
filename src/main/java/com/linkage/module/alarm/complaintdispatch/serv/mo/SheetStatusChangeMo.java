package com.linkage.module.alarm.complaintdispatch.serv.mo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class SheetStatusChangeMo {
	
	private String ower_name;
	private String dept_name;
	private String role_name;
	private String cell_phone;
	private String hang_cause;
	private String attachfile;
	private String local_comp_name;
	private String local_town_name;
	private String class_one_name;
	private String class_two_name;
	private String current_group_name;
	private String delivery_exp;
	private String is_call_fault;
	private String change_set;
	private String deal_time_limit;
	private String over_time;
	private String is_transfer_otherpro;
	private String provinces_name;
	private String fault_attributionname;
	private String fault_causationonename;
	private String fault_causationtwoname;
	private String prosecute_oneclassname;
	private String prosecute_twoclassname;
	private String prosecute_threeclassid;
	private String prosecute_fourclassname;
	private String exist_fault_form_no;
	private String reach_warn_level;
	private String malf_area;
	private String scene_name;
	private String scene2_name;
	private String longitude;
	private String latitude;
	private String prosecute_address1_name;
	private String prosecute_address2_name;
	private String prosecute_address3_name;
	private String prosecute_address4;
	private String prosecute_address5;
	private String reason_to_pro;
	private String deal_process;
	private String deal_result;
	private String common_col1;
	private String reject_cause;
	
	
	private String form_no;
	private String ht_description;
	private String common_col2;
	private String deal_time;
	private String sheetStatus;
	private String sheetPart;
	private Long dealTimeLong;
	
	public String getHt_description() {
		return ht_description;
	}
	public void setHt_description(String ht_description) {
		this.ht_description = ht_description;
	}
	public String getCommon_col2() {
		return common_col2;
	}
	public void setCommon_col2(String common_col2) {
		this.common_col2 = common_col2;
	}
	public String getDeal_time() {
		return deal_time;
	}
	public void setDeal_time(String deal_time) {
		this.deal_time = deal_time;
	}
	public String getSheetStatus() {
		return sheetStatus;
	}
	public void setSheetStatus(String sheetStatus) {
		this.sheetStatus = sheetStatus;
	}
	public String getSheetPart() {
		return sheetPart;
	}
	public void setSheetPart(String sheetPart) {
		this.sheetPart = sheetPart;
	}
	public Long getDealTimeLong() {
		return dealTimeLong;
	}
	public void setDealTimeLong(Long dealTimeLong) {
		this.dealTimeLong = dealTimeLong;
	}
	public String getForm_no() {
		return form_no;
	}
	public void setForm_no(String form_no) {
		this.form_no = form_no;
	}
	
	public static SheetStatusChangeMo fromStr(String xml){
		
		if(xml.indexOf("<form>")!=-1)
		{
			xml = xml.substring(xml.indexOf("<form>"), xml.indexOf("</form>")+7);
		}
		
		XStream xstream = new XStream(new DomDriver());
		xstream.aliasType("form", SheetStatusChangeMo.class);
		return (SheetStatusChangeMo)xstream.fromXML(xml);
	}
	
	public static void main(String[] args) {
		
		//一级投诉处理-挂起
		String xml1 = "<form><ower_name>张小利</ower_name><dept_name>网络监控室</dept_name><role_name>投诉一级处理组</role_name><cell_phone>13905182365</cell_phone><deal_time>2011-07-26 10:47:48</deal_time><form_no>JS-900-110726-1</form_no><ht_description>一级投诉处理</ht_description><common_col2>挂起</common_col2><hang_cause>11</hang_cause><attachfile></attachfile></form>";
		System.out.println(SheetStatusChangeMo.fromStr(xml1));
		
		//一级投诉处理-移交T2 
		String xml2 = "<form><ower_name>张小利</ower_name><dept_name>网络监控室</dept_name><role_name>投诉一级处理组</role_name><cell_phone>13905182365</cell_phone><deal_time>2011-07-26 10:59:35</deal_time><form_no>JS-900-110726-1</form_no><ht_description>一级投诉处理</ht_description><common_col2>移交T2</common_col2><local_comp_name>江苏</local_comp_name><local_town_name></local_town_name><class_one_name>增值网</class_one_name><class_two_name></class_two_name><current_group_name>否</current_group_name><delivery_exp>11</delivery_exp><attachfile></attachfile></form>";
		System.out.println(SheetStatusChangeMo.fromStr(xml2));
		
		//二级投诉处理-挂起
		String xml3 = "<form><ower_name>韩夜冰</ower_name><dept_name>增值网络维护室</dept_name><role_name>投诉二级处理组</role_name><cell_phone>13901588571</cell_phone><deal_time>2011-07-26 11:04:56</deal_time><form_no>JS-900-110726-1</form_no><ht_description>二级投诉处理</ht_description><common_col2>挂起</common_col2><hang_cause>11</hang_cause><attachfile></attachfile></form>";
		System.out.println(SheetStatusChangeMo.fromStr(xml3));
		
		//二级投诉处理-解挂
		String xml4 = "<form><ower_name>韩夜冰</ower_name><dept_name>增值网络维护室</dept_name><role_name>投诉二级处理组</role_name><cell_phone>13901588571</cell_phone><deal_time>2011-07-26 11:14:31</deal_time><form_no>JS-900-110726-1</form_no><ht_description>二级投诉处理</ht_description><common_col2>解挂</common_col2><is_call_fault>否</is_call_fault><change_set>否</change_set><deal_time_limit>2011-07-26 18:32:41</deal_time_limit><over_time>2011-07-26 11:14:31</over_time><is_transfer_otherpro>否</is_transfer_otherpro><provinces_name></provinces_name><fault_attributionname>非故障</fault_attributionname><fault_causationonename></fault_causationonename><fault_causationtwoname></fault_causationtwoname><prosecute_oneclassname>网络原因</prosecute_oneclassname><prosecute_twoclassname>交换网设备故障</prosecute_twoclassname><prosecute_threeclassid></prosecute_threeclassid><prosecute_fourclassname></prosecute_fourclassname><exist_fault_form_no></exist_fault_form_no><reach_warn_level>否</reach_warn_level><malf_area></malf_area><scene_name>打工区</scene_name><scene2_name></scene2_name><longitude>117</longitude><latitude>34</latitude><prosecute_address1_name>江苏</prosecute_address1_name><prosecute_address2_name>南京</prosecute_address2_name><prosecute_address3_name>南京市区</prosecute_address3_name><prosecute_address4>11</prosecute_address4><prosecute_address5>11</prosecute_address5><reason_to_pro>11</reason_to_pro><deal_process>11</deal_process><deal_result>11</deal_result><common_col1></common_col1><attachfile></attachfile></form>";
		System.out.println(SheetStatusChangeMo.fromStr(xml4));
		
		//挂起审核
		String xml5 = "<form><ower_name>何曼</ower_name><dept_name>网络监控室</dept_name><role_name></role_name><cell_phone></cell_phone><deal_time>2011-07-26 11:05:57</deal_time><form_no>JS-900-110726-1</form_no><ht_description>挂起审核</ht_description><common_col2>同意</common_col2><common_col1>同意！</common_col1><attachfile></attachfile></form>";
		System.out.println(SheetStatusChangeMo.fromStr(xml5));
		
		//质检
		String xml6 = "<form><ower_name>孙莉</ower_name><dept_name>省公司</dept_name><role_name></role_name><cell_phone></cell_phone><deal_time>2011-07-26 11:15:32</deal_time><form_no>JS-900-110726-1</form_no><ht_description>质检</ht_description><common_col2>通过</common_col2><common_col1>通过！</common_col1><attachfile></attachfile></form>";
		System.out.println(SheetStatusChangeMo.fromStr(xml6));
		
		//归档
		String xml7 = "<form><ower_name>客服用户</ower_name><dept_name>客服部门</dept_name><role_name></role_name><cell_phone></cell_phone><deal_time>2011-07-26 11:16:33</deal_time><form_no>JS-900-110726-1</form_no><ht_description>归档</ht_description><common_col2>很满意</common_col2><common_col1>满意！</common_col1><attachfile></attachfile></form>";
		System.out.println(SheetStatusChangeMo.fromStr(xml7));
		
		//一级投诉处理-回复
		String xml8= "<form><ower_name>汤炜</ower_name><dept_name>网络监控室</dept_name><role_name>投诉一级处理组</role_name><cell_phone>13905189642</cell_phone><deal_time>2011-08-03 11:12:18</deal_time><form_no>JS-900-110803-2</form_no><ht_description>一级投诉处理</ht_description><common_col2>回复</common_col2><is_call_fault>否</is_call_fault><change_set>否</change_set><deal_time_limit>2011-08-03 14:30:31</deal_time_limit><over_time>2011-08-03 11:12:18</over_time><is_transfer_otherpro>否</is_transfer_otherpro><provinces_name></provinces_name><fault_attributionname>非故障</fault_attributionname><fault_causationonename></fault_causationonename><fault_causationtwoname></fault_causationtwoname><prosecute_oneclassname>网络原因</prosecute_oneclassname><prosecute_twoclassname>本运营商内部干扰</prosecute_twoclassname><prosecute_threeclassid>011002</prosecute_threeclassid><prosecute_fourclassname></prosecute_fourclassname><exist_fault_form_no></exist_fault_form_no><reach_warn_level>否</reach_warn_level><malf_area></malf_area><scene_name>交通干线</scene_name><scene2_name></scene2_name><longitude>120</longitude><latitude>33</latitude><prosecute_address1_name>江苏</prosecute_address1_name><prosecute_address2_name>泰州</prosecute_address2_name><prosecute_address3_name>姜堰</prosecute_address3_name><prosecute_address4>1</prosecute_address4><prosecute_address5>2</prosecute_address5><reason_to_pro>1</reason_to_pro><deal_process>2</deal_process><deal_result>3</deal_result><common_col1>4</common_col1><attachfile></attachfile></form>"; 
		System.out.println(SheetStatusChangeMo.fromStr(xml8));
		
		//一级投诉处理-驳回   (这个因为CSP客服配合，所以暂未测试，只提供了报文格式)
		String xml9 = "<form><ower_name>蔡敬万</ower_name><dept_name>增值网络维护室</dept_name><role_name>投诉一级处理组</role_name><cell_phone>13905182062</cell_phone><deal_time>2011-08-03 13:48:38</deal_time><form_no>JS-900-110803-3</form_no><ht_description>一级投诉处理</ht_description><common_col2>驳回</common_col2><reject_cause>ggggg</reject_cause><attachfile></attachfile></form>";
		System.out.println(SheetStatusChangeMo.fromStr(xml9));
	}

}
