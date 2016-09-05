package org.andy.work.state;

/**
 * 付款信息类型
 * @author Administrator
 *
 */
public class PaymentInfoType {
	
	//订单支付
	public static final String ORDER_PAY = "ORDER_PAY";
	//账户充值
	public static final String RECHARGE = "RECHARGE";
	//账户提现
	public static final String TAKE = "TAKE";
	//微信红包
	public static final String RED_ENVELOPE = "RED_ENVELOPE";
	//微信打款
	public static final String ENTERPRISE_PAY = "ENTERPRISE_PAY";
	
	public static boolean isIncome(String type)
	{
		return RECHARGE.equals(type);
	}
}
