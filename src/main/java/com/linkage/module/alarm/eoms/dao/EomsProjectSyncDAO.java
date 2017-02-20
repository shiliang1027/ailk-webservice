package com.linkage.module.alarm.eoms.dao;

import java.util.List;
import java.util.Map;

import com.linkage.module.alarm.eoms.serv.mo.NETADJUSTINFO;
import com.linkage.module.alarm.eoms.serv.mo.ProjectSyncMo;

public interface EomsProjectSyncDAO
{
	/**
	 * 查询是否已存在此次工程预约信息
	 * @param sequ_id
	 * @return
	 */
	List<Map> query(String sequ_id);
	
	/**
	 * 查询网元信息
	 * @param mo_name
	 * @return
	 */
	List<Map> queryMoInfo(String mo_name);
	
	/**
	 * 查询工程网元信息
	 * @param mo_name
	 * @return
	 */
	List<Map> queryMo(String sequ_id);

	/**
	 * 新增工程预约信息
	 * @param mo
	 */
	void insert(ProjectSyncMo mo);

	/**
	 * 更新工程预约信息
	 * @param mo
	 */
	void update(ProjectSyncMo mo);

	/**
	 * 更新工程预约状态
	 * @param mo
	 */
	void updateStates(ProjectSyncMo mo);
	
	/**
	 * 更新工程预约状态
	 * @param mo
	 */
	void updateStates2(ProjectSyncMo mo);

	/**
	 * 新增工程网元表
	 */
	void insertBelong(List<NETADJUSTINFO> info);
	
	/**
	 * 删除工程网元表
	 */
	void deleteBelong(String sequ_id);

	/**
	 * 更新网元表中网元工程
	 * @param sequ_id
	 */
	void updateMoStates(String sequ_id);

	/**
	 * 恢复网元表中网元状态
	 * @param sequ_id
	 */
	void rebackMoStates(String sequ_id);

	/**
	 * 工程屏蔽1
	 * @param ts
	 */
	List<Map> schTask1(long ts);
	
	/**
	 * 工程屏蔽2
	 * @param ts
	 */
	List<Map> schTask2(long ts);
	
	/**
	 * 工程屏蔽3
	 * @param ts
	 */
	List<Map> schTask3(long ts);
	
	/**
	 * 工程屏蔽4
	 * @param ts
	 */
	List<Map> schTask4(long ts);
}
