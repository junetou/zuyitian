package org.andy.work.appserver.service.impl;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.ITestDAO;
import org.andy.work.appserver.model.ITest;
import org.andy.work.appserver.service.ITestMain;
import org.springframework.stereotype.Service;


@Service
public class TestMain implements ITestMain {
	
	@Resource
	private ITestDAO testDao;

	@Override
	public ITest test(int id) {
		ITest iTest = this.testDao.test(id);
		return iTest;
	}

}
