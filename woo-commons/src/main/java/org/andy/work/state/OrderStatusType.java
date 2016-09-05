package org.andy.work.state;

import java.util.ArrayList;
import java.util.List;

public enum OrderStatusType
{
	PENDING("10", "waitingPayment"), //等待付款
	WAIT_FOR_DELIVERY("20", "waitingDelivery"), //等待发货
	PICKING_UP("30", "pickingup"), //已拣货
	SHIPPED("40", "delivered"), //已发货
	COMPLETE("50", "transactionCompletion"),//交易完成
	CANCEL("60", "tradeOff"),//交易关闭
	PART_SHIPED("70", "partialShipment"),//商品缺货
	OVERDUE("80", "overdueOrder");//过期订单
	
	private String code;
	private String description;
	
	private OrderStatusType(String code, String description)
	{
		this.code = code;
		this.description = description;
	}
	
	public static OrderStatusType getByCode(String code)
	{
		for (OrderStatusType type : OrderStatusType.values())
		{
			if (type.getCode().equals(code))
			{
				return type;
			}
		}
		
		return null;
	}
	
	public static OrderStatusType getByName(String name)
	{
		for (OrderStatusType type : OrderStatusType.values())
		{
			if (type.toString().equals(name))
			{
				return type;
			}
		}
		
		return null;
	}
	
	public static List<String> getUnpaidStatus()
	{
		List<String> list = new ArrayList<String>();
		list.add(PENDING.getCode());
		list.add(CANCEL.getCode());
		list.add(OVERDUE.getCode());
		return list;
	}
	
	public String getCode()
	{
		return code;
	}
	
	public String getDescription() 
	{
		return description;
	}
}
