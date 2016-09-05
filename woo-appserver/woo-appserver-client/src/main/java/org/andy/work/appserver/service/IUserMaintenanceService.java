package org.andy.work.appserver.service;

import java.util.List;

import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.IUserGroup;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;

public interface IUserMaintenanceService {
	
	IUser findUserByUsername(String username);
	
	IUser saveUser(IUser user);
	
	IUser createUser();
	
	IUser getUserById(Integer uid);
	
	void removeUser(Integer uid);
	
	IUserGroup createUserGroup();
	
	IUserGroup saveUserGroup(IUserGroup userGroup);
	
	IUserGroup getUserGroupById(Integer id);
	
	void removeUserGroup(Integer id);
	
	List<IUserGroup> getUserGroup();
	
	IUserGroup getUserGroupByName(String name);
	
	List<String> getGroupUsers(Integer groupId);

	SearchResponse<IUserGroup> searchSearchResponse(SearchRequest<String> searchReq);

	SearchResponse<IUser> searchUser(SearchRequest<AcctUserSearchCriteria> searchReq);

	boolean isAccount(String account);

	boolean isUserGroup(Integer id);
	
	boolean hasusrname(String usrname);
	
}
