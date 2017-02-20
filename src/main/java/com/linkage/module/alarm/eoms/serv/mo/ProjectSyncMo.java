package com.linkage.module.alarm.eoms.serv.mo;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.linkage.module.alarm.utils.ResTools;
import com.linkage.module.service.outer.exception.XmlException;
import com.linkage.system.utils.DateTimeUtil;

public class ProjectSyncMo
{
	// 工程割接信息ID
	private String EQUIPBUSIRANGE;
	private String EXPECTATIONBEGINTIME;
	private String EXPECTATIONENDTIME;
	private String PROLEADER;
	private String RELATIONSHIP;
	private String INFOANNOUNCEMENT;
	private String INFOANNOUNCETIME;
	private String FLAG;
	private String INFOHEADER;
	private String INFOCONTENT;
	private String INFOPUSHTIME;
	private String REGION;
	private String INFOTYPE;
	private String INFO_ID;
	private String INFO_NO;
	private String KF_NOTICE;
	private String ADJUSTTYPE;
	private String PROJECTTYPE;
	private String SCENERELATIONSHIP;
	private String SCENEPROLEADER;
	private String ISEFFECTBUSINESS;
	private String ISCUSTOMRENDER;
	private String IS_MUTIPUBLIC;
	private String ISINFODATEREPORT;
	private String BEGIONCONFIRMER;
	private String BEGINCONFIRMTIME;
	private String ENDCONFIRMER;
	private String ENDCONFIRMTIME;
	
	private List<NETADJUSTINFO> netadjust;
	
	private List<NETADJUSTINFO> affect;
	
