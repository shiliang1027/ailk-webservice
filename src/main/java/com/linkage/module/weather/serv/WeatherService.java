package com.linkage.module.weather.serv;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.linkage.module.service.outer.WebServiceConstant;
import com.linkage.module.service.outer.mo.RtInfo;
import com.linkage.module.service.serv.Service;
import com.linkage.system.designpattern.observer.MapEventObject;

/**
 * 该java的描述信息
 * @author shiliang Tel:18661205639
 * @version 1.0
 * @since 2014-6-14 下午5:01:00
 * @category com.linkage.module.weather.serv
 * @copyright 
 */
public class WeatherService implements Service {
	private static Logger log = Logger.getLogger(WeatherService.class);
	@Override
	public void process(MapEventObject object) {
		String xmlData = (String) object.getProperty(WebServiceConstant.DATA);
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(new ByteArrayInputStream(xmlData.getBytes()));
		} catch (DocumentException e) {
			log.error(e);
		}

		log.info("doc:"+doc);
		Element root = doc.getRootElement();
		String city = root.elementText("city").trim();
		String output = root.elementText("output").trim();
		log.info("city:"+city);
		log.info("output:"+output);
		try {
			city = URLEncoder.encode(city, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpClient httpClient = new HttpClient();
		String url = "http://api.map.baidu.com/telematics/v3/weather?location="+city+"&output="+output+"&ak=z3Mnok3mu8dldBgEdLVxGbcr";
		log.info("url:"+url);
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		String responseBody = null;
		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
			}
			// 读取内容
			responseBody = new String(getMethod.getResponseBody(),
					"UTF-8");
			log.info("读取内容:"+responseBody);

		} catch (Exception e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			log.error(e);
		}finally {
			// 释放连接
			getMethod.releaseConnection();
		}
		object.putProperty(WebServiceConstant.RESULT, new RtInfo("1", responseBody).toXML());
	}

}
