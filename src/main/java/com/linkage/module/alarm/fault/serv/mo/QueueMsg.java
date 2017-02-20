
package com.linkage.module.alarm.fault.serv.mo;

import com.linkage.system.designpattern.observer.MapEventObject;

/**
 * 队列消息
 * 
 * @author mengqiang(5453) Tel:15952009765
 * @version 1.0
 * @since 2012-3-16 上午10:22:30
 * @category com.linkage.alarm.fault.serv.mo
 * @copyright 南京联创科技 网管开发部
 */
public class QueueMsg
{

	private MapEventObject msgObject = null;
	/**
	 * 加入队列时间
	 */
	private long queueTime = 0;

	public QueueMsg(MapEventObject msgObject)
	{
		this.msgObject = msgObject;
		this.queueTime = System.currentTimeMillis();
	}

	public MapEventObject getMsgObject()
	{
		return msgObject;
	}

	public long getQueueTime()
	{
		return queueTime;
	}
}