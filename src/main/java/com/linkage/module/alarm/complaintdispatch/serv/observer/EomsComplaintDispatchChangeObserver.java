
package com.linkage.module.alarm.complaintdispatch.serv.observer;

import java.util.EventObject;

import org.apache.log4j.Logger;

import com.linkage.module.service.serv.Service;
import com.linkage.system.designpattern.observer.Listener;
import com.linkage.system.designpattern.observer.MapEventObject;
import com.linkage.system.designpattern.observer.TopicEventCenter;

public class EomsComplaintDispatchChangeObserver implements Listener
{

	private static final Logger logger = Logger.getLogger(EomsComplaintDispatchChangeObserver.class);
	/** 事件中心对象 */
	private TopicEventCenter topicEventCenter;
	/** 工单BIO */
	private Service bio;
	/** 主题 */
	private String operation;

	@Override
	public boolean accepts(EventObject event)
	{
		return true;
	}

	@Override
	public String getId()
	{
		return operation;
	}

	@Override
	public void onEvent(EventObject event)
	{
		logger.info("收到主题为： " + operation + " 的事件");
		bio.process((MapEventObject) event);
	}

	/**
	 * 注册观察者
	 */
	public void addOberser()
	{
		logger.info("注册工单同步观察者......operation=" + operation);
		topicEventCenter.addListener(operation, this);
	}

	public void setTopicEventCenter(TopicEventCenter topicEventCenter)
	{
		this.topicEventCenter = topicEventCenter;
	}

	public void setBio(Service bio)
	{
		this.bio = bio;
	}

	public void setOperation(String operation)
	{
		this.operation = operation;
	}
}