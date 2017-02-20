
package com.linkage.module.account.serv;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.linkage.module.account.dao.AccountSyncDAO;
import com.linkage.module.account.dao.AccountSyncExternalDAO;
import com.linkage.module.account.serv.mo.AccountMO;
import com.linkage.module.service.outer.WebServiceConstant;
import com.linkage.module.service.outer.mo.RtInfo;
import com.linkage.module.service.serv.Service;
import com.linkage.system.designpattern.observer.MapEventObject;

/**
 * 该java的描述信息
 * 
 * @author shiliang Tel:13770527121
 * @version 1.0
 * @since Apr 10, 2012 5:51:46 PM .linkage.account.serv
 * @copyright
 */
public class AccountSyncService implements Service
{

	private static Logger log = Logger.getLogger(AccountSyncService.class);
	private AccountSyncDAO dao;
	private AccountSyncExternalDAO externalDao;

	@Override
	public void process(MapEventObject object)
	{
		// TODO Auto-generated method stub
		String xmlData = (String) object.getProperty(WebServiceConstant.DATA);
		log.info(xmlData);
		SAXReader reader = new SAXReader();
		Document doc = null;
		try
		{
			doc = reader.read(new ByteArrayInputStream(xmlData.getBytes()));
		}
		catch (DocumentException e)
		{
			e.printStackTrace();
		}
		Element root = doc.getRootElement();
		String opt = root.elementText("opt").trim();
		String accountName = root.elementText("accountName").trim();
		log.info("[账号同步]操作:" + opt + ",账号:" + accountName);
		AccountMO accountMO = new AccountMO();
		accountMO.setAcc_type(2);
		accountMO.setAccLoginName(accountName);
		if ("add".equals(opt))
		{
			addAccount(object, accountMO);
		}
		else if ("mod".equals(opt))
		{
			modAccount(object, accountMO);
		}
		else if ("del".equals(opt))
		{
			delAccount(object, accountMO);
		}
	}

	private void addAccount(MapEventObject object, AccountMO accountMO)
	{
		// TODO Auto-generated method stub
		Map account = dao.queryAccount(accountMO);
		Map accountMoMap = externalDao.queryAccount(accountMO);
		if (account == null && accountMoMap != null)
		{
			accountMO.setPerName((String) accountMoMap.get("perName"));
			accountMO.setEmail((String) accountMoMap.get("email"));
			accountMO.setPerPhone((String) accountMoMap.get("perPhone"));
			// 
			accountMO.setPassword(accountMO.getAccLoginName());
			accountMO.setAccOid(String.valueOf(dao.queryMaxUserId() + 1));// 用户主键
			// accValidateMonth
			Date accValidateMonthDate = (Date) accountMoMap.get("accValidateMonth");
			int validateMonth = getValidateMonth(accValidateMonthDate);
			accountMO.setAccValidateMonth(String.valueOf(validateMonth));
			accountMO.setIms_phone(accountMO.getPerPhone());// ims_phone
			accountMO.setCreationDate(getCurrentDate());// 帐号创建时间
			accountMO.setAccPwdTime(getCurrentDate());// 密码创建时间
			accountMO.setRemark("4A用户");
			accountMO.setCreator("1");// 创建者
			accountMO.setAcc_type(2);
			dao.addAccouts(accountMO);
			log.info("[账号新增]成功:" + accountMO.getAccLoginName());
			object.putProperty(WebServiceConstant.RESULT, new RtInfo("1", "账号新增成功")
					.toXML());
		}
		else if (account == null && accountMoMap == null)
		{
			log.info("[账号新增]账号在4A不存在:" + accountMO.getAccLoginName());
			object.putProperty(WebServiceConstant.RESULT, new RtInfo("-1", "账号在4A不存在")
					.toXML());
		}
		else
		{
			log.info("[账号新增]账号已存在:" + accountMO.getAccLoginName());
			object.putProperty(WebServiceConstant.RESULT, new RtInfo("-1", "账号已存在")
					.toXML());
		}
	}

