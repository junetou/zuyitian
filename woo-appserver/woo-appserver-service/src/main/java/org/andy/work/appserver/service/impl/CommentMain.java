package org.andy.work.appserver.service.impl;

import javax.annotation.Resource;

import org.andy.work.appserver.dao.ICommentDAO;
import org.andy.work.appserver.model.IComment;
import org.andy.work.appserver.model.impl.Comment;
import org.andy.work.appserver.service.ICommentMain;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.springframework.stereotype.Service;


@Service
public class CommentMain implements ICommentMain{

	@Resource
	private ICommentDAO commentmain;
	
	//查看评论
	@Override
    public IComment getcomment(Integer thingsid){
		
		IComment comment=this.commentmain.getcomment(thingsid);
		return comment;
	}
	
	//增加评论
	@Override
	public String addcomment(Comment comment){
		commentmain.addcomment(comment);
		return "success";
	}

	@Override
	public SearchResponse<IComment> searchUsers(SearchRequest<AcctUserSearchCriteria> searchReq){
		return this.commentmain.searchUsers(searchReq);
	}
	
	
}
