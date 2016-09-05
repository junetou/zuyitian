package org.andy.work.appserver.service.impl;

import javax.annotation.Resource;





import org.andy.work.appserver.dao.ITaogeDAO;
import org.andy.work.appserver.dao.ITestDAO;
import org.andy.work.appserver.model.impl.Taoge;
import org.andy.work.appserver.service.ITaogeMain;
import org.springframework.stereotype.Service;

@Service
public class TaogeMain implements ITaogeMain{

	@Resource
	private ITaogeDAO taogeDAO;
	
	@Override
	public int add(Taoge taoge) {
		taogeDAO.add(taoge);
		return 1;
	}

}
