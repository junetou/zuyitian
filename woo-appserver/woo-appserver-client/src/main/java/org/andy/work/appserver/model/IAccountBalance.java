package org.andy.work.appserver.model;


import java.util.Date;

public interface IAccountBalance
{

	Integer getId();

	void setId(Integer id);

	Double getBalanceAmount();

	void setBalanceAmount(Double balanceAmount);

	Double getCreditLimit();

	void setCreditLimit(Double creditLimit);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();

	void setUpdatedDate(Date updatedDate);

}