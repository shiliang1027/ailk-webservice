
package com.linkage.module.alarm.utils;

import java.util.Iterator;
import java.util.Map;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.OperationClient;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.client.async.AxisCallback;
import org.apache.axis2.context.MessageContext;
import org.apache.log4j.Logger;

/**
 * WebService接口通用实现类
 * 
 * @author xief (6150)
 * @version 1.0
 * @since 2009-12-18
 * @category com.linkage.module.itv.fault.bio<br>
 *           版权：南京联创科技 网管科技部
 */
public class WSClientProcess
{

	private final static long TIMEOUT = 300000l;
	/**
	 * 日志工具类
	 */
	private static final Logger log = Logger.getLogger(WSClientProcess.class);

	/**
	 * 通过服务端URL和方法名称获取Client对象
	 * 
	 * @param url
	 *            服务端URL
	 * @param action
	 *            方法
	 * @return 返回需要的功能存根对象，如果失败，则返回null
	 */
	private static ServiceClient getInstance(String url, String action)
	{
		try
		{
			log.info("创建ServiceClient对象");
			ServiceClient sc = new ServiceClient();
			Options opts = new Options();
			opts.setTimeOutInMilliSeconds(TIMEOUT);
			opts.setAction(action);
			opts.setTo(new EndpointReference(url));
			opts.setCallTransportCleanup(true);
			sc.setOptions(opts);
			return sc;
		}
		catch (AxisFault e)
		{
			log.info("创建ServiceClient对象失败", e);
		}
		return null;
	}

	/**
	 * 通过服务端URL和方法名称重新绑定
	 * 
	 * @param url
	 *            服务端URL
	 * @param action
	 *            方法
	 * @return 返回需要的功能存根对象，如果失败，则返回null
	 */
	private static ServiceClient rebind(String url, String action)
	{
		log.info("重新创建ServiceClient对象");
		return getInstance(url, action);
	}

	/**
	 * @param uri
	 * @param prefix
	 * @param methodName
	 * @param param
	 * @return
	 */
	private static OMElement getPayLoad(String uri, String prefix, String methodName,
			Map<String, String> param)
	{
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace(uri, prefix);
		OMElement method = fac.createOMElement(methodName, omNs);
		if (param != null && !param.isEmpty())
		{
			Iterator<String> it = param.keySet().iterator();
			while (it.hasNext())
			{
				String paraName = it.next();
				OMElement value = fac.createOMElement(paraName, omNs);
				value.setText(param.get(paraName));
				method.addChild(value);
			}
		}
		log.debug("Method " + method.getText());
		return method;
	}

	/**
	 * 匿名同步调用WebService接口,有参数方法,有返回值
	 * @param uri 命名空间
	 * @param prefix 前缀
	 * @param actionName actionName属性
	 * @param methodName 方法名
	 * @param param 参数
	 * @param url 链接
	 * @return
	 */
	public static OMElement serviceReceive(String uri, String prefix, String actionName,
			String methodName, Map<String, String> param, String url)
	{
		ServiceClient sc = getInstance(url, actionName);
		OMElement element = null;
		try
		{
			log.info("开始调用WebService.serviceReceive");
			element = sc.sendReceive(getPayLoad(uri, prefix, methodName, param));
			log.info("WebService.serviceReceive调用成功");
		}
		catch (Exception e)
		{
			// 重新绑定
			sc = rebind(url, actionName);
			if (null != sc)
			{
				try
				{
					log.info("WebService.serviceReceive重新调用");
					// 重新发送请求
					element = sc.sendReceive(getPayLoad(uri, prefix, methodName, param));
					log.info("WebService.serviceReceive调用成功");
				}
				catch (AxisFault e1)
				{
					log.info("WebService.serviceReceive通讯失败", e1);
				}
			}
		}
		finally
		{
			try
			{
				// 关闭连接
				sc.cleanupTransport();
				sc.cleanup();
			}
			catch (AxisFault e)
			{
				e.printStackTrace();
			}
			sc = null;
		}
		if(element!=null)
		{
			log.info("对外接口返回内容:" + element.getFirstElement().getText());
		}
		return element;
	}

	/**
	 * 匿名同步调用WebService接口,无参数方法,有返回值
	 * @param uri 命名空间
	 * @param prefix 前缀
	 * @param actionName actionName属性
	 * @param methodName 方法名
	 * @param url 链接
	 * @return
	 */
	public static OMElement serviceReceive(String uri, String prefix, String actionName,
			String methodName, String url)
	{
		return serviceReceive(uri, prefix, actionName, methodName, null, url);
	}

