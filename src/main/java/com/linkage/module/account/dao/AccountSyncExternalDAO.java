
package com.linkage.module.account.dao;

import java.util.Map;

import com.linkage.module.account.serv.mo.AccountMO;

/**
 * 该java的描述信息
 * 
 * @author shiliang Tel:13770527121
 * @version 1.0
 * @since Apr 11, 2012 2:04:10 PM .linkage.account.dao
 * @copyright
 */
public interface AccountSyncExternalDAO
{

	public abstract Map queryAccount(AccountMO accountMO);
}
