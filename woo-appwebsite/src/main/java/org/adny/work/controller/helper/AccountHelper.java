package org.adny.work.controller.helper;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.adny.work.controller.form.RegisterForm;
import org.andy.work.appserver.model.IAccount;
import org.andy.work.appserver.model.IAccountBalance;
import org.andy.work.appserver.model.IAccountSecurity;
import org.andy.work.appserver.model.ISaaccount;
import org.andy.work.appserver.service.IAccountMaintenanceService;
import org.andy.work.obj.BalanceDisplay;
import org.andy.work.state.AccountStatusType;
import org.andy.work.type.AccountType;
import org.andy.work.utils.AuthorityUtil;
import org.andy.work.utils.StringUtil;
import org.springframework.stereotype.Component;

@Component
public class AccountHelper {
	
	@Resource
	private IAccountMaintenanceService accountMaintenanceService;

	public IAccount findAccountByUsername(String username) {
		return this.accountMaintenanceService.findAccountByUsername(username);
	}

	public IAccount getCurrenctUserAccount() {
		return null;
	}

	public IAccount refreshAccountBalance(Integer accountId) {
		return this.accountMaintenanceService.refreshAccountBalance(accountId);
		
	}

	public BalanceDisplay getAccountBalanceDisplay(IAccount account) {
		return null;
	}

	public void updateAccount(IAccount account) {
		
	}

	public ISaaccount registAccount(RegisterForm accountForm) {
		ISaaccount saaccount = this.accountMaintenanceService.getSaaccountByUsername(accountForm.getAccName());
		if (saaccount != null) {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 2);
			saaccount.setExpriedDate(c.getTime());
			
			saaccount.setCustomerName(accountForm.getCustName());
			
			String salt = AuthorityUtil.generateSalt();
			saaccount.setPassword(AuthorityUtil.hashPassword(accountForm.getPwd(), salt));
			saaccount.setSalt(salt);
			
			String secureCode = AuthorityUtil.hmac(saaccount.getUsername(), salt);
			saaccount.setSecureCode(secureCode);
			
			saaccount = this.accountMaintenanceService.updateSaaccount(saaccount);
		} else {
			saaccount = this.accountMaintenanceService.createSaaccount();
			saaccount.setUsername(StringUtil.trim(accountForm.getAccName()));
			saaccount.setEmail(saaccount.getUsername());
			saaccount.setAccountType(AccountType.B2C);
			saaccount.setCustomerName(accountForm.getCustName());
			
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 2);
			saaccount.setExpriedDate(c.getTime());
			
			String salt = AuthorityUtil.generateSalt();
			saaccount.setPassword(AuthorityUtil.hashPassword(accountForm.getPwd(), salt));
			saaccount.setSalt(salt);
			
			String secureCode = AuthorityUtil.hmac(saaccount.getUsername(), salt);
			saaccount.setSecureCode(secureCode);
			
			saaccount = this.accountMaintenanceService.persistSaaccount(saaccount);
		}
		return saaccount;
	}

	public ISaaccount getSaaccountBySecureCode(String secureCode) {
		ISaaccount saaccount = this.accountMaintenanceService.getSaaccountBySecureCode(secureCode);
		return saaccount;
	}

	public boolean isSaacountExpried(ISaaccount saaccount) {
		if (saaccount.getExpriedDate().before(new Date())) {
			return true;
		}
		return false;
	}

	public void deleteSaaccount(ISaaccount saaccount) {
		this.accountMaintenanceService.deleteSaaccount(saaccount);
	}

	public IAccount accountActivation(ISaaccount saaccount) {
		IAccount account = this.createAccount();
		account.setAcctType(saaccount.getAccountType());
		account.setLocked("");
		account.setStatus(AccountStatusType.ACTIVE);
		account.setUsername(saaccount.getUsername());
		account.setNickname(saaccount.getCustomerName());
		
		IAccountBalance accountBalance = this.accountMaintenanceService.createAccountBalace();
		accountBalance.setBalanceAmount(0D);
		accountBalance.setCreditLimit(0D);
		
		account.setAccountBalance(accountBalance);
		
		IAccountSecurity accountSecurity = this.accountMaintenanceService.createAccountSecurity();
		accountSecurity.setAccountPassword(saaccount.getPassword());
		accountSecurity.setAuthEmail("Y");
		accountSecurity.setEmail(saaccount.getEmail());
		accountSecurity.setSalt(saaccount.getSalt());
		
		account.setAccountSecurity(accountSecurity);
		
		account = this.accountMaintenanceService.persistAccount(account);
		this.accountMaintenanceService.deleteSaaccount(saaccount);
		
		return account;
	}

	private IAccount createAccount() {
		return this.accountMaintenanceService.createAccount();
	}

	public boolean isUsernameExist(String username) {
		return this.accountMaintenanceService.isUsernameExist(username);
	}

	public IAccount getAccountBySecurityCode(String secCode) {
		return this.accountMaintenanceService.getAccountBySecurityCode(secCode);
	}

	public boolean isExpirationDate(Date date, Integer id) {
		return this.accountMaintenanceService.isExpirationDate(date, id);
	}

}
