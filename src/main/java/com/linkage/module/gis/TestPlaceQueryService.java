package com.linkage.module.gis;

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
public class TestPlaceQueryService {
	private final static String endPointReference = "http://localhost:8080/ailk-webservice/services/OuterAdapter?wsdl";
	private final static String targetNamespace = "http://outer.service.module.linkage.com";
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
										"PLACE_QUERY",
										"cms",
										"<?xml version=\"1.0\" encoding=\"utf-8\"?><query><name>新街口</name><city>南京</city><output>xml</output></query>" }));
	}
}
