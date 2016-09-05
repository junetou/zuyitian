package org.andy.work.appserver.service.impl;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.INewtestDAO;
import org.andy.work.appserver.model.INewtest;
import org.andy.work.appserver.service.INewtestMain;
import org.springframework.stereotype.Service;



@Service
public class NewtestMain implements INewtestMain {

	@Resource
	private INewtestDAO testDao;
	
	@Override
	public INewtest test(int id) {
		INewtest Test = this.testDao.test(id);
		return Test;
	}

	@Override
	public int count(){
		
		int count=this.testDao.count();
		return count;
	}
	
	
}
