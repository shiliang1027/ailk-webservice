package com.linkage.module.alarm.fault.dao;

import java.util.List;
import java.util.Map;

public interface OverTimeDAO
{
	/**
	 * 扫描超时工单,并发送工单状态消息
	 */
	void sendOverTimeMsg();

	/**
	 * 定时移除关闭工单
	 */
	void moveDispatch();

	/**
	 * 创建分区
	 */
	void createTableSpace();

	/**
	 * 建单时更新工单编号
	 * @param msg
	 */
	void updateSheetStatus(List<Map> msg);

	/**
	 * 同步工单状态
	 * @param msg
	 */
	void syncSheetStatus(List<Map> msg);

	/**
	 * 查询工单序列号
	 * @param formNo
	 * @return
	 */
	List<Map> querySheet(Map formNo);

	/**
	 * 查询告警的清除时间
	 * @param formNo
	 * @return
	 */
	List<Map> queryAlarm(Map param);
}
