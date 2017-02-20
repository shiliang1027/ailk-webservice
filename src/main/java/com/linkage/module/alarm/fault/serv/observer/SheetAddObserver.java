package com.linkage.module.alarm.fault.serv.observer;

import java.util.EventObject;

import com.linkage.module.service.serv.Service;
import com.linkage.system.designpattern.observer.Listener;
import com.linkage.system.designpattern.observer.MapEventObject;
import com.linkage.system.designpattern.observer.TopicEventCenter;

/**
 * 
 * @author gaoqc(69151) Tel:18652058796
 * @version 1.0
 * @since 2012-9-18 下午5:01:33
 * @category com.linkage.module.alarm.fault.serv.observer
 * @copyright 南京联创科技 网管科技部
 *
 */ 
public class SheetAddObserver implements Listener
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
