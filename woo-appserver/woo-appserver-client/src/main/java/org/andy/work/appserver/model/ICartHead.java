package org.andy.work.appserver.model;

import java.util.Date;
import java.util.Set;

public interface ICartHead
{

	Integer getId();

	void setId(Integer id);
	
	IAccount getAccount();
	
	void setAccount(IAccount account);

	Integer getItemCount();

	void setItemCount(Integer itemCount);

	Double getTotalAmount();

	void setTotalAmount(Double totalAmount);

	Date getLastUpdatedDate();

	void setLastUpdatedDate(Date lastUpdatedDate);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);
	
	Set<ICartDetail> getCartDetails();
	
	void setCartDetails(Set<ICartDetail> cartDetails);

}