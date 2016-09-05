package org.andy.work.appserver.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.IShoppingCartDAO;
import org.andy.work.appserver.model.ICartDetail;
import org.andy.work.appserver.model.ICartHead;
import org.andy.work.appserver.model.impl.CartDetail;
import org.andy.work.appserver.model.impl.CartHead;
import org.andy.work.appserver.service.IShoppingCartMaintenanceService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartMaintenanceService implements IShoppingCartMaintenanceService {

	@Resource
	private IShoppingCartDAO shoppingCartDAO;

	@Override
	public ICartHead getAccountCartHead(Integer accountId, boolean loadLazy) {
		return this.shoppingCartDAO.getAccountCartHead(accountId, loadLazy);
	}

	@Override
	public ICartHead createCartHead() {
		ICartHead cartHead = new CartHead();
		cartHead.setCreatedDate(new Date());
		return cartHead;
	}

	@Override
	public ICartDetail createCartDetail() {
		ICartDetail cartDetail = new CartDetail();
		return cartDetail;
	}

	@Override
	public ICartHead persistCartHead(ICartHead cartHead) {
		return (ICartHead) this.shoppingCartDAO.makePersist(cartHead);
	}

	@Override
	public ICartHead updateCartHead(ICartHead cartHead) {
		this.shoppingCartDAO.makeUpdate(cartHead);
		return cartHead;
	}

	@Override
	public ICartDetail persistCartDetail(ICartDetail cartDetail) {
		return (ICartDetail) this.shoppingCartDAO.makePersist(cartDetail);
	}

	@Override
	public ICartDetail updateCartDetail(ICartDetail cartDetail) {
		this.shoppingCartDAO.makeUpdate(cartDetail);
		return cartDetail;
	}
	
	@Override
	public void deleteCartItemByProdIds(Integer accountId, List<Integer> prodIds) {
		this.shoppingCartDAO.deleteCartItemByProdIds(accountId, prodIds);
	}
	
	@Override
	public void cleanShoppingCart(Integer accountId) {
		ICartHead cartHead = this.getAccountCartHead(accountId, false);
		if (cartHead != null) {
			this.shoppingCartDAO.deleteEntity(cartHead);
		}
	}
	
}
