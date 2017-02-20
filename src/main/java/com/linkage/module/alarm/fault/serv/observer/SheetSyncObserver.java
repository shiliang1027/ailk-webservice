
package com.linkage.module.alarm.fault.serv.observer;

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
public class SheetSyncObserver implements Listener
{

	/** 事件中心对象 */
	private TopicEventCenter topicEventCenter;
	/** 查询网络设备信息bio */
	private Service bio;

	private String[] topics = null;
	
	private String lid = null;
	
	/**
	 * 注册观察者
	 */
	public void addOberser()
	{
		for(String t : topics)
		{
			topicEventCenter.addListener(t, this);
		}
	}

	public boolean accepts(EventObject event)
	{
		return true;
	}

	public String getId()
	{
		return lid;
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

	public void setTopics(String[] topics) {
		this.topics = topics;
	}
	
	public void setLid(String lid)
	{
		this.lid = lid;
	}
}