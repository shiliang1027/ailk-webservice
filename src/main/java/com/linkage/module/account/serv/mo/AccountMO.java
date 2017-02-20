
package com.linkage.module.account.serv.mo;

/**
 * 用户管理
 * 
 * @author shiliang 66614
 * @version 1.0
 * @since 2012-4-11 .linkage.account.serv.mo
 */
public class AccountMO
{
	private String accOid;// 用户主键
	private String accLoginName;// 登录名
	private String startTime;// 帐号创建开始时间
	private String endTime;// 帐号创建结束时间
	private String groupOid;// 组id(科室)
	private String accStatus = "1";// 帐号状态 0:锁定 1:活动
	private String password;// 密码
	private String isPassword;// 密码确认
	private String passValidateMonth = "1";// 密码有效期,单位：月
	private String accValidateMonth = "1";// 帐号有效期，单位：月
	private String roleId;// 隶属角色
	private String cityId;// 属地
	private String areaId;// 隶属域
	private String perName;// 姓名
	private String perPhone;// 联系电话
	private String email;
	private String remark;// 备注
	private String creator;// 帐号的创建者
	private String creationDate;// 帐号创建时间
	private String accPwdTime;// 密码创建时间
	private String ims_phone = "";// ims主叫号码
	private int acc_type = 2;// 账户类型

	public String getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(String creationDate)
	{
		this.creationDate = creationDate;
	}

	public String getAccPwdTime()
	{
		return accPwdTime;
	}

	public void setAccPwdTime(String accPwdTime)
	{
		this.accPwdTime = accPwdTime;
	}

	public String getCreator()
	{
		return creator;
	}

	public void setCreator(String creator)
	{
		this.creator = creator;
	}

	public String getAccOid()
	{
		return accOid;
	}

	public void setAccOid(String accOid)
	{
		this.accOid = accOid;
	}

	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	public String getRoleId()
	{
		return roleId;
	}

	public String getIsPassword()
	{
		return isPassword;
	}

	public void setIsPassword(String isPassword)
	{
		this.isPassword = isPassword;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPassValidateMonth()
	{
		return passValidateMonth;
	}

	public void setPassValidateMonth(String passValidateMonth)
	{
		this.passValidateMonth = passValidateMonth;
	}

	public String getAccValidateMonth()
	{
		return accValidateMonth;
	}

	public void setAccValidateMonth(String accValidateMonth)
	{
		this.accValidateMonth = accValidateMonth;
	}

	public String getCityId()
	{
		return cityId;
	}

	public void setCityId(String cityId)
	{
		this.cityId = cityId;
	}

	public String getAreaId()
	{
		return areaId;
	}

	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}

	public String getPerName()
	{
		return perName;
	}

	public void setPerName(String perName)
	{
		this.perName = perName;
	}

	public String getPerPhone()
	{
		return perPhone;
	}

	public void setPerPhone(String perPhone)
	{
		this.perPhone = perPhone;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getGroupOid()
	{
		return groupOid;
	}

	public void setGroupOid(String groupOid)
	{
		this.groupOid = groupOid;
	}

	public String getStartTime()
	{
		return startTime;
	}

	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}

	public String getEndTime()
	{
		return endTime;
	}

	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

	public String getAccLoginName()
	{
		return accLoginName;
	}

	public void setAccLoginName(String accLoginName)
	{
		this.accLoginName = accLoginName;
	}

	public String getAccStatus()
	{
		return accStatus;
	}

	public void setAccStatus(String accStatus)
	{
		this.accStatus = accStatus;
	}

	public String getIms_phone()
	{
		return ims_phone;
	}

	public void setIms_phone(String ims_phone)
	{
		this.ims_phone = ims_phone;
	}

	public int getAcc_type()
	{
		return acc_type;
	}

	public void setAcc_type(int acc_type)
	{
		this.acc_type = acc_type;
	}
}
