package org.andy.work.state;
/**
 *
 *developer
 *2015年2月12日下午2:42:03
 *
 */
public enum OrderReturnItemStatusType {
	PENDING("10"), // 等待审核
	WAIT_FOR_RETURN_GOODS("20"), //处理中
	WAIT_FOR_RETURN_MONEY("30"), // 已完成
	REJUECT("50"); //拒绝退货
	
	private String code;
	
	private OrderReturnItemStatusType(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
