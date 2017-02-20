
package com.linkage.module.alarm.fault.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.log4j.Logger;

import com.linkage.module.alarm.fault.object.FaultDispatch;
import com.linkage.system.jdbc.jpa.JPABaseDAO;
import com.linkage.system.utils.DateTimeUtil;
import com.linkage.system.utils.corba.CorbaMsgSupport;
import com.linkage.system.utils.corba.NodeUtil;

public class OverTimeDAOImp extends JPABaseDAO implements OverTimeDAO
{

	private static final Logger LOG = Logger.getLogger(OverTimeDAOImp.class);
	private static final String NS = OverTimeDAOImp.class.getName();
	private CorbaMsgSupport cms;

	public void setCms(CorbaMsgSupport cms)
	{
		this.cms = cms;
	}

	private static String TOPIC = "/Alarm/Abims/AlarmChange/SheetStatusChange";

	@Override
	public void moveDispatch()
	{
		// 扫描已关闭工单
		long ts = System.currentTimeMillis() / 1000 - 86400;
		getSqlSessionTemplate().insert(NS + ".moveDispatch", ts);
		getSqlSessionTemplate().delete(NS + ".deleteDispatch", ts);
	}

	@Override
	public void sendOverTimeMsg()
	{
		long ts = System.currentTimeMillis() / 1000;
		// 发送超时消息
		getSqlSessionTemplate().select(NS + ".queryOverTime", ts, new ResultHandler()
		{

			@Override
			public void handleResult(ResultContext arg0)
			{
				FaultDispatch o = (FaultDispatch) arg0.getResultObject();
				// 超时
				Map map = new HashMap();
				map.put("alarmuniqueid", o.getSerialno());
				map.put("sheetstatus", "9");
				Map nodeAttr = new HashMap();
				nodeAttr.put("nodeAttrs", map);
				Map node = NodeUtil.createElement();
				NodeUtil.addSubNode(node, nodeAttr);
				// 发超时消息
				LOG.debug("发送工单超时主题消息:" + TOPIC);
				cms.sendMsg(TOPIC, map);
			}
		});
		// 更新超时状态
		getSqlSessionTemplate().update(NS + ".updateOverTime", ts);
	}

	@Override
	public void createTableSpace()
	{
		DateTimeUtil dt = new DateTimeUtil();
		dt.getNextMonth(1);
		Map map = new HashMap();
		map.put("ts", new DateTimeUtil(dt.getLastDayOfMonth() + " 23:59:59")
				.getLongTime());
		// 创建分区
		getSqlSessionTemplate().insert(NS + ".createTableSpace", map);
	}

	@Override
	public void updateSheetStatus(List<Map> msg)
	{
		getSqlSessionTemplate().batchUpdate(NS + ".updateSheetStatus", msg);
	}

	@Override
	public void syncSheetStatus(List<Map> msg)
	{
		Map msgMap = msg.get(0);
		String sheetPart = String.valueOf(msgMap.get("sheetpart"));
		if (!"".equals(sheetPart) && !"null".equals(sheetPart))
		{
			getSqlSessionTemplate().update(NS + ".syncSheetStatus", msgMap);
		}
		else
		{
			getSqlSessionTemplate().update(NS + ".syncSheetStatusNotPart", msgMap);
		}
	}

	@Override
	public List<Map> querySheet(Map param)
	{
		return getSqlSessionTemplate().selectList(NS + ".querySheet", param);
	}

	@Override
	public List<Map> queryAlarm(Map param)
	{
		return getSqlSessionTemplate().selectList(NS + ".queryAlarm", param);
	}
}
