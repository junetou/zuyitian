package org.andy.work.appserver.service;

import java.util.List;

import org.andy.work.appserver.model.ICartDetail;
import org.andy.work.appserver.model.ICartHead;

public interface IShoppingCartMaintenanceService {

	ICartHead getAccountCartHead(Integer accountId, boolean loadLazy);

	ICartHead createCartHead();

	ICartDetail createCartDetail();

	ICartHead persistCartHead(ICartHead cartHead);

	ICartHead updateCartHead(ICartHead cartHead);

	ICartDetail persistCartDetail(ICartDetail cartDetail);

	ICartDetail updateCartDetail(ICartDetail cartDetail);

	void deleteCartItemByProdIds(Integer accountId, List<Integer> asList);

	void cleanShoppingCart(Integer accountId);

}
