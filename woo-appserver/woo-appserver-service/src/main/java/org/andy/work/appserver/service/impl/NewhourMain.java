package org.andy.work.appserver.service.impl;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.INewhourDAO;

import org.andy.work.appserver.model.impl.Newhour;

import org.andy.work.appserver.service.INewhourMain;

import org.springframework.stereotype.Service;



@Service
public class NewhourMain implements INewhourMain{
	@Resource
	private INewhourDAO newhourDAO;
	
	@Override
	public int add(Newhour newhour) {
		newhourDAO.add(newhour);
		return 1;
	}


}
