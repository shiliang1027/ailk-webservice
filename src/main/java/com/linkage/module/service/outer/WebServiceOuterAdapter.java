package com.linkage.module.service.outer;

/**
 * 统一对外WS接口
 * 
 * @author xief (6150)
 * @version 1.0
 * @since 2010-12-5
 * @category com.linkage.module.ims.alarm.server.service<br>
 *           版权：南京联创科技 网管科技部
 */
public interface WebServiceOuterAdapter
{

	/**
	 * Alarm对外接口
	 * 
	 * @param OPERATION
	 *            操作类型
	 * @param SYSTEM
	 *            系统名
	 * @param xmlData
	 *            数据
	 * @return
	 */
	String process(String OPERATION, String SYSTEM, String xmlData);
}