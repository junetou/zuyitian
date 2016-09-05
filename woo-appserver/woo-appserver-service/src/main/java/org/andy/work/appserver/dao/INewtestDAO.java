package org.andy.work.appserver.dao;

import org.andy.work.appserver.model.INewtest;

public interface INewtestDAO extends IGenericDAO{
	INewtest test(int id);
	int count();
}