	/**
	 * 匿名同步调用WebService接口,有参数方法,无返回值
	 * @param uri 命名空间
	 * @param prefix 前缀
	 * @param actionName actionName属性
	 * @param methodName 方法名
	 * @param param 参数
	 * @param url 链接
	 * @return
	 */
	public static void serviceNoReceive(String uri, String prefix, String actionName,
			String methodName, Map<String, String> param, String url)
	{
		ServiceClient sc = getInstance(url, actionName);
		try
		{
			log.info("WebService.serviceNoReceive开始调用");
			sc.sendRobust(getPayLoad(uri, prefix, methodName, param));
			log.info("WebService.serviceNoReceive调用成功");
		}
		catch (Exception e)
		{
			// 重新绑定
			sc = rebind(url, actionName);
			if (null != sc)
			{
				try
				{
					log.info("WebService.serviceNoReceive重新调用");
					// 重新发送请求
					sc.sendRobust(getPayLoad(uri, prefix, methodName, param));
					log.info("WebService.serviceNoReceive调用成功");
				}
				catch (AxisFault e1)
				{
					log.info("WebService.serviceNoReceive通讯失败", e1);
				}
			}
		}
		finally
		{
			try
			{
				// 关闭连接
				sc.cleanupTransport();
				sc.cleanup();
			}
			catch (AxisFault e)
			{
				e.printStackTrace();
			}
			sc = null;
		}
	}

	/**
	 * 匿名同步调用WebService接口,无参数方法,无返回值
	 * 
	 * @param uri 命名空间
	 * @param prefix 前缀
	 * @param actionName actionName属性
	 * @param methodName 方法名
	 * @param url 链接
	 * @return
	 */
	public static void serviceNoReceive(String uri, String prefix, String actionName,
			String methodName, String url)
	{
		serviceNoReceive(uri, prefix, actionName, methodName, null, url);
	}

	/**
	 * 匿名异步调用WebService接口,有参数方法,无返回值,有回调函数
	 * @param uri 命名空间
	 * @param prefix 前缀
	 * @param actionName actionName属性
	 * @param methodName 方法名
	 * @param param 参数
	 * @param url 链接
	 * @param callback 回调函数
	 */
	public static void serviceReceiveNonBlocking(String uri, String prefix, String actionName,
			String methodName, Map<String, String> param, String url,
			AxisCallback callback)
	{
		ServiceClient sc = getInstance(url, actionName);
		try
		{
			log.info("WebService.serviceReceiveNonBlocking开始调用");
			sc.sendReceiveNonBlocking(getPayLoad(uri, prefix, methodName, param),
					callback);
			log.info("WebService.serviceReceiveNonBlocking调用成功");
		}
		catch (Exception e)
		{
			// 重新绑定
			sc = rebind(url, actionName);
			if (null != sc)
			{
				try
				{
					log.info("WebService.serviceReceiveNonBlocking重新调用");
					// 重新发送请求
					sc.sendReceiveNonBlocking(getPayLoad(uri, prefix, methodName, param),
							callback);
					log.info("WebService.serviceReceiveNonBlocking调用成功");
				}
				catch (AxisFault e1)
				{
					log.info("WebService.serviceReceiveNonBlocking通讯失败", e1);
				}
			}
		}
		finally
		{
			try
			{
				// 关闭连接
				sc.cleanupTransport();
				sc.cleanup();
			}
			catch (AxisFault e)
			{
				e.printStackTrace();
			}
			sc = null;
		}
	}

	/**
	 * 匿名异步调用WebService接口,无参数方法,无返回值,有回调函数
	 * @param uri 命名空间
	 * @param prefix 前缀
	 * @param actionName actionName属性
	 * @param methodName 方法名
	 * @param param 参数
	 * @param url 链接
	 * @param callback 回调函数
	 */
	public static void serviceReceiveNonBlocking(String uri, String prefix, String actionName,
			String methodName, String url, AxisCallback callback)
	{
		serviceReceiveNonBlocking(uri, prefix, actionName, methodName, null, url,
				callback);
	}
	
	/**
	 * 
	 * @param actionName
	 * @param url
	 * @param message
	 */
	public static SOAPEnvelope advanceServiceReceive(String actionName, String url, SOAPEnvelope envelope)
	{
		try
		{
			ServiceClient sc = new ServiceClient();
			OperationClient oc = sc.createClient(ServiceClient.ANON_OUT_IN_OP);
			
			MessageContext message = new MessageContext();
			Options opts = message.getOptions();
			opts.setTo(new EndpointReference(url));
			opts.setAction(actionName);
			message.setEnvelope(envelope);
			
			oc.addMessageContext(message);
			
			oc.execute(true);
			
			return oc.getMessageContext("In").getEnvelope();
		}
		catch (AxisFault e)
		{
			log.info("webservice调用时发生错误", e);
			return null;
		}
		catch (Exception e)
		{
			log.info("webservice调用时发生错误", e);
			return null;
		}
	}
}
