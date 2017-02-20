package com.linkage.module.alarm.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.linkage.system.extend.spring.jdbc.JdbcTemplateExtend;

@SuppressWarnings("unchecked")
public class ResTools 
{
	/**
	 * log4j日志记录器
	 */
	private static final Logger LOG = Logger.getLogger(ResTools.class);
	/**属地MAP*/
	private static Map<String,String> CITYMAP = new HashMap<String,String>();

	private static JdbcTemplateExtend jte = null;
	private static DriverManagerDataSource ds = new DriverManagerDataSource();
	private static String dataSourceClassName = "org.logicalcobwebs.proxool.ProxoolDriver";
	private static String url = "proxool.xml-test";
	private static AtomicBoolean isinit = new AtomicBoolean(false);
	private static String m_CITY_SQL = "select city_id,city_name from tab_city";

	// 资源初始化，静态方法，只初始化一次
	private synchronized static void init()
	{
		if (isinit.get())
		{
			return;
		}
		ds.setDriverClassName(dataSourceClassName);
		ds.setUrl(url);
		jte = new JdbcTemplateExtend(ds);
		isinit.set(true);
	}
	
	public static String getCITY(String city_name) {
		if(city_name==null)
		{
			return null;
		}
		return CITYMAP.get(city_name);
	}

	static {
		LOG.info("资源类型初始化");
		// 数据库连接
		init();
		
		// 投诉工单专业
		List<Map<String,String>> list = jte.queryForList(m_CITY_SQL);
		for(Map<String,String> map : list)
		{
			CITYMAP.put(map.get("city_name"), map.get("city_id"));
		}
	}
}
