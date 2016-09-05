package org.andy.work.appserver.dao;

import org.andy.work.appserver.model.IComment;
import org.andy.work.appserver.model.impl.Comment;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;

public interface ICommentDAO  extends IGenericDAO {
	
	IComment getcomment(Integer thingsid);//获取评论
	
	String addcomment(Comment comment);//增加评论

	
	SearchResponse<IComment> searchUsers(SearchRequest<AcctUserSearchCriteria> searchReq);//获取全部评论
}
