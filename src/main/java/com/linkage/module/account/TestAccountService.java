package com.linkage.module.account;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

/**
 * 该java的描述信息
 * 
 * @author shiliang Tel:13770527121
 * @version 1.0
 * @since Apr 11, 2012 9:23:57 AM
 * @category com.linkage.cms
 * @copyright
 */
public class TestAccountService {
//	private final static String endPointReference = "http://10.40.11.118:8086/cms/services/OuterAdapter?wsdl";
private final static String endPointReference = "http://10.40.9.238:8777/cms/services/OuterAdapter?wsdl";
	private final static String targetNamespace = "http://service.server.ims.module.linkage.com";
	private final static String webServiceMethod = "process";

	public static void main(String[] args) throws Exception {
		Service service = new Service();
		Call call = (Call) service.createCall();
		QName qn = new QName(targetNamespace, webServiceMethod);
		call.setReturnType(qn, String.class);
		call.setOperationName(qn);
		call.addParameter(new QName(targetNamespace, "OPERATION"),
				XMLType.XSD_STRING, ParameterMode.IN);
		call.addParameter(new QName(targetNamespace, "SYSTEM"),
				XMLType.XSD_STRING, ParameterMode.IN);
		call.addParameter(new QName(targetNamespace, "xmlData"),
				XMLType.XSD_STRING, ParameterMode.IN);
		call.setTargetEndpointAddress(new URL(endPointReference));
		// 调用的服务器端方法
		System.out
				.println("返回值:"
						+ call
								.invoke(new Object[] {
										"ACCOUNT_SYNC",
										"4A",
										"<?xml version=\"1.0\" encoding=\"UTF-8\"?><notice><opt>add</opt><accountName>test</accountName></notice>" }));
	}
}
