package org.andy.work.appserver.dao;

import java.util.Date;

import org.andy.work.appserver.model.IAccount;
import org.andy.work.appserver.model.ISaaccount;

public interface IAccountDAO extends IGenericDAO {

	ISaaccount getSaaccountByUsername(String username);

	ISaaccount getSaaccountBySecureCode(String secureCode);

	boolean isUsernameExist(String username);

	IAccount findAccountByUsername(String username);

	IAccount getAccountBySecurityCode(String secCode);

	boolean isExpirationDate(Date date, Integer id);

}
