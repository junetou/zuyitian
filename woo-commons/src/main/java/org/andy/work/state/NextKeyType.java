package org.andy.work.state;

public class NextKeyType
{
	
	//insert nextkey(type,def_start, extra, length, next, prefix, version) value('account_num',0, '', 8, 1, '', 0)
	public final static String AUTH_ACCOUNT_NUM = "account_num";//自动生成的账户
	//insert nextkey(type,def_start, extra, length, next, prefix, version) value('prod_num',0, 'DY', 10, 1, 'DY', 0)
	public final static String PROD_NUM = "prod_num";//商品编号
	//insert nextkey(type,def_start, extra, length, next, prefix, version) value('trade_num',0, 'DY', 10, 1, 'DY', 0)
	public final static String TRADE_NUM = "trade_num";//交易记录编号
	//insert nextkey(type,def_start, extra, length, next, prefix, version) value('deposit_num',0, 'DY', 10, 1, 'DY', 0)
	public final static String DEPOSIT_NUM = "deposit_num";//收款单编号
	//insert nextkey(type,def_start, extra, length, next, prefix, version) value('order_num',0, 'DY', 10, 1, 'DY', 0)
	public final static String ORDER_NUM = "order_num";//订单编号
	//insert nextkey(type,def_start, extra, length, next, prefix, version) value('return_num',0, 'DY', 10, 1, 'DY', 0)
	public final static String RETURN_NUM = "return_num";//退货单编号
	//insert nextkey(type,def_start, extra, length, next, prefix, version) value('author_num',0, 'DY', 15, 1, 'DY', 0)
	public final static String AUTHOR_NUM = "author_num";//授权书编号
	
}
