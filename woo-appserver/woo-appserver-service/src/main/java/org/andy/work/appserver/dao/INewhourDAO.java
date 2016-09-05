package org.andy.work.appserver.dao;

import org.andy.work.appserver.model.impl.Newhour;


public interface INewhourDAO  extends IGenericDAO {
	int add(Newhour newhour);
}
