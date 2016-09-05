package org.andy.work.appserver.dao;

import java.util.List;

import org.andy.work.appserver.model.ICartHead;

public interface IShoppingCartDAO extends IGenericDAO {

	ICartHead getAccountCartHead(Integer accountId, boolean loadLazy);

	void deleteCartItemByProdIds(Integer accountId, List<Integer> prodIds);

}
