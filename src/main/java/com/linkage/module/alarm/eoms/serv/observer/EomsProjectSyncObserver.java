
package com.linkage.module.alarm.eoms.serv.observer;

import java.util.EventObject;

import com.linkage.module.service.serv.Service;
import com.linkage.system.designpattern.observer.Listener;
import com.linkage.system.designpattern.observer.MapEventObject;
import com.linkage.system.designpattern.observer.TopicEventCenter;

/**
 * [类功能描述] 告警状态观察者类
 * 
 * @author xief(6150) xief@lianchuang.com
 * @version 1.0
 * @since 2010-12-5
 * @category com.linkage.module.ims.alarm.server.serv.observer<br>
 */
public class EomsProjectSyncObserver implements Listener
{

	/** 事件中心对象 */
	private TopicEventCenter topicEventCenter;
	/** 查询网络设备信息bio */
	private Service bio;
	/** 主题 */
	private String topic;
	
	/**
	 * 注册观察者
	 */
	public void addOberser()
	{
		topicEventCenter.addListener(topic, this);
	}

	public boolean accepts(EventObject event)
	{
		return true;
	}

	public String getId()
	{
		return topic;
	}

	public void onEvent(EventObject event)
	{
		bio.process((MapEventObject)event);
	}

	public void setTopicEventCenter(TopicEventCenter topicEventCenter)
	{
		this.topicEventCenter = topicEventCenter;
	}

	public void setBio(Service bio)
	{
		this.bio = bio;
	}

	public void setTopic(String topic)
	{
		this.topic = topic;
	}
}