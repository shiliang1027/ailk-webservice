package com.linkage.module.alarm.complaintdispatch.serv.mo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class DispatchSyncMo {
	
	private String form_no ;
	private String isgroup_name;
	private String prosecute_flowid;
	private String urgency_level_name;
	private String prosecute_tel;
	private String deal_time_limit;
	private String accept_time;
	private String accept_deptname;
	private String accept_username;
	private String acceptman_tel;
	private String bigarea_prose;
	private String prosecute_times;
	private String form_source_name;
	private String rel_man;
	private String rel_tel;
	private String calling_no;
	private String called_no;
	private String pro_man_type_name;
	private String pro_man_sizeup_name;
	private String pro_man_level_name;
	private String pro_man_belong_name;
	private String pro_regionname;
	private String accept_regionname;
	private String deal_desc;
	private String csarchiving_dept;
	private String csarchiving_manname;
	private String is_visiting;
	private String guest_satisfaction;
	private String prosecute_type_name;
	private String attachfile;
	private String prosecute_desc;
	
	private String firstclass;
	private String secondclass;
	private Long acceptTime;
	private Long dealTimeLimit;
	private String sheetStatus;
	
	
	
	public String getForm_no() {
		return form_no;
	}
	public void setForm_no(String form_no) {
		this.form_no = form_no;
	}
	public String getProsecute_flowid() {
		return prosecute_flowid;
	}
	public void setProsecute_flowid(String prosecute_flowid) {
		this.prosecute_flowid = prosecute_flowid;
	}
	public String getUrgency_level_name() {
		return urgency_level_name;
	}
	public void setUrgency_level_name(String urgency_level_name) {
		this.urgency_level_name = urgency_level_name;
	}
	public String getDeal_time_limit() {
		return deal_time_limit;
	}
	public void setDeal_time_limit(String deal_time_limit) {
		this.deal_time_limit = deal_time_limit;
	}
	public String getAccept_time() {
		return accept_time;
	}
	public void setAccept_time(String accept_time) {
		this.accept_time = accept_time;
	}
	public String getProsecute_type_name() {
		return prosecute_type_name;
	}
	public void setProsecute_type_name(String prosecute_type_name) {
		this.prosecute_type_name = prosecute_type_name;
	}
	public String getRel_man() {
		return rel_man;
	}
	public void setRel_man(String rel_man) {
		this.rel_man = rel_man;
	}
	public String getPro_regionname() {
		return pro_regionname;
	}
	public void setPro_regionname(String pro_regionname) {
		this.pro_regionname = pro_regionname;
	}
	public String getProsecute_desc() {
		return prosecute_desc == null? "" : prosecute_desc;
	}
	public void setProsecute_desc(String prosecute_desc) {
		this.prosecute_desc = prosecute_desc;
	}
	public String getFirstclass() {
		return firstclass;
	}
	public void setFirstclass(String firstclass) {
		this.firstclass = firstclass;
	}
	public String getSecondclass() {
		return secondclass;
	}
	public void setSecondclass(String secondclass) {
		this.secondclass = secondclass;
	}
	public Long getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(Long acceptTime) {
		this.acceptTime = acceptTime;
	}
	public Long getDealTimeLimit() {
		return dealTimeLimit;
	}
	public void setDealTimeLimit(Long dealTimeLimit) {
		this.dealTimeLimit = dealTimeLimit;
	}
	
	public String getIsgroup_name() {
		return isgroup_name;
	}
	public void setIsgroup_name(String isgroup_name) {
		this.isgroup_name = isgroup_name;
	}
	
	public String getProsecute_tel() {
		return prosecute_tel;
	}
	public void setProsecute_tel(String prosecute_tel) {
		this.prosecute_tel = prosecute_tel;
	}
	public String getAccept_deptname() {
		return accept_deptname;
	}
	public void setAccept_deptname(String accept_deptname) {
		this.accept_deptname = accept_deptname;
	}
	public String getAccept_username() {
		return accept_username;
	}
	public void setAccept_username(String accept_username) {
		this.accept_username = accept_username;
	}
	public String getAcceptman_tel() {
		return acceptman_tel;
	}
	public void setAcceptman_tel(String acceptman_tel) {
		this.acceptman_tel = acceptman_tel;
	}
	public String getBigarea_prose() {
		return bigarea_prose;
	}
	public void setBigarea_prose(String bigarea_prose) {
		this.bigarea_prose = bigarea_prose;
	}
	public String getProsecute_times() {
		return prosecute_times;
	}
	public void setProsecute_times(String prosecute_times) {
		this.prosecute_times = prosecute_times;
	}
	public String getForm_source_name() {
		return form_source_name;
	}
	public void setForm_source_name(String form_source_name) {
		this.form_source_name = form_source_name;
	}
	public String getRel_tel() {
		return rel_tel;
	}
	public void setRel_tel(String rel_tel) {
		this.rel_tel = rel_tel;
	}
	public String getCalling_no() {
		return calling_no;
	}
	public void setCalling_no(String calling_no) {
		this.calling_no = calling_no;
	}
	public String getCalled_no() {
		return called_no;
	}
	public void setCalled_no(String called_no) {
		this.called_no = called_no;
	}
	public String getPro_man_type_name() {
		return pro_man_type_name;
	}
	public void setPro_man_type_name(String pro_man_type_name) {
		this.pro_man_type_name = pro_man_type_name;
	}
	public String getPro_man_sizeup_name() {
		return pro_man_sizeup_name;
	}
	public void setPro_man_sizeup_name(String pro_man_sizeup_name) {
		this.pro_man_sizeup_name = pro_man_sizeup_name;
	}
	public String getPro_man_level_name() {
		return pro_man_level_name;
	}
	public void setPro_man_level_name(String pro_man_level_name) {
		this.pro_man_level_name = pro_man_level_name;
	}
	public String getPro_man_belong_name() {
		return pro_man_belong_name;
	}
	public void setPro_man_belong_name(String pro_man_belong_name) {
		this.pro_man_belong_name = pro_man_belong_name;
	}
	public String getAccept_regionname() {
		return accept_regionname;
	}
	public void setAccept_regionname(String accept_regionname) {
		this.accept_regionname = accept_regionname;
	}
	public String getDeal_desc() {
		return deal_desc;
	}
	public void setDeal_desc(String deal_desc) {
		this.deal_desc = deal_desc;
	}
	public String getCsarchiving_dept() {
		return csarchiving_dept;
	}
	public void setCsarchiving_dept(String csarchiving_dept) {
		this.csarchiving_dept = csarchiving_dept;
	}
	public String getCsarchiving_manname() {
		return csarchiving_manname;
	}
	public void setCsarchiving_manname(String csarchiving_manname) {
		this.csarchiving_manname = csarchiving_manname;
	}
	public String getIs_visiting() {
		return is_visiting;
	}
	public void setIs_visiting(String is_visiting) {
		this.is_visiting = is_visiting;
	}
	public String getGuest_satisfaction() {
		return guest_satisfaction;
	}
	public void setGuest_satisfaction(String guest_satisfaction) {
		this.guest_satisfaction = guest_satisfaction;
	}
	public String getAttachfile() {
		return attachfile;
	}
	public void setAttachfile(String attachfile) {
		this.attachfile = attachfile;
	}
	public String getSheetStatus() {
		return sheetStatus;
	}
	public void setSheetStatus(String sheetStatus) {
		this.sheetStatus = sheetStatus;
	}
	
	public static DispatchSyncMo fromStr(String str){
		
		if(str.indexOf("<form>")!=-1)
		{
			str = str.substring(str.indexOf("<form>"), str.indexOf("</form>")+7);
		}
		
		XStream xstream = new XStream(new DomDriver());
		xstream.aliasType("form", DispatchSyncMo.class);
		return (DispatchSyncMo)xstream.fromXML(str);
	}
	
//	public static void main(String[] args) {
//		String xml = "<form><form_no>JS-900-110726-1</form_no><isgroup_name>个人</isgroup_name><prosecute_flowid>20110726151221195111</prosecute_flowid><urgency_level_name>一般</urgency_level_name><prosecute_tel>11111111</prosecute_tel><deal_time_limit>2011-06-22 11:12:00</deal_time_limit><accept_time>2011-06-20 15:12:05</accept_time><accept_deptname>投诉专席组</accept_deptname><accept_username>88413082</accept_username><acceptman_tel>10086</acceptman_tel><bigarea_prose>否</bigarea_prose><prosecute_times></prosecute_times><form_source_name>用户投诉</form_source_name><prosecute_type_name>投诉申告->基础通信->话音基本业务->网络覆盖->住宅室内->曾有覆盖现在无信号</prosecute_type_name><rel_man></rel_man><rel_tel>null</rel_tel><calling_no>10086</calling_no><called_no>10086</called_no><pro_man_type_name>其他</pro_man_type_name><pro_man_sizeup_name>神州行品牌</pro_man_sizeup_name><pro_man_level_name>未知级别</pro_man_level_name><pro_man_belong_name></pro_man_belong_name><pro_regionname>江苏</pro_regionname><accept_regionname>江苏客服</accept_regionname><prosecute_desc>流水号:20110620151221195012"
//			+"投诉内容:客户于（时间）反映（具体的道路名称、小区名称，哪栋哪层，具体房间门牌）无信号或信号较弱，已持续（时间），周围客户情况（周围客户情况），客户（是、否）同意联系进行测试。用户第二联系方式（用户第二联系方式）。我处查询知识库，无相关网络调整信息。"
//			+"故障出现时间 : ; 客户是、否同意联系进行测试 : ; 是否已有口径 : ; 故障范围 : ; 一般信号为几格 : ; 信号情况 : ;"
//			+"周围客户情况 : ; 故障提示 : ; 具体地址 : ; 是否已进行“同步”操作 : ;      </prosecute_desc><deal_desc> 派单部门: 投诉专席组 派单人:99000002 处理描述：null</deal_desc><csarchiving_dept>客服部门</csarchiving_dept><csarchiving_manname>客服用户</csarchiving_manname><is_visiting>否</is_visiting><guest_satisfaction>满意</guest_satisfaction><attachfile></attachfile></form>";
//
//		
//		DispatchSyncMo d = DispatchSyncMo.fromStr(xml);
//		System.out.println(d.getForm_no());
//		
//	}

}
