package org.andy.work.appserver.service;

import org.andy.work.appserver.model.IComment;
import org.andy.work.appserver.model.impl.Comment;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;

public interface ICommentMain {
    
    IComment getcomment(Integer thingsid);
	String addcomment(Comment comment);
	SearchResponse<IComment> searchUsers(SearchRequest<AcctUserSearchCriteria> searchReq);
	
}
