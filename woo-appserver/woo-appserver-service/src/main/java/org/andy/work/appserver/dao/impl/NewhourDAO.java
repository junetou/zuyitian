package org.andy.work.appserver.dao.impl;

import org.andy.work.appserver.dao.INewhourDAO;
import org.andy.work.appserver.model.impl.Newhour;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)

public class NewhourDAO extends GenericDAO  implements INewhourDAO{

	@Override
	public int add(Newhour newhour) {
		sessionFactory.getCurrentSession().save(newhour);
		return 1;
	}
	


}
