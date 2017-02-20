
package com.linkage.module.alarm.fault.serv.mo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.linkage.system.utils.DateTimeUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class CustFaultMsg implements Msg
{

	static Logger log = Logger.getLogger(CustFaultMsg.class);
	private String flowId;
	private String formNo;
	private String startTime;
	private String dkhFlowId;
	private String isSolve;
	private String htName;
	private String htDescription;
	private String ownerName;
	private String deptName;
	private String roleName;
	private String cellPhone;
	private String stateCn;
	private String noSolveReason;
	private String dispatchSource;
	private String faultReason;
	private String isAccept;
	private String faultType;
	private String isDealSuccess;
	private String classTwoId;
	private String alarmResumeTime;
	private String dealDesc;
	private String description;
	private String postponeTime;
	private String postponeCause;
	private String sheetstatus;
	private String sheetpart;
	private String sheettime;

	public String getFlowId()
	{
		return flowId;
	}

	public void setFlowId(String flowId)
	{
		this.flowId = flowId;
	}

	public String getFormNo()
	{
		return formNo;
	}

	public void setFormNo(String formNo)
	{
		this.formNo = formNo;
	}

	public String getHtName()
	{
		return htName;
	}

	public void setHtName(String htName)
	{
		this.htName = htName;
	}

	public String getHtDescription()
	{
		return htDescription;
	}

	public void setHtDescription(String htDescription)
	{
		this.htDescription = htDescription;
	}

	public String getOwnerName()
	{
		return ownerName;
	}

	public void setOwnerName(String ownerName)
	{
		this.ownerName = ownerName;
	}

	public String getDeptName()
	{
		return deptName;
	}

	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	public String getCellPhone()
	{
		return cellPhone;
	}

	public void setCellPhone(String cellPhone)
	{
		this.cellPhone = cellPhone;
	}

	public String getStateCn()
	{
		return stateCn;
	}

	public void setStateCn(String stateCn)
	{
		this.stateCn = stateCn;
	}

	public String getNoSolveReason()
	{
		return noSolveReason;
	}

	public void setNoSolveReason(String noSolveReason)
	{
		this.noSolveReason = noSolveReason;
	}

	public String getClassTwoId()
	{
		return classTwoId;
	}

	public void setClassTwoId(String classTwoId)
	{
		this.classTwoId = classTwoId;
	}

	public String getDispatchSource()
	{
		return dispatchSource;
	}

	public void setDispatchSource(String dispatchSource)
	{
		this.dispatchSource = dispatchSource;
	}

	public String getFaultReason()
	{
		return faultReason;
	}

	public void setFaultReason(String faultReason)
	{
		this.faultReason = faultReason;
	}

	public String getIsAccept()
	{
		return isAccept;
	}

	public void setIsAccept(String isAccept)
	{
		this.isAccept = isAccept;
	}

	public String getFaultType()
	{
		return faultType;
	}

	public void setFaultType(String faultType)
	{
		this.faultType = faultType;
	}

	public String getIsDealSuccess()
	{
		return isDealSuccess;
	}

	public void setIsDealSuccess(String isDealSuccess)
	{
		this.isDealSuccess = isDealSuccess;
	}

	public String getAlarmResumeTime()
	{
		return alarmResumeTime;
	}

	public void setAlarmResumeTime(String alarmResumeTime)
	{
		this.alarmResumeTime = alarmResumeTime;
	}

	public String getDealDesc()
	{
		return dealDesc;
	}

	public void setDealDesc(String dealDesc)
	{
		this.dealDesc = dealDesc;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getSheettime()
	{
		return sheettime;
	}

	public void setSheettime(String sheettime)
	{
		this.sheettime = sheettime;
	}

	public String getStartTime()
	{
		return startTime;
	}

	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}

	public String getDkhFlowId()
	{
		return dkhFlowId;
	}

	public void setDkhFlowId(String dkhFlowId)
	{
		this.dkhFlowId = dkhFlowId;
	}

	public String getIsSolve()
	{
		return isSolve;
	}

	public void setIsSolve(String isSolve)
	{
		this.isSolve = isSolve;
	}

	public String getPostponeTime()
	{
		return postponeTime;
	}

	public void setPostponeTime(String postponeTime)
	{
		this.postponeTime = postponeTime;
	}

	public String getPostponeCause()
	{
		return postponeCause;
	}

	public void setPostponeCause(String postponeCause)
	{
		this.postponeCause = postponeCause;
	}

	public String getSheetstatus()
	{
		if ("待受理".equals(stateCn))
		{
			sheetstatus = "2";
		}
		else if ("在处理".equals(stateCn))
		{
			sheetstatus = "3";
		}
		else if ("完成".equals(stateCn))
		{
			sheetstatus = "7";
		}
		return sheetstatus;
	}

	public void setSheetstatus(String sheetstatus)
	{
		this.sheetstatus = sheetstatus;
	}

	public String getSheetpart()
	{
		if ("待受理".equals(stateCn))
		{
			sheetpart = "待受理";
		}
		else if ("在处理".equals(stateCn))
		{
			sheetpart = "在处理";
		}
		else if ("完成".equals(stateCn))
		{
			sheetpart = "已归档";
		}
		return sheetpart;
	}

	public void setSheetpart(String sheetpart)
	{
		this.sheetpart = sheetpart;
	}

	public Msg strToObject(String str)
	{
		XStream xstream = new XStream(new DomDriver());
		// 设置节点名
		xstream.alias("form", CustFaultMsg.class);
		return (CustFaultMsg) xstream.fromXML(str);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map> objectToMap()
	{
		log.info("objectToMap:" + this.formNo);
		List<Map> list = new ArrayList<Map>();
		try
		{
			Map temp = PropertyUtils.describe(this);
			temp.put("sheetno", this.formNo);
			temp.put("sheetstatus", getSheetstatus());
			temp.put("sheetpart", getSheetpart());
			if (startTime != null && !"".equals(startTime))
			{
				temp.put("sheetstarttime", new DateTimeUtil(startTime).getLongTime());
			}
			list.add(temp);
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String getStatus()
	{
		return getSheetstatus();
	}
}
