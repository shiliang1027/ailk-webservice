
package com.linkage.module.alarm.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.axiom.om.OMElement;

public class Test
{

	public static void main(String[] args) throws Exception
	{
		HashMap<String, String> param = new LinkedHashMap<String, String>();
		param.put("OPERATION", "NORALARMSHEET_CHANGE");
		param.put("SYSTEM", "JS_TEST");
		param.put("xmlData", "<?xml version=\"1.0\" encoding=\"gbk\"?><req></req>");
		OMElement str = WSClientProcess.serviceReceive(
				"http://service.server.ims.module.linkage.com", "nsl", "urn:process",
				"process", param, "http://10.40.9.238:8777/cms/services/OuterAdapter");
		System.out.println(str.toString());
	}
}