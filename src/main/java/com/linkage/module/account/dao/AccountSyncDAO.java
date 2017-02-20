
package com.linkage.module.account.dao;

import java.util.Map;

import com.linkage.module.account.serv.mo.AccountMO;

/**
 * 该java的描述信息
 * 
 * @author shiliang Tel:13770527121
 * @version 1.0
 * @since Apr 10, 2012 5:52:37 PM .linkage.account.dao
 * @copyright
 */
public interface AccountSyncDAO
{

	public abstract Map queryAccount(AccountMO accountMO);

	public abstract int queryMaxUserId();

	public abstract void addAccouts(AccountMO accountMO);

	public abstract void deleteAccounts(AccountMO accountMO);

	public abstract void deletePersons(AccountMO accountMO);

	public abstract void deleteRole(AccountMO accountMO);

	public abstract void deleteUserRole(AccountMO accountMO);

	public abstract void deleteUserArea(AccountMO accountMO);

	public abstract void deleteArea(AccountMO accountMO);

	public abstract void deleteUserGroup(AccountMO accountMO);

	public abstract void updateAccouts(AccountMO accountMO);

	public abstract void updatePersons(AccountMO accountMO);

	public abstract void updateUserRole(AccountMO accountMO);

	public abstract void updateUserArea(AccountMO accountMO);

	public abstract void updateUserGroup(AccountMO accountMO);
}
