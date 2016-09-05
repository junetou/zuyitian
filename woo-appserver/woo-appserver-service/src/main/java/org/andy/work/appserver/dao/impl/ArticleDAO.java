package org.andy.work.appserver.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.andy.work.appserver.dao.IArticleDAO;
import org.andy.work.appserver.dao.obj.QueryHelper;
import org.andy.work.appserver.model.IArticle;
import org.andy.work.appserver.model.IArticleCategory;
import org.andy.work.criteria.ArticleSearchCriteria;
import org.andy.work.criteria.CategoryCriteria;
import org.andy.work.paging.PagingManagement;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.andy.work.state.CommonState;
import org.andy.work.state.NewsRecordStatus;
import org.andy.work.utils.CommonUtils;
import org.andy.work.utils.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class ArticleDAO extends GenericDAO implements IArticleDAO {

	@Override
	public List<IArticle> getArticleALL() {
		String hql = "from Article a inner join a.articleCategorys tac where a.examineState = :examineState AND a.state = :state order by a.viewNumber desc";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("examineState", NewsRecordStatus.PUBLISHED_EXAMINE);
		query.setParameter("state", NewsRecordStatus.PUBLISHED);
		query.setCacheable(true);
		List<IArticle> list = new ArrayList<IArticle>();
		@SuppressWarnings("unchecked")
		List<Object[]> objs = query.list();
		for (Object[] object : objs) {
			list.add((IArticle) object[0]);
		}
		return list;
	}

	@Override
	public SearchResponse<IArticle> searchArticle(SearchRequest<ArticleSearchCriteria> searchRequest) {
		SearchResponse<IArticle> searchResp = new SearchResponse<IArticle>();
		ArticleSearchCriteria criteria = searchRequest.getCriteria();
		PagingManagement pgm = searchRequest.getPgm();
		QueryHelper queryHelper = QueryHelper.getInstance();
		
		StringBuffer whereHql = this.buildArticleSearchCriteria(criteria, queryHelper);
		
		StringBuffer hql = new StringBuffer("SELECT COUNT(a.id) FROM Article a");
		if (criteria.getCategoryId() != null && criteria.getCategoryId() != 0) {
			hql.append(" inner join a.articleCategorys tac");
		}
		hql.append(whereHql);
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql.toString());
		queryHelper.setToQuery(query, null);
		
		Long count = (Long) query.uniqueResult();
		searchResp.setTotalRecords(count);
		
		if (count > 0) {
			hql = new StringBuffer("FROM Article a");
			if (criteria.getCategoryId() != null && criteria.getCategoryId() != 0) {
				hql.append(" inner join a.articleCategorys tac");
			}
			hql.append(whereHql);
			if (StringUtil.hasValue(criteria.getOrderMode())) {
				hql.append(" ORDER BY a.").append(criteria.getOrderMode()).append(" DESC");
			} else {
				hql.append(" ORDER BY a.updateDate DESC");
			}
			query = session.createQuery(hql.toString());
			queryHelper.setToQuery(query, pgm);
			if (criteria.getCategoryId() != null && criteria.getCategoryId() != 0) {
				List<IArticle> list = new ArrayList<IArticle>();
				@SuppressWarnings("unchecked")
				List<Object[]> objs = query.list();
				for (Object[] object : objs) {
					list.add((IArticle) object[0]);
				}
				searchResp.setResults(list);
			} else {
				@SuppressWarnings("unchecked")
				List<IArticle> list = query.list();
				searchResp.setResults(list);
			}
			
		}
		return searchResp;
	}


	private StringBuffer buildArticleSearchCriteria( ArticleSearchCriteria criteria, QueryHelper queryHelper) {
		StringBuffer buff = new StringBuffer(" where 1=1");
		if (StringUtil.hasValue(criteria.getKeyword())) {
			buff.append(" AND (a.title like :keyword OR a.summary like :keyword");
			buff.append(" OR a.author like :keyword OR a.source like :keyword)");
			queryHelper.addParameter("keyword", "%" + criteria.getKeyword() + "%");
		}
		if (StringUtil.hasValue(criteria.getHotId())) {
			buff.append(" AND a.id <> :articleId and (a.columnImages is null or a.columnImages = '')");
			queryHelper.addParameter("articleId", Integer.valueOf(criteria.getHotId()));
			
		}
		if (StringUtil.hasValue(criteria.getApplyState())) {
			buff.append(" AND a.examineState = :examineState");
			queryHelper.addParameter("examineState", criteria.getApplyState());
		}
		if (StringUtil.hasValue(criteria.getState())) {
			buff.append(" AND a.state = :state");
			queryHelper.addParameter("state", criteria.getState());
		}
		if (criteria.getCategoryId() != null && criteria.getCategoryId() != 0) {
			buff.append(" AND (tac.id = :categoryId");
			queryHelper.addParameter("categoryId", criteria.getCategoryId());
			this.getCategory(criteria.getCategoryId(), buff);
			buff.append(")");
			
		}
		if (StringUtil.hasValue(criteria.getDateStart())) {
			Date dateStart = CommonUtils.parseFromDate(criteria.getDateStart());
			if (dateStart != null) {
				buff.append(" and a.createDate > :dateStart");
				queryHelper.addParameter("dateStart", dateStart);
			}
		}
		if (StringUtil.hasValue(criteria.getDateEnd())) {
			Date dateEnd = CommonUtils.parseToDate(criteria.getDateEnd());
			if (dateEnd != null) {
				buff.append(" and a.createDate < :dateEnd");
				queryHelper.addParameter("dateEnd", dateEnd);
			}
		}
		return buff;
	}
	
	@Override
	public List<IArticle> findWinFiveYearsArticleImages(Integer categoryId) {
		StringBuffer hql =  new StringBuffer("from Article a inner join a.articleCategorys tac where a.examineState = :examineState AND a.state = :state  AND (tac.id = :categoryId");
		this.getCategory(categoryId, hql);
		hql.append(") order by a.viewNumber desc");
		
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql.toString());
		query.setParameter("examineState", NewsRecordStatus.PUBLISHED_EXAMINE);
		query.setParameter("state", NewsRecordStatus.PUBLISHED);
		query.setParameter("categoryId", categoryId);
		query.setCacheable(true);
		query.setMaxResults(4);
		List<IArticle> list = new ArrayList<IArticle>();
		@SuppressWarnings("unchecked")
		List<Object[]> objs = query.list();
		for (Object[] object : objs) {
			list.add((IArticle) object[0]);
		}
		return list;
	}
	
	private void getCategory(Integer articleCategoryId, StringBuffer buff) {
		List<IArticleCategory> categorys = this.getArticleCategoryByParayId(articleCategoryId);
		for (IArticleCategory category : categorys) {
			buff.append(" or tac.id = ").append(category.getId());
			this.getCategory(category.getId(), buff);
		}
	}

	@SuppressWarnings("unchecked")
	public List<IArticleCategory> getArticleCategoryByParayId(Integer categoryId) {
		String hql = "from ArticleCategory a where a.parent.id = :categoryId";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("categoryId", categoryId);
		query.setCacheable(true);
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<IArticleCategory> getCategoryList() {
		String queryString = "FROM ArticleCategory category";
		Query query = this.sessionFactory.getCurrentSession().createQuery(queryString);
		query.setCacheable(true);
		return query.list();
	}

	@Override
	public List<IArticleCategory> getCategoryListById(Integer id) {
		String queryString = "FROM ArticleCategory category WHERE category.parent.id = " + id;
		@SuppressWarnings("unchecked")
		List<IArticleCategory> categories = this.sessionFactory.getCurrentSession().createQuery(queryString).list();
		return categories;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<IArticleCategory> getImmunityCagetoryById(Integer categoryId) {
		String queryString = "FROM ArticleCategory category WHERE category.parent.id = :categoryId and category.state = :state";
		Query query = this.sessionFactory.getCurrentSession().createQuery(queryString);
		query.setParameter("categoryId", categoryId);
		query.setParameter("state", CommonState.ACTIVE);
		query.setCacheable(true);
		return query.list();
	}

	@Override
	public List<IArticle> getArticleListByCategoryId(Integer id) {
		String queryString = "FROM Article a inner join a.articleCategorys tac WHERE tac.id = " + id;
		Query query = this.sessionFactory.getCurrentSession().createQuery(queryString);
		query.setCacheable(true);
		List<IArticle> list = new ArrayList<IArticle>();
		@SuppressWarnings("unchecked")
		List<Object[]> objs = query.list();
		for (Object[] object : objs) {
			list.add((IArticle) object[0]);
		}
		return list;
	}

	@Override
	public SearchResponse<IArticleCategory> searchCategory(SearchRequest<CategoryCriteria> searchRequest) {
		SearchResponse<IArticleCategory> searchResp = new SearchResponse<IArticleCategory>();
		CategoryCriteria criteria = searchRequest.getCriteria();
		PagingManagement pgm = searchRequest.getPgm();
		QueryHelper queryHelper = QueryHelper.getInstance();
		StringBuffer whereHql = this.buildMultiMediaCategory(criteria, queryHelper);
		
		StringBuffer hql = new StringBuffer("SELECT COUNT(c.id) FROM ArticleCategory c");
		hql.append(whereHql);
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql.toString());
		queryHelper.setToQuery(query, null);
		
		Long count = (Long) query.uniqueResult();
		searchResp.setTotalRecords(count);
		
		if (count > 0) {
			hql = new StringBuffer("FROM ArticleCategory c");
			hql.append(whereHql);
			hql.append(" ORDER BY c.sort ASC");
			query = session.createQuery(hql.toString());
			queryHelper.setToQuery(query, pgm);
			
			@SuppressWarnings("unchecked")
			List<IArticleCategory> list = query.list();
			searchResp.setResults(list);
		}
		return searchResp;
	}

	private StringBuffer buildMultiMediaCategory(CategoryCriteria criteria, QueryHelper queryHelper) {
		StringBuffer whereHql = new StringBuffer(" where 1=1");
		if (StringUtil.hasValue(criteria.getKeyword())) {
			whereHql.append(" AND c.name like :keyword OR c.description like :keyword");
			queryHelper.addParameter("keyword", "%" + criteria.getKeyword() + "%");
		}
		if (criteria.getId() != null && criteria.getId() != 0) {
			whereHql.append(" and c.parent.id = :parentId");
			queryHelper.addParameter("parentId", criteria.getId());
		} else {
			whereHql.append(" and c.parent.id is null");
		}
		return whereHql;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IArticleCategory> getCategoryListByState() {
		String queryString = "FROM ArticleCategory ac where ac.state = :state order by ac.sort asc";
		Query query = this.sessionFactory.getCurrentSession().createQuery(queryString);
		query.setParameter("state", CommonState.ACTIVE);
		query.setCacheable(true);
		return query.list();
	}
}