	private void modAccount(MapEventObject object, AccountMO accountMO)
	{
		// TODO Auto-generated method stub
		Map account = dao.queryAccount(accountMO);
		Map accountMoMap = externalDao.queryAccount(accountMO);
		if (account == null || accountMoMap == null)
		{
			log.info("[账号在4A不存在]或[帐号在综合监控不存在]");
			return;
		}
		else if (account == null && accountMoMap != null)
		{
			accountMO.setPerName((String) accountMoMap.get("perName"));
			accountMO.setEmail((String) accountMoMap.get("email"));
			accountMO.setPerPhone((String) accountMoMap.get("perPhone"));
			// 
			accountMO.setPassword(accountMO.getAccLoginName());
			accountMO.setAccOid(String.valueOf(dao.queryMaxUserId() + 1));// 用户主键
			// accValidateMonth
			Date accValidateMonthDate = (Date) accountMoMap.get("accValidateMonth");
			int validateMonth = getValidateMonth(accValidateMonthDate);
			accountMO.setAccValidateMonth(String.valueOf(validateMonth));
			accountMO.setIms_phone(accountMO.getPerPhone());// ims_phone
			accountMO.setCreationDate(getCurrentDate());// 帐号创建时间
			accountMO.setAccPwdTime(getCurrentDate());// 密码创建时间
			accountMO.setCreator("1");// 创建者
			accountMO.setAcc_type(2);
			dao.addAccouts(accountMO);
		}
		else
		{
			accountMO.setPerName((String) accountMoMap.get("perName"));
			accountMO.setEmail((String) accountMoMap.get("email"));
			accountMO.setPerPhone((String) accountMoMap.get("perPhone"));
			// 
			accountMO.setAccOid(String.valueOf(account.get("accOid")));
			// accValidateMonth
			Date accValidateMonthDate = (Date) accountMoMap.get("accValidateMonth");
			int validateMonth = getValidateMonth(accValidateMonthDate);
			accountMO.setAccValidateMonth(String.valueOf(validateMonth));
			accountMO.setIms_phone(accountMO.getPerPhone());// ims_phone
			accountMO.setCreationDate(getCurrentDate());// 帐号创建时间
			accountMO.setAccPwdTime(getCurrentDate());// 密码创建时间
			accountMO.setRemark("4A用户");
			accountMO.setCreator("1");// 创建者
			accountMO.setAcc_type(2);
			dao.updateAccouts(accountMO);
			dao.updatePersons(accountMO);
		}
		log.info("[账号更新]");
		object.putProperty(WebServiceConstant.RESULT, new RtInfo("2", "账号更新成功").toXML());
	}

	private void delAccount(MapEventObject object, AccountMO accountMO)
	{
		// TODO Auto-generated method stub
		Map account = dao.queryAccount(accountMO);
		if (account == null)
		{
			log.info("[帐号在综合监控不存在]");
			return;
		}
		accountMO.setAccOid(String.valueOf(account.get("accOid")));
		try
		{
			dao.deleteAccounts(accountMO);
			dao.deletePersons(accountMO);
			dao.deleteRole(accountMO);
			dao.deleteUserRole(accountMO);
			dao.deleteArea(accountMO);
			dao.deleteUserArea(accountMO);
			dao.deleteUserGroup(accountMO);
			log.info("[账号删除]成功：" + accountMO.getAccLoginName());
			object.putProperty(WebServiceConstant.RESULT, new RtInfo("3", "账号删除成功")
					.toXML());
		}
		catch (Exception e)
		{
			log.error("[账号删除]失败：" + accountMO.getAccLoginName());
			object.putProperty(WebServiceConstant.RESULT, new RtInfo("-3", "账号删除失败")
					.toXML());
		}
	}

	/**
	 * 得到有效月份
	 * 
	 * @param cancelTime
	 * @return
	 */
	private int getValidateMonth(Date cancelTime)
	{
		log.info("[cancelTime]：" + cancelTime);
		int cancelYear = cancelTime.getYear();
		int cancelMonth = cancelTime.getMonth();
		Date nowDate = new Date();
		int month = (cancelYear - nowDate.getYear()) * 12
				+ (cancelMonth - nowDate.getMonth());
		if (month < 1)
		{
			return 0;
		}
		else
		{
			return month;
		}
	}

	/**
	 * 取得当前日期的时间值
	 * 
	 * @return
	 */
	private String getCurrentDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sdf.format(new Date());
	}

	public AccountSyncDAO getDao()
	{
		return dao;
	}

	public void setDao(AccountSyncDAO dao)
	{
		this.dao = dao;
	}

	public AccountSyncExternalDAO getExternalDao()
	{
		return externalDao;
	}

	public void setExternalDao(AccountSyncExternalDAO externalDao)
	{
		this.externalDao = externalDao;
	}
}
