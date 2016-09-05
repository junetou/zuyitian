package org.andy.work.appserver.dao.impl;

import java.util.List;

import org.andy.work.appserver.dao.IShoppingCartDAO;
import org.andy.work.appserver.model.ICartDetail;
import org.andy.work.appserver.model.ICartHead;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SuppressWarnings("unchecked")
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class ShoppingCartDAO extends GenericDAO implements IShoppingCartDAO {

	@Override
	public ICartHead getAccountCartHead(Integer accountId, boolean loadLazy) {
		String hql = "from CartHead c where c.account.id=:accountId";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("accountId", accountId);
		query.setCacheable(true);
		ICartHead cartHead = (ICartHead) query.uniqueResult();
		if (cartHead != null && loadLazy) {
			Hibernate.initialize(cartHead.getCartDetails());
		}
		return cartHead;
	}

	@Override
	public void deleteCartItemByProdIds(Integer accountId, List<Integer> prodIds) {
		String hql = "select d from CartHead c inner join c.cartDetails d where c.account.id=:accountId and d.product.id in (:prodIds)";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameterList("prodIds", prodIds);
		query.setParameter("accountId", accountId);
		query.setCacheable(true);
		
		List<ICartDetail> cartDetails = query.list();
		if (!cartDetails.isEmpty()) {
			Session session = this.sessionFactory.getCurrentSession();
			for (ICartDetail cartDetail : cartDetails) {
				session.delete(cartDetail);
			}
			session.flush();
		}
	}

}
