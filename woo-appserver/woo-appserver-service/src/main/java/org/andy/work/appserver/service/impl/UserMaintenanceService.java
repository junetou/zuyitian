package org.andy.work.appserver.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.IUserDAO;
import org.andy.work.appserver.model.IUser;
import org.andy.work.appserver.model.IUserGroup;
import org.andy.work.appserver.model.impl.User;
import org.andy.work.appserver.model.impl.UserGroup;
import org.andy.work.appserver.service.IUserMaintenanceService;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserMaintenanceService implements IUserMaintenanceService {
	
	@Resource
	private IUserDAO userDAO;
	
	@Override
	public IUser findUserByUsername(String username) {
		return this.userDAO.finUserByusername(username);
	}
	
	@Override
	public IUser saveUser(IUser user) {
		this.userDAO.makeSaveOrUpdate(user);
		return user;
	}
	
	@Override
	public IUser createUser() {
		IUser user = new User();
		user.setCreatedDate(new Date());
		return user;
	}
	
	@Override
	public IUser getUserById(Integer uid) {
		IUser user = (IUser) this.userDAO.getEntityById(User.class, uid);
		return user;
	}

	@Override
	public void removeUser(Integer uid) {
		IUser user = this.getUserById(uid);
		if (user != null) {
			this.userDAO.deleteEntity(user);
		}
	}
	
	@Override
	public IUserGroup createUserGroup(){
		IUserGroup group = new UserGroup();
		return group;
	}
	
	@Override
	@Cacheable(value="QY_api_productTop") 
	public List<IUserGroup> getUserGroup() {
		return this.userDAO.getUserGroup();
	}

	
	@Override
	public IUserGroup getUserGroupById(Integer id) {
		return (IUserGroup) this.userDAO.getEntityById(UserGroup.class, id);
	}
	
	@Override
	public void removeUserGroup(Integer id) {
		IUserGroup userGroup = this.getUserGroupById(id);
		if (userGroup != null) {
			this.userDAO.deleteEntity(userGroup);
		}
	}
	
	@Override
	public IUserGroup saveUserGroup(IUserGroup userGroup) {
		this.userDAO.makeSaveOrUpdate(userGroup);
		return userGroup;
	}
	
	@Override
	public IUserGroup getUserGroupByName(String name) {
		return this.userDAO.getUserGroupByName(name);
	}
	
	@Override
	public List<String> getGroupUsers(Integer groupId) {
		return this.userDAO.getGroupUsers(groupId);
	}

	@Override
	public SearchResponse<IUserGroup> searchSearchResponse(SearchRequest<String> searchReq) {
		return this.userDAO.searchSearchResponse(searchReq);
	}

	@Override
	public SearchResponse<IUser> searchUser(SearchRequest<AcctUserSearchCriteria> searchReq) {
		return this.userDAO.searchUsers(searchReq);
	}

	@Override
	public boolean isAccount(String account) {
		return this.userDAO.isAccount(account);
	}
	
	@Override
	public boolean isUserGroup(Integer id) {
		return this.userDAO.isUserGroup(id);
	}
	
	@Override
	public boolean hasusrname(String usrname){
		return this.userDAO.hasusrname(usrname);
	}
	
}