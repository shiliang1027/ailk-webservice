
package com.linkage.module.service.outer;

import org.apache.log4j.Logger;

import com.linkage.module.service.outer.mo.RtInfo;
import com.linkage.system.designpattern.observer.MapEventObject;
import com.linkage.system.designpattern.observer.TopicEventCenter;
import com.linkage.system.utils.SystemProperties;

/**
 * 统一对外WS接口
 * 
 * @author xief (6150)
 * @version 1.0
 * @since 2010-12-5
 * @category com.linkage.module.ims.alarm.server.service<br>
 *           版权：南京联创科技 网管科技部
 */
public class WebServiceOuterAdapterImp implements WebServiceOuterAdapter
{

	private static final Logger LOG = Logger.getLogger(WebServiceOuterAdapterImp.class);
	private TopicEventCenter topicEventCenter;

	public WebServiceOuterAdapterImp(TopicEventCenter topicEventCenter)
	{
		this.topicEventCenter = topicEventCenter;
	}

	@Override
	public String process(String OPERATION, String SYSTEM, String xmlData)
	{
		LOG.info("接收" + SYSTEM + "系统," + OPERATION + "主题,内容为:" + xmlData);
		// 构造数据
		MapEventObject event = new MapEventObject(OPERATION);
		event.putProperty(WebServiceConstant.OPERATION, OPERATION);
		event.putProperty(WebServiceConstant.DATA, xmlData);
		// 非线程处理
		if ("0".equals(SystemProperties.get(OPERATION)))
		{
			try
			{
				// 广播
				topicEventCenter.publishEvent(OPERATION, event, false);
				if (event.getProperty(WebServiceConstant.RESULT) != null)
				{
					return (String) event.getProperty(WebServiceConstant.RESULT);
				}
			}
			catch (Exception e)
			{
				LOG.error("异常信息", e);
				return new RtInfo("001", "处理发生异常,请重新发送").toXML();
			}
		}
		else
		{
			// 线程处理
			topicEventCenter.publishEvent(OPERATION, event, true);
		}
		// 返回
		return new RtInfo("000", "OK").toXML();
	}
}