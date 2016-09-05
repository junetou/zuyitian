package org.andy.work.state;
/**
 *
 *developer
 *2015年3月3日下午9:38:59
 *
 */
public enum OrderReturnStatusType
{
	PENDING("10"), // 等待审核
	PROCESSING("20"), //处理中
	COMPLETE("30"), //已完成
	REJECT("50"); //拒绝退货
	
	private String code;
	
	private OrderReturnStatusType(String code)
	{
		this.code = code;
	}
	
	public String getCode()
	{
		return this.code;
	}
}
