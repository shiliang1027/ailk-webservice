
package com.linkage.module.account.dao;

import java.util.Map;

import com.linkage.module.account.serv.mo.AccountMO;
import com.linkage.system.jdbc.jpa.JPABaseDAO;

/**
 * 该java的描述信息
 * 
 * @author shiliang Tel:13770527121
 * @version 1.0
 * @since Apr 11, 2012 2:11:35 PM .linkage.account.dao
 * @copyright
 */
public class AccountSyncExternalDAOImp extends JPABaseDAO implements
		AccountSyncExternalDAO
{

	private static String alias = AccountSyncExternalDAOImp.class.getName();

	@Override
	public Map queryAccount(AccountMO accountMO)
	{
		return (Map) this.getSqlSessionTemplate().selectOne(alias + ".queryAccounts",
				accountMO);
	}
}
