package org.andy.work.appserver.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.IAccountDAO;
import org.andy.work.appserver.model.IAccount;
import org.andy.work.appserver.model.IAccountBalance;
import org.andy.work.appserver.model.IAccountSecurity;
import org.andy.work.appserver.model.ISaaccount;
import org.andy.work.appserver.model.impl.Account;
import org.andy.work.appserver.model.impl.AccountBalance;
import org.andy.work.appserver.model.impl.AccountSecurity;
import org.andy.work.appserver.model.impl.Saaccount;
import org.andy.work.appserver.service.IAccountMaintenanceService;
import org.andy.work.utils.MathUtil;
import org.springframework.stereotype.Service;

@Service
public class AccountMaintenanceService implements IAccountMaintenanceService {
	
	@Resource
	private IAccountDAO accountDAO;
		
	@Override
	public IAccount findAccountByUsername(String username) {
		return this.accountDAO.findAccountByUsername(username);
	}

	@Override
	public ISaaccount getSaaccountByUsername(String username) {
		return this.accountDAO.getSaaccountByUsername(username);
	}

	@Override
	public ISaaccount updateSaaccount(ISaaccount saaccount) {
		this.accountDAO.makeUpdate(saaccount);
		return saaccount;
	}

	@Override
	public ISaaccount createSaaccount() {
		ISaaccount saaccount = new Saaccount();
		saaccount.setCreatedDate(new Date());
		return saaccount;
	}
	
	@Override
	public ISaaccount persistSaaccount(ISaaccount saaccount) {
		return (ISaaccount) this.accountDAO.makePersist(saaccount);
	}

	@Override
	public ISaaccount getSaaccountBySecureCode(String secureCode) {
		return this.accountDAO.getSaaccountBySecureCode(secureCode);
	}
	
	@Override
	public void deleteSaaccount(ISaaccount saaccount) {
		this.accountDAO.deleteEntity(saaccount);
	}

	@Override
	public IAccountBalance createAccountBalace() {
		IAccountBalance accountBalance = new AccountBalance();
		accountBalance.setCreatedDate(new Date());
		return accountBalance;
	}

	@Override
	public IAccountSecurity createAccountSecurity() {
		IAccountSecurity accountSecurity = new AccountSecurity();
		accountSecurity.setCreatedDate(new Date());
		
		return accountSecurity;
	}

	@Override
	public IAccount persistAccount(IAccount account) {
		return (IAccount) this.accountDAO.makePersist(account);
	}
	
	@Override
	public IAccount createAccount() {
		IAccount account = new Account();
		account.setCreatedDate(new Date());
		return account;
	}
	
	@Override
	public boolean isUsernameExist(String username) {
		return this.accountDAO.isUsernameExist(username);
	}

	@Override
	public IAccount refreshAccountBalance(Integer accountId) {
		double balanceAmount = 0;
		double totalIncome = 0.0;//this.tradeRecordDAO.calculateAccountIncome(accountId);
		double totalConsume = 0.0;//this.tradeRecordDAO.calculateAccountConsume(accountId);
		
		totalIncome = MathUtil.round(totalIncome, 2).doubleValue();
		totalConsume = MathUtil.round(totalConsume, 2).doubleValue();
		
		balanceAmount = MathUtil.subtract(totalIncome, totalConsume, 2).doubleValue();
		
		IAccount account = (IAccount) this.accountDAO.getEntityById(Account.class, accountId);
		IAccountBalance balance = account.getAccountBalance();
		if (balance == null) {
			balance = this.createAccountBalace();
			balance.setCreditLimit(0D);
			
			account.setAccountBalance(balance);
		}
		
		balance.setBalanceAmount(balanceAmount);
		balance.setUpdatedDate(new Date());
	
		this.accountDAO.makeUpdate(balance);
		
		return account;
	}

	@Override
	public IAccount getAccountBySecurityCode(String secCode) {
		return this.accountDAO.getAccountBySecurityCode(secCode);
	}
	
	@Override
	public boolean isExpirationDate(Date date, Integer id) {
		return this.accountDAO.isExpirationDate(date, id);
	}
}
