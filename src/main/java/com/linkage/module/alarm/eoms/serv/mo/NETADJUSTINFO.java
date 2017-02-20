package com.linkage.module.alarm.eoms.serv.mo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class NETADJUSTINFO
{
	private String MO_ID;
	private String INFO_ID;
	private String NENAME;
	private String CODE;
	private String INFOTYPE;
	private String RELATIONALARM;
	private String EMSNAME;
	private String REGIONNAME;
	private String TRANSNE;
	private String EMSID;
	private String REGION;
	public String getINFO_ID() {
		return INFO_ID;
	}
	public void setINFO_ID(String iNFOID) {
		INFO_ID = iNFOID;
	}
	public String getNENAME() {
		return NENAME;
	}
	public void setNENAME(String nENAME) {
		NENAME = nENAME;
	}
	public String getCODE() {
		return CODE;
	}
	public void setCODE(String cODE) {
		CODE = cODE;
	}
	public String getINFOTYPE() {
		return INFOTYPE;
	}
	public void setINFOTYPE(String iNFOTYPE) {
		INFOTYPE = iNFOTYPE;
	}
	public String getRELATIONALARM() {
		return RELATIONALARM;
	}
	public void setRELATIONALARM(String rELATIONALARM) {
		RELATIONALARM = rELATIONALARM;
	}
	public String getEMSNAME() {
		return EMSNAME;
	}
	public void setEMSNAME(String eMSNAME) {
		EMSNAME = eMSNAME;
	}
	public String getREGIONNAME() {
		return REGIONNAME;
	}
	public void setREGIONNAME(String rEGIONNAME) {
		REGIONNAME = rEGIONNAME;
	}
	public String getTRANSNE() {
		return TRANSNE;
	}
	public void setTRANSNE(String tRANSNE) {
		TRANSNE = tRANSNE;
	}
	public String getEMSID() {
		return EMSID;
	}
	public void setEMSID(String eMSID) {
		EMSID = eMSID;
	}
	public String getREGION() {
		return REGION;
	}
	public void setREGION(String rEGION) {
		REGION = rEGION;
	}
	public String getMO_ID() {
		return MO_ID;
	}
	public void setMO_ID(String mOID) {
		MO_ID = mOID;
	}
	public static NETADJUSTINFO strToObject(String alias,String str)
	{
		XStream xstream = new XStream(new DomDriver());
		// 设置节点名
		xstream.alias(alias, NETADJUSTINFO.class);
		return (NETADJUSTINFO) xstream.fromXML(str);
	}
}
