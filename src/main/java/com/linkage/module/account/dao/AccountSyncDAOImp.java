
package com.linkage.module.account.dao;

import java.util.Map;

import com.linkage.module.account.serv.mo.AccountMO;
import com.linkage.system.jdbc.jpa.JPABaseDAO;

/**
 * 该java的描述信息
 * 
 * @author shiliang Tel:13770527121
 * @version 1.0
 * @since Apr 10, 2012 6:08:25 PM .linkage.account.dao
 * @copyright
 */
public class AccountSyncDAOImp extends JPABaseDAO implements AccountSyncDAO
{

	private static String alias = AccountSyncDAOImp.class.getName();

	@Override
	public Map queryAccount(AccountMO accountMO)
	{
		// TODO Auto-generated method stub
		return (Map) this.getSqlSessionTemplate().selectOne(alias + ".queryAccounts",
				accountMO);
	}

	@Override
	public void addAccouts(AccountMO accountMO)
	{
		this.getSqlSessionTemplate().insert(alias + ".addAccouts", accountMO);
	}

	@Override
	public int queryMaxUserId()
	{
		return (Integer) getSqlSessionTemplate().selectOne(alias + ".queryMaxUserId");
	}

	@Override
	public void deleteAccounts(AccountMO accountMO)
	{
		getSqlSessionTemplate().delete(alias + ".deleteAccounts", accountMO);
	}

	@Override
	public void deleteArea(AccountMO accountMO)
	{
		getSqlSessionTemplate().delete(alias + ".deleteArea", accountMO);
	}

	@Override
	public void deletePersons(AccountMO accountMO)
	{
		getSqlSessionTemplate().delete(alias + ".deletePersons", accountMO);
	}

	@Override
	public void deleteRole(AccountMO accountMO)
	{
		getSqlSessionTemplate().delete(alias + ".deleteRole", accountMO);
	}

	@Override
	public void deleteUserArea(AccountMO accountMO)
	{
		getSqlSessionTemplate().delete(alias + ".deleteUserArea", accountMO);
	}

	@Override
	public void deleteUserGroup(AccountMO accountMO)
	{
		getSqlSessionTemplate().delete(alias + ".deleteUserGroup", accountMO);
	}

	@Override
	public void deleteUserRole(AccountMO accountMO)
	{
		getSqlSessionTemplate().delete(alias + ".deleteUserRole", accountMO);
	}

	@Override
	public void updateAccouts(AccountMO accountMO)
	{
		getSqlSessionTemplate().delete(alias + ".updateAccouts", accountMO);
	}

	@Override
	public void updatePersons(AccountMO accountMO)
	{
		getSqlSessionTemplate().delete(alias + ".updatePersons", accountMO);
	}

	@Override
	public void updateUserArea(AccountMO accountMO)
	{
		getSqlSessionTemplate().delete(alias + ".updateUserArea", accountMO);
	}

	@Override
	public void updateUserGroup(AccountMO accountMO)
	{
		getSqlSessionTemplate().delete(alias + ".updateUserGroup", accountMO);
	}

	@Override
	public void updateUserRole(AccountMO accountMO)
	{
		getSqlSessionTemplate().delete(alias + ".updateUserRole", accountMO);
	}
}
