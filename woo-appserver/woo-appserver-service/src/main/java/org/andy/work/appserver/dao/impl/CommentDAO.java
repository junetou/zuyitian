package org.andy.work.appserver.dao.impl;

import java.util.List;

import org.andy.work.appserver.dao.ICommentDAO;
import org.andy.work.appserver.dao.obj.QueryHelper;
import org.andy.work.appserver.model.IComment;
import org.andy.work.appserver.model.IDetailmessage;
import org.andy.work.appserver.model.impl.Comment;
import org.andy.work.criteria.AcctUserSearchCriteria;
import org.andy.work.paging.PagingManagement;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.andy.work.utils.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class CommentDAO extends GenericDAO implements ICommentDAO {
	
	@Override
	public IComment getcomment(Integer thingsid){
		String hql="from Comment t where t.id=:id";
		int id=thingsid.intValue();
		Query query=super.sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", id);
		return (IComment)query.uniqueResult();
	}
	
	@Override
	public String addcomment(Comment comment){
		sessionFactory.getCurrentSession().save(comment);
		return "success";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SearchResponse<IComment> searchUsers(SearchRequest<AcctUserSearchCriteria> searchReq){
		SearchResponse<IComment> searchResp = new SearchResponse<IComment>();
		QueryHelper queryHelper = QueryHelper.getInstance();
		AcctUserSearchCriteria criteria = searchReq.getCriteria();
		PagingManagement pgm = searchReq.getPgm();
		StringBuffer hql = new StringBuffer("select count(u.commentid) from Comment u ");	
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql.toString());
		queryHelper.setToQuery(query, null);
		Long count=(Long) query.uniqueResult();
		searchResp.setTotalRecords(count);

		if(count>0){ 
			if(StringUtil.hasValue(criteria.getKeyWord())){
		    String keyword=criteria.getKeyWord();
		    Integer number=Integer.valueOf(keyword);
			hql=new StringBuffer("from Comment t where t.everythingnumber = :everythingnumber ");
			queryHelper.addParameter("everythingnumber",number);
			}
			query = session.createQuery(hql.toString());
			queryHelper.setToQuery(query, pgm);
			List<IComment> list=query.list();
			searchResp.setResults(list);
        }
		return searchResp;
	}
	
}
