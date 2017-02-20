package com.linkage.module.alarm.fault.serv;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import EventCorrelation.Event;
import EventCorrelation.EventAttribute;
import EventCorrelation.EventScheduler;
import EventCorrelation.History;

import com.linkage.module.alarm.fault.dao.OverTimeDAO;
import com.linkage.module.alarm.fault.serv.mo.Msg;
import com.linkage.module.alarm.fault.serv.mo.ReturnInfo;
import com.linkage.module.alarm.utils.WSClientProcess;
import com.linkage.module.service.outer.WebServiceConstant;
import com.linkage.module.service.outer.mo.RtInfo;
import com.linkage.module.service.serv.Service;
import com.linkage.system.designpattern.observer.MapEventObject;
import com.linkage.system.utils.DateTimeUtil;
import com.linkage.system.utils.SystemProperties;
import com.linkage.system.utils.corba.CorbaClientCreator;
import com.linkage.system.utils.corba.CorbaService;

public class AlarmQueryService<T extends Msg> implements Service,
		InitializingBean {
	private static Logger log = Logger.getLogger(AlarmQueryService.class);

	private OverTimeDAO dao;

	private Class<T> targetInterface;

	private CorbaService cs;
	
	private EventScheduler es = null;

	public void setDao(OverTimeDAO dao) {
		this.dao = dao;
	}

	public void setTargetInterface(Class<T> targetInterface) {
		this.targetInterface = targetInterface;
	}

	public void setCs(CorbaService cs) {
		this.cs = cs;
	}

	@Override
	public void process(MapEventObject object) {
		// 判断
		String xmlData = (String) object.getProperty(WebServiceConstant.DATA);

		// 处理消息
		try {
			Msg mo = targetInterface.newInstance().strToObject(xmlData);
			// 查询数据库
			List<Map> list = mo.objectToMap();
			list.get(0).put("tablename", "ta_realalarm");
			List<Map> data = dao.queryAlarm(list.get(0));
			if (data == null || data.isEmpty()) {
				DateTimeUtil dt = new DateTimeUtil();
				list.get(0).put("tablename",
						"ta_historyalarm_" + dt.getDatePatternStr("yyyy_MM"));
				data = dao.queryAlarm(list.get(0));

				if (data == null || data.isEmpty()) {
					dt.getNextDate("month", -1);
					list.get(0).put(
							"tablename",
							"ta_historyalarm_"
									+ dt.getDatePatternStr("yyyy_MM"));
					data = dao.queryAlarm(list.get(0));

					if (data == null || data.isEmpty()) {
						dt.getNextDate("month", -1);
						list.get(0).put(
								"tablename",
								"ta_historyalarm_"
										+ dt.getDatePatternStr("yyyy_MM"));
						data = dao.queryAlarm(list.get(0));
						if (data == null || data.isEmpty()) {
							object.putProperty(WebServiceConstant.RESULT, new ReturnInfo(
									"0", "未查询到此告警",
									new DateTimeUtil().getLongDate()).toXML());
							return;
						}
					}
				}
			}

			log.debug("故障工单清除时间查询成功...");
			if ("1".equals(String.valueOf(data.get(0).get("alarmstatus")))) {
				// 传输网管再验证
				if ("传输网管"
						.equals(String.valueOf(data.get(0).get("systemname")))) {
					HashMap<String, String> param = new LinkedHashMap<String, String>();
					param.put("in0", String.valueOf(data.get(0).get(
							"vendorserialno")));
					OMElement om = WSClientProcess.serviceReceive(
							SystemProperties.get("tnms.namespace"), "nsl",
							"urn:" + SystemProperties.get("tnms.method"),
							SystemProperties.get("tnms.method"), param,
							SystemProperties.get("tnms.url"));

					// 解析传输网管信息
					OMElement element = om.getFirstElement();
					Iterator<OMElement> it = element.getChildElements();
					Map<String, String> temp = new HashMap<String, String>();
					while (it.hasNext()) {
						OMElement ele = it.next();
						temp.put(ele.getLocalName(), ele.getText());
					}

					// 状态成功
					if ("1".equals(temp.get("status"))) {
						data.get(0).put("clearTime", temp.get("clearTime"));

						try {
							Event event = getEvent(data.get(0));
							es.ReceiveEventList(new Event[]{event});
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						object.putProperty(WebServiceConstant.RESULT, new ReturnInfo(
								"0", "告警已清除", temp.get("clearTime")).toXML());
						return;
					}
				}

				object.putProperty(WebServiceConstant.RESULT, new ReturnInfo("-1",
						"告警未清除", "").toXML());
			} else {
				object.putProperty(WebServiceConstant.RESULT, new ReturnInfo("0",
						"告警已清除",
						new DateTimeUtil(Long.parseLong(String.valueOf(data
								.get(0).get("canceltime"))) * 1000)
								.getLongDate()).toXML());
			}

		} catch (Exception e) {
			log.error("异常信息", e);
			object.putProperty(WebServiceConstant.RESULT, new RtInfo("001",
					"普通工单清除时间查询处理异常").toXML());
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// scheduler
		CorbaClientCreator<EventScheduler> schccc = new CorbaClientCreator<EventScheduler>();
		schccc.setCorbaService(cs);
		schccc.setTargetLocaltion(SystemProperties
				.get("system.corba.sch.service.name"));
		schccc.setTargetInterface(EventScheduler.class);
		schccc.setSync(true);
		schccc.afterPropertiesSet();
		es = schccc.getObjectType().cast(schccc.getObject());
	}

	private Event getEvent(Map data) {
		Event event = new Event();
		try {
			// m_AlarmId
			event.m_AlarmId = String.valueOf(data.get("vendorserialno"));
			// m_AlarmCId
			event.m_AlarmCId = String.valueOf(data.get("vendorserialno"));
			// m_SystemName
			event.m_SystemName = new String(String.valueOf(data.get("systemname")).getBytes(),
					"ISO-8859-1"); 
			// m_AlarmSource
			event.m_AlarmSource = new String(String.valueOf(data.get("systemname")).getBytes(),
					"ISO-8859-1"); 
			// m_AlarmStatus
			event.m_AlarmStatus = 0;
			// m_ClearTime
			event.m_ClearTime = (int) new DateTimeUtil(String.valueOf(data.get("clearTime"))).getLongTime();
			// m_Number
			event.m_Number = "0";
			// m_NeId
			event.m_NeId = "";
			// m_NeName
			event.m_NeName =  "";
			// event.m_NeAlias
			event.m_NeAlias = "";
			// m_NeIp
			event.m_NeIp = "";
			// 网元管理系统产生的告警：OMC名称
			event.m_OMCName = "";
			// m_SystemType
			event.m_SystemType = 4;
			// m_DeviceType
			event.m_DeviceType = "";
			// m_VendorSeverity
			event.m_VendorSeverity = "";
			// m_VendorVersion
			event.m_VendorVersion = "";
			// m_VendorAlarmId
			event.m_VendorAlarmId = "";
			// m_LocateNeName
			event.m_LocateNeName = "";
			// m_VendorAlarmType
			// m_LocateNeType
			// m_Specialty
			event.m_Vendor = "";
			event.m_LocateNeType = "";
			event.m_VendorAlarmType = "";
			event.m_Specialty = 1;
			// m_NeStatus
			// event.m_NeStatus = this.ProjStatusInit;
			event.m_NeStatus = (short) 0;
			// m_SubNeStatus
			event.m_SubNeStatus = (short) 0;
			// m_OPC
			event.m_OPC = "";
			// m_SPC
			event.m_SPC = "";
			// m_SPC_Name
			event.m_SPC_Name = "";
			// m_CreateTime
			event.m_CreateTime = 0;
			// m_ReceiveTime
			event.m_ReceiveTime = 0;
			// m_AckTime
			event.m_AckTime = 0;
			// m_Severity
			event.m_Severity = 0;
			// m_NmsAlarmId
			event.m_NmsAlarmId = "";
			// m_AckFlag
			event.m_AckFlag = 0;
			// m_AckUser
			event.m_AckUser = "";
			// m_AlarmSubType
			event.m_AlarmSubType = "";
			// m_AlarmAcquistition
			event.m_AlarmAcquistition = "";
			// m_DisplayTitle
			event.m_DisplayTitle = "";
			// m_StandardAlarmName
			event.m_StandardAlarmName = "";
			// m_ProbableCauseTxt
			event.m_ProbableCauseTxt = "";
			// m_DisplayString
			event.m_DisplayString = "";
			// m_AlarmLogicClass
			event.m_AlarmLogicClass = "";
			// m_AlarmLogicSubClass
			event.m_AlarmLogicSubClass = "";
			// m_EffectOnEquipment
			event.m_EffectOnEquipment = (short) 1;
			// m_EffectOnBusiness
			event.m_EffectOnBusiness = (short) 1;
			// m_AlarmExplain
			event.m_AlarmExplain = "";
			// m_AlarmExplainText
			event.m_AlarmExplainText = "";
			// m_NmsAlarmType
			event.m_NmsAlarmType = (short) 1;
			// m_SendGroupFlag
			event.m_SendGroupFlag = (short) 1;
			// m_RelatedFlag
			event.m_RelatedFlag = 0;
			// m_strCity and m_strCounty
			event.m_strCity = "";
			event.m_strCounty = "";
			// m_strOffice
			event.m_strOffice = "";
			// m_AlarmActCount
			event.m_AlarmActCount = 0;
			// m_CorrelateAlarmFlag/0：产生告警无对应清除告警1：产生告警有对应清除告警
			event.m_CorrelateAlarmFlag = (short) 0;
			event.m_BusinessSystem = "";
			event.m_GroupCustomer = "";
			event.m_GroupCustomerId = "";
			// m_NetType
			event.m_NetType = "";
			event.m_CustomerClass = "";
			event.m_BTSType = "";
			event.m_BusinessLevel = "";
			// 99999
			event.m_SheetStatus = 0;
			event.m_SendSheetStatus = 0;
			event.m_SheetType = 0;
			event.m_SheetNo = "";
			event.m_SheetRule = "";
			event.m_SheetError = "";
			event.m_RelationType = "";
			event.m_RelationRule = "";
			// m_IfPreDeal
			event.m_IfPreDeal = (short) 1;
			event.m_PreDealStuats = 0;
			event.m_IfEomsPreDeal = "";
			event.m_DeptAccept = "";
			event.m_CityAccept = "";
			// m_GatherID
			event.m_GatherID = "";
			// m_Remark
			event.m_Remark = "";
			// preserve1
			event.preserve1 = "";
			// preserve2
			event.preserve2 = "";
			// IDL文件追加四个字段
			// 机房
			event.m_neroom = "";
			// 机楼
			event.m_nebuilding = "";
			// 是否声光告警
			event.m_ifsoundalarm = 2;
			// 是否异常告警
			event.m_ifabnormal = 2;
			event.m_LocateNeId = "";
			// m_HistoryList
			event.m_HistoryList = new EventCorrelation.History[1];
			event.m_HistoryList[0] = new History("", 0, "", "");
			// m_AttributeList
			event.m_AttributeList = new EventCorrelation.EventAttribute[1];
			event.m_AttributeList[0] = new EventAttribute("clearuser",
					(short) 0, new String("同步接口".getBytes(),
					"ISO-8859-1"));
			return event;
		} catch (Exception e) {
			log.error("Exception:", e);
			return null;
		}
	}
}