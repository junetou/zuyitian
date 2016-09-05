package org.andy.work.appserver.service;

import java.util.Date;

import org.andy.work.appserver.model.IAccount;
import org.andy.work.appserver.model.IAccountBalance;
import org.andy.work.appserver.model.IAccountSecurity;
import org.andy.work.appserver.model.ISaaccount;


public interface IAccountMaintenanceService {

	IAccount findAccountByUsername(String username);

	ISaaccount getSaaccountByUsername(String username);

	ISaaccount updateSaaccount(ISaaccount saaccount);

	ISaaccount createSaaccount();

	ISaaccount persistSaaccount(ISaaccount saaccount);

	ISaaccount getSaaccountBySecureCode(String secureCode);

	void deleteSaaccount(ISaaccount saaccount);

	IAccountBalance createAccountBalace();

	IAccountSecurity createAccountSecurity();

	IAccount persistAccount(IAccount account);

	IAccount createAccount();

	boolean isUsernameExist(String username);

	IAccount refreshAccountBalance(Integer accountId);

	IAccount getAccountBySecurityCode(String secCode);

	boolean isExpirationDate(Date date, Integer id);
	
}
