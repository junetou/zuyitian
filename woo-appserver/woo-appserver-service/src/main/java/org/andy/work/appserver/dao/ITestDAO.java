package org.andy.work.appserver.dao;

import org.andy.work.appserver.model.ITest;

public interface ITestDAO extends IGenericDAO{
	
	ITest test(int id);

}
