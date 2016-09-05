package org.andy.work.state;

/**
 * 付款方式类型
 * @author Administrator
 *
 */
public enum PaymentMethodType
{
	/*ONLINE 在线支付、OFFLINE 线下支付、BALANCE 余额支付*/
	ONLINE("10"), OFFLINE("20"), BALANCE("30");
	
	private String code;
	
	private PaymentMethodType(String code)
	{
		this.code = code;
	}
	
	public String getCode()
	{
		return this.code;
	}
	
	public static PaymentMethodType getByCode(String code)
	{
		for (PaymentMethodType method : PaymentMethodType.values())
		{
			if (method.code.equals(code))
			{
				return method;
			}
		}
		
		return null;
	}
	
	public static PaymentMethodType getByName(String name)
	{
		for (PaymentMethodType method : PaymentMethodType.values())
		{
			if (method.toString().equals(name))
			{
				return method;
			}
		}
		
		return null;
	}
}