	public String getEQUIPBUSIRANGE() {
		return EQUIPBUSIRANGE;
	}
	public void setEQUIPBUSIRANGE(String eQUIPBUSIRANGE) {
		EQUIPBUSIRANGE = eQUIPBUSIRANGE;
	}
	public String getEXPECTATIONBEGINTIME() {
		return String.valueOf(new DateTimeUtil(EXPECTATIONBEGINTIME).getLongTime());
	}
	public void setEXPECTATIONBEGINTIME(String eXPECTATIONBEGINTIME) {
		EXPECTATIONBEGINTIME = eXPECTATIONBEGINTIME;
	}
	public String getEXPECTATIONENDTIME() {
		return String.valueOf(new DateTimeUtil(EXPECTATIONENDTIME).getLongTime());
	}
	public void setEXPECTATIONENDTIME(String eXPECTATIONENDTIME) {
		EXPECTATIONENDTIME = eXPECTATIONENDTIME;
	}
	public String getPROLEADER() {
		return PROLEADER;
	}
	public void setPROLEADER(String pROLEADER) {
		PROLEADER = pROLEADER;
	}
	public String getRELATIONSHIP() {
		return RELATIONSHIP;
	}
	public void setRELATIONSHIP(String rELATIONSHIP) {
		RELATIONSHIP = rELATIONSHIP;
	}
	public String getINFOANNOUNCEMENT() {
		return INFOANNOUNCEMENT;
	}
	public void setINFOANNOUNCEMENT(String iNFOANNOUNCEMENT) {
		INFOANNOUNCEMENT = iNFOANNOUNCEMENT;
	}
	public String getINFOANNOUNCETIME() {
		return INFOANNOUNCETIME;
	}
	public void setINFOANNOUNCETIME(String iNFOANNOUNCETIME) {
		INFOANNOUNCETIME = iNFOANNOUNCETIME;
	}
	public String getFLAG() {
		return FLAG;
	}
	public void setFLAG(String fLAG) {
		FLAG = fLAG;
	}
	public String getINFOHEADER() {
		return INFOHEADER;
	}
	public void setINFOHEADER(String iNFOHEADER) {
		INFOHEADER = iNFOHEADER;
	}
	public String getINFOCONTENT() {
		return INFOCONTENT;
	}
	public void setINFOCONTENT(String iNFOCONTENT) {
		INFOCONTENT = iNFOCONTENT;
	}
	public String getINFOPUSHTIME() {
		return INFOPUSHTIME;
	}
	public void setINFOPUSHTIME(String iNFOPUSHTIME) {
		INFOPUSHTIME = iNFOPUSHTIME;
	}
	public String getREGION() {
		// 查询所属属地
		if(REGION!=null)
		{
			REGION = ResTools.getCITY(REGION)==null?REGION:ResTools.getCITY(REGION);
		}
		
		if(REGION==null || "江苏".equals(REGION))
		{
			REGION = "00";
		}
		
		return REGION;
	}
	public void setREGION(String rEGION) {
		REGION = rEGION;
	}
	public String getINFOTYPE() {
		return INFOTYPE;
	}
	public void setINFOTYPE(String iNFOTYPE) {
		INFOTYPE = iNFOTYPE;
	}
	public String getINFO_ID() {
		return INFO_ID;
	}
	public void setINFO_ID(String iNFOID) {
		INFO_ID = iNFOID;
	}
	public String getINFO_NO() {
		return INFO_NO;
	}
	public void setINFO_NO(String iNFONO) {
		INFO_NO = iNFONO;
	}
	public String getKF_NOTICE() {
		return KF_NOTICE;
	}
	public void setKF_NOTICE(String kFNOTICE) {
		KF_NOTICE = kFNOTICE;
	}
	public String getADJUSTTYPE() {
		return ADJUSTTYPE;
	}
	public void setADJUSTTYPE(String aDJUSTTYPE) {
		ADJUSTTYPE = aDJUSTTYPE;
	}
	public String getPROJECTTYPE() {
		return PROJECTTYPE;
	}
	public void setPROJECTTYPE(String pROJECTTYPE) {
		PROJECTTYPE = pROJECTTYPE;
	}
	public String getSCENERELATIONSHIP() {
		return SCENERELATIONSHIP;
	}
	public void setSCENERELATIONSHIP(String sCENERELATIONSHIP) {
		SCENERELATIONSHIP = sCENERELATIONSHIP;
	}
	public String getSCENEPROLEADER() {
		return SCENEPROLEADER;
	}
	public void setSCENEPROLEADER(String sCENEPROLEADER) {
		SCENEPROLEADER = sCENEPROLEADER;
	}
	public String getISEFFECTBUSINESS() {
		return ISEFFECTBUSINESS;
	}
	public void setISEFFECTBUSINESS(String iSEFFECTBUSINESS) {
		ISEFFECTBUSINESS = iSEFFECTBUSINESS;
	}
	public String getISCUSTOMRENDER() {
		return ISCUSTOMRENDER;
	}
	public void setISCUSTOMRENDER(String iSCUSTOMRENDER) {
		ISCUSTOMRENDER = iSCUSTOMRENDER;
	}
	public String getIS_MUTIPUBLIC() {
		return IS_MUTIPUBLIC;
	}
	public void setIS_MUTIPUBLIC(String iSMUTIPUBLIC) {
		IS_MUTIPUBLIC = iSMUTIPUBLIC;
	}
	public String getISINFODATEREPORT() {
		return ISINFODATEREPORT;
	}
	public void setISINFODATEREPORT(String iSINFODATEREPORT) {
		ISINFODATEREPORT = iSINFODATEREPORT;
	}
	public List<NETADJUSTINFO> getNetadjust() {
		return netadjust;
	}
	public void setNetadjust(List<NETADJUSTINFO> netadjust) {
		this.netadjust = netadjust;
	}
	public List<NETADJUSTINFO> getAffect() {
		return affect;
	}
	public void setAffect(List<NETADJUSTINFO> affect) {
		this.affect = affect;
	}
	public String getBEGIONCONFIRMER() {
		return BEGIONCONFIRMER;
	}
	public void setBEGIONCONFIRMER(String bEGIONCONFIRMER) {
		BEGIONCONFIRMER = bEGIONCONFIRMER;
	}
	public String getBEGINCONFIRMTIME() {
		return BEGINCONFIRMTIME;
	}
	public void setBEGINCONFIRMTIME(String bEGINCONFIRMTIME) {
		BEGINCONFIRMTIME = bEGINCONFIRMTIME;
	}
	public String getENDCONFIRMER() {
		return ENDCONFIRMER;
	}
	public void setENDCONFIRMER(String eNDCONFIRMER) {
		ENDCONFIRMER = eNDCONFIRMER;
	}
	public String getENDCONFIRMTIME() {
		return ENDCONFIRMTIME;
	}
	public void setENDCONFIRMTIME(String eNDCONFIRMTIME) {
		ENDCONFIRMTIME = eNDCONFIRMTIME;
	}
	@SuppressWarnings("unchecked")
	public static ProjectSyncMo strToObject(String str) throws XmlException {
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new ByteArrayInputStream(str.getBytes()));
			Element root = doc.getRootElement();
			List<Element> list = root.elements("xmlData");
			if(list != null && !list.isEmpty())
			{
				ProjectSyncMo mo = new ProjectSyncMo();
				Element element = list.get(0);
				mo.setEQUIPBUSIRANGE(element.elementText("EQUIPBUSIRANGE"));
				mo.setEXPECTATIONBEGINTIME(element.elementText("EXPECTATIONBEGINTIME"));
				mo.setEXPECTATIONENDTIME(element.elementText("EXPECTATIONENDTIME"));
				mo.setPROLEADER(element.elementText("PROLEADER"));
				mo.setRELATIONSHIP(element.elementText("RELATIONSHIP"));
				mo.setINFOANNOUNCEMENT(element.elementText("INFOANNOUNCEMENT"));
				mo.setINFOANNOUNCETIME(element.elementText("INFOANNOUNCETIME"));
				mo.setFLAG(element.elementText("FLAG"));
				mo.setINFOHEADER(element.elementText("INFOHEADER"));
				mo.setINFOCONTENT(element.elementText("INFOCONTENT"));
				mo.setINFOPUSHTIME(element.elementText("INFOPUSHTIME"));
				mo.setREGION(element.elementText("REGION"));
				mo.setINFOTYPE(element.elementText("INFOTYPE"));
				mo.setINFO_ID(element.elementText("INFO_ID"));
				mo.setINFO_NO(element.elementText("INFO_NO"));
				mo.setKF_NOTICE(element.elementText("KF_NOTICE"));
				mo.setADJUSTTYPE(element.elementText("ADJUSTTYPE"));
				mo.setPROJECTTYPE(element.elementText("PROJECTTYPE"));
				mo.setSCENERELATIONSHIP(element.elementText("SCENERELATIONSHIP"));
				mo.setSCENEPROLEADER(element.elementText("SCENEPROLEADER"));
				mo.setISEFFECTBUSINESS(element.elementText("ISEFFECTBUSINESS"));
				mo.setISCUSTOMRENDER(element.elementText("ISCUSTOMRENDER"));
				mo.setIS_MUTIPUBLIC(element.elementText("IS_MUTIPUBLIC"));
				mo.setISINFODATEREPORT(element.elementText("ISINFODATEREPORT"));
				mo.setBEGIONCONFIRMER(element.elementText("BEGIONCONFIRMER"));
				mo.setBEGINCONFIRMTIME(element.elementText("BEGINCONFIRMTIME"));
				mo.setENDCONFIRMER(element.elementText("ENDCONFIRMER"));
				mo.setENDCONFIRMTIME(element.elementText("ENDCONFIRMTIME"));
				
				Iterator<Element> it = element.elementIterator("NETADJUSTINFO");
				if (it != null)
				{
					mo.netadjust = new ArrayList<NETADJUSTINFO>();
					while (it.hasNext())
					{
						mo.netadjust.add(NETADJUSTINFO.strToObject("NETADJUSTINFO", it.next().asXML()));
					}
				}
				
				it = element.elementIterator("AFFECTNETINFO");
				if (it != null)
				{
					mo.affect = new ArrayList<NETADJUSTINFO>();
					while (it.hasNext())
					{
						mo.affect.add(NETADJUSTINFO.strToObject("AFFECTNETINFO", it.next().asXML()));
					}
				}
				return mo;
			}
			else
			{
				throw new XmlException("数据格式有误，请检查！" + str);
			}
		}
		catch(Exception e) {
			throw new XmlException("数据格式有误，解析出错！" + str);
		}
	}
}
