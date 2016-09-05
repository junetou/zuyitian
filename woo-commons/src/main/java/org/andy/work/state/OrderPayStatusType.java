package org.andy.work.state;
/**
 *
 *developer
 *2015年2月12日下午3:35:36
 *
 */
public enum OrderPayStatusType
{
	PENDING("10","unpaid"),//未付款
	PAID("20","paid");//已付款
	
	private String code;
	private String description;
	
	private OrderPayStatusType(String code, String description)
	{
		this.code = code;
		this.description = description;
	}
	
	public String getCode()
	{
		return this.code;
	}
	
	public String getDescription() 
	{
		return this.description;
	}
	
	public static OrderPayStatusType getByCode(String code)
	{
		for (OrderPayStatusType type : OrderPayStatusType.values())
		{
			if (type.getCode().equals(code))
			{
				return type;
			}
		}
		
		return null;
	}
}
