package com.linkage.module.gis.serv;

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

import com.ailk.gis.parser.BaiduQueryParser;
import com.ailk.gis.service.QueryPlaceResult;
import com.linkage.module.service.outer.WebServiceConstant;
import com.linkage.module.service.outer.mo.RtInfo;
import com.linkage.module.service.serv.Service;
import com.linkage.system.designpattern.observer.MapEventObject;

/**
 * 该java的描述信息
 * @author shiliang Tel:13770527121
 * @version 1.0
 * @since Dec 19, 2012 11:18:03 AM
 * @category com.linkage.module.gis.serv
 * @copyright 
 */
public class PlaceQueryService implements Service{
	private static Logger log = Logger.getLogger(PlaceQueryService.class);
	@Override
	public void process(MapEventObject object) {
		// TODO Auto-generated method stub
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
		String name = root.elementText("name").trim();
		String city = root.elementText("city").trim();
		String output = root.elementText("output").trim();
		try {
			name = URLEncoder.encode(name, "UTF-8");
			city = URLEncoder.encode(city, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error(e);
		}
		String resultStr = null;
		HttpClient httpClient = new HttpClient();
		String url = "http://api.map.baidu.com/place/search?&query="+name+"&region="+city+"&output=xml&key=73b3cb1afb214112128f4402f835cdfe";
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
			}
			// 读取内容
			String responseBody = new String(getMethod.getResponseBody(),
					"UTF-8");
			log.info("读取内容:"+responseBody);
			QueryPlaceResult result = new QueryPlaceResult(new BaiduQueryParser(responseBody));
			if("JSON".equals(output.trim().toUpperCase())){
				resultStr = result.toJSON();
			}else if("XML".equals(output.trim().toUpperCase())){
				resultStr = result.toXML();
			}
			log.info("结果"+resultStr);
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			log.error(e);
		} catch (IOException e) {
			// 发生网络异常
			log.error(e);
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
		object.putProperty(WebServiceConstant.RESULT, new RtInfo("1", resultStr).toXML());
	}

}
