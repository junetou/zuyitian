package org.andy.work.appserver.service.impl;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.IProductDAO;
import org.andy.work.appserver.model.IProduct;
import org.andy.work.appserver.model.impl.Product;
import org.andy.work.appserver.service.IProductMaintenanceService;
import org.springframework.stereotype.Service;

@Service
public class ProductMaintenanceService implements IProductMaintenanceService {
	
	@Resource
	private IProductDAO productDAO;

	@Override
	public IProduct getProductById(Integer prodId) {
		return (IProduct) this.productDAO.getEntityById(Product.class, prodId);
	}
	
}
