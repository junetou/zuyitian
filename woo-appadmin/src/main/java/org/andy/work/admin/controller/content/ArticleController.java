package org.andy.work.admin.controller.content;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.andy.work.admin.auth.AuthOperation;
import org.andy.work.admin.controller.content.detail.ArticleCategoryDetail;
import org.andy.work.admin.controller.content.detail.ArticleDetail;
import org.andy.work.admin.controller.content.form.ArticleCategoryForm;
import org.andy.work.admin.helper.UserSessionHelper;
import org.andy.work.admin.log.LogsType;
import org.andy.work.admin.log.SystemLog;
import org.andy.work.admin.permission.AuthOperationConfiguration;
import org.andy.work.admin.permission.RoleType;
import org.andy.work.appserver.model.IArticle;
import org.andy.work.appserver.model.IArticleCategory;
import org.andy.work.appserver.model.impl.ArticleCategory;
import org.andy.work.appserver.service.IArticleMaintenanceService;
import org.andy.work.appserver.service.ICommonMaintenanceService;
import org.andy.work.criteria.ArticleSearchCriteria;
import org.andy.work.criteria.CategoryCriteria;
import org.andy.work.paging.GridData;
import org.andy.work.paging.PagingHelper;
import org.andy.work.paging.PagingManagement;
import org.andy.work.paging.SearchRequest;
import org.andy.work.paging.SearchResponse;
import org.andy.work.utils.AjaxResponse;
import org.andy.work.utils.CommonUtils;
import org.andy.work.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/portal/web/article")
public class ArticleController {
	
	@Resource IArticleMaintenanceService articleService;
	
	@Resource ICommonMaintenanceService commanSrvice;
	
	@Resource UserSessionHelper sessionHelper;
	
	/**
	 * 这里是文章分页列表的方法
	 * @param model 这个是用来传到页面的保存数据到request
	 * @param request 这是你们知道的
	 * @param search 这个是用来做搜索条件的对象
	 * @return
	 */
	@RequestMapping
	@AuthOperation(roleType=RoleType.WEB, operationType=AuthOperationConfiguration.ARTICLE_VIEW)
	public ModelAndView articleList(ModelAndView model, HttpServletRequest request, ArticleSearchCriteria search) {
		//数据List传到页面显示的
		GridData<ArticleDetail> grid = new GridData<ArticleDetail>();
		//分页管理器
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		//从数据库取到数据
		SearchResponse<IArticle> searchResp = this.articleService.searchArticle(new SearchRequest<ArticleSearchCriteria>(search, pgm));
		//判读list数据记录数必须大于0
		if (searchResp.getTotalRecords() > 0) {
			List<ArticleDetail> details = new ArrayList<ArticleDetail>();
			List<IArticle> articles = searchResp.getResults();
			for (int i = 0; i < articles.size(); i++) {
				IArticle article = articles.get(i);
				ArticleDetail detail = this.toArticleDetail(article);
				details.add(detail);
			}
			grid.setDatas(details);
		}
		pgm.setTotalRecord(searchResp.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		
		List<IArticleCategory> categories = this.articleService.getCategoryFormatList();
		
		List<ArticleCategoryDetail> categoryDetails = new ArrayList<ArticleCategoryDetail>();
		for (IArticleCategory category : categories) {
			ArticleCategoryDetail categoryDetail = this.toCategoryDetail(category);
			categoryDetails.add(categoryDetail);
		}
		//将数据添加到ModelAndView这个对象中，设置跳转页面 - tiles/article-view
		model.addObject("grid", grid).addObject("categoryDisplays", categoryDetails).setViewName("tiles/article-view");
		return model;
	}
	
	@RequestMapping(value = "category")
	@AuthOperation(roleType=RoleType.WEB, operationType=AuthOperationConfiguration.ARTICLE_CATEGORY_VIEW)
	public ModelAndView search(ModelAndView model, HttpServletRequest request, CategoryCriteria search) {
		GridData<ArticleCategoryDetail> grid = new GridData<ArticleCategoryDetail>();
		PagingManagement pgm = PagingHelper.buildPagingManagement(request);
		
		SearchResponse<IArticleCategory> searchResp = this.articleService.searchCategory(new SearchRequest<CategoryCriteria>(search, pgm));
		
		if (searchResp.getTotalRecords() > 0) {
			List<ArticleCategoryDetail> details = new ArrayList<ArticleCategoryDetail>();
			List<IArticleCategory> categories = searchResp.getResults();
			for (int i = 0; i < categories.size(); i++) {
				IArticleCategory category = categories.get(i);
				ArticleCategoryDetail detail = this.toCategoryDetail(category);
				details.add(detail);
			}
			grid.setDatas(details);
		}
		pgm.setTotalRecord(searchResp.getTotalRecords());
		PagingHelper.setPaging(pgm, grid);
		
		model.addObject("grid", grid).setViewName("tiles/article-category-view");
		return model;
	}
	
	@RequestMapping(value = "category/form/{id}")
	@AuthOperation(roleType=RoleType.WEB, operationType=AuthOperationConfiguration.ARTICLE_CATEGORY_VIEW)
	public ModelAndView addPage(ModelAndView model, @PathVariable("id") Integer id, @RequestParam(value="paranId", required=false) String paranId) {
		List<IArticleCategory> categories = this.articleService.getCategoryFormatList();
		List<ArticleCategoryDetail> displays = new ArrayList<ArticleCategoryDetail>();

		ArticleCategoryDetail def = new ArticleCategoryDetail();
		def.setName("无父级分类");
		displays.add(def);
		
		ArticleCategoryForm form;
		if(id == 0) {
			form = new ArticleCategoryForm();
			if (StringUtil.hasValue(paranId)) {
				Integer paranIds = Integer.valueOf(paranId);
				IArticleCategory paran = this.articleService.getCategoryById(paranIds);
				if (paran != null) {
					form.setParentId(paran.getId());
					form.setParentName(paran.getName());
				}
			}
		} else {
			IArticleCategory category = this.articleService.getCategoryById(id);
			form = this.toCategoryForm(category);
		}
		
		for (IArticleCategory category : categories) {
			ArticleCategoryDetail display = this.toCategoryDetail(category);
			displays.add(display);
		}
		model.addObject("categories", displays).addObject("command", form);
		model.setViewName("tiles/includes/article-category-form");
		return model;
	}
	
	@ResponseBody
	@RequestMapping("category/saveOrUpdate")
	@SystemLog(description="增加或修改文章分类", type=LogsType.UPDATE)
	@AuthOperation(roleType=RoleType.WEB, operationType=AuthOperationConfiguration.ARTICLE_CATEGORY_ADD)
	public AjaxResponse saveOrUpdate(ArticleCategoryForm form) {
		IArticleCategory category = null;
		try {
			if(form.getId() != null && form.getId() != 0) {
				category = this.articleService.getCategoryById(form.getId());
			} else {
				category = new ArticleCategory();
			}
			category.setName(form.getName());
			category.setDescription(form.getDescription());
			category.setSort(form.getSort());
			category.setLikeUrl(form.getLikeUrl());
			category.setIconUrl(form.getIconUrl());
			category.setState(form.getState());
			if(form.getParentId() != null && form.getParentId() != 0) {
				IArticleCategory parent = this.articleService.getCategoryById(form.getParentId());
				category.setParent(parent);
			}
			this.articleService.saveOrUpdateCategory(category);
		} catch (Exception e) {
			return AjaxResponse.fail("未知异常!");
		}
		return AjaxResponse.success();
	}
	
	@ResponseBody
	@RequestMapping("category/delete")
	@SystemLog(description="删除文章分类", type=LogsType.DELETE)
	@AuthOperation(roleType=RoleType.WEB, operationType=AuthOperationConfiguration.ARTICLE_CATEGORY_DELETE)
	public AjaxResponse deleteCategory(@RequestParam Integer id) {
		try {
			boolean canDelete = this.articleService.deleteCategory(id);
			if(!canDelete) {
				return AjaxResponse.fail("请先删除其他关联数据!");
			}
		} catch (Exception e) {
			return AjaxResponse.fail("未知异常!");
		}
		return new AjaxResponse("1", "删除成功！");
	}
	
	private ArticleDetail toArticleDetail(IArticle article) {
		ArticleDetail detail = new ArticleDetail();
		detail.setSummaryImage(this.getImage300Url(article.getImages()));
		detail.setId(article.getId());
		detail.setTitle(article.getTitle());
		detail.setSummary(article.getSummary());
		detail.setImages(article.getImages());
		detail.setColumnImages(article.getColumnImages());
		detail.setAuthor(article.getAuthor());
		detail.setSource(article.getSource());
		detail.setViewNumber(article.getViewNumber());
		detail.setContent(article.getContent());
		detail.setState(article.getState());
		detail.setExamineState(article.getExamineState());
		detail.setPageTitle(article.getPageTitle());
		detail.setHotspot(article.getHotspot());
		detail.setPageKeyword(article.getPageKeyword());
		detail.setPageDescription(article.getPageDescription());
		Set<IArticleCategory> categories = article.getArticleCategorys();
		if(categories.size() > 0) {
			String categoryNames = "";
			for (IArticleCategory category : categories) {
				categoryNames += category.getName() + ", ";
			}
			detail.setCategoryNames(categoryNames.substring(0, categoryNames.length() - 2));
		}
		detail.setCreateDate(CommonUtils.dateFormat(article.getCreateDate()));
		detail.setCreateBy(article.getCreateBy());
		detail.setUpdateDate(CommonUtils.dateFormat(article.getUpdateDate()));
		detail.setUpdateBy(article.getUpdateBy());
		return detail;
	}
	
	private ArticleCategoryDetail toCategoryDetail(IArticleCategory category) {
		ArticleCategoryDetail detail = new ArticleCategoryDetail();
		detail.setId(category.getId());
		detail.setName(category.getName());
		detail.setDescription(category.getDescription());
		detail.setSort(category.getSort());
		detail.setState(category.getState());
		IArticleCategory parent = category.getParent();
		if(parent != null) {
			detail.setParentName(parent.getName());
		}
		return detail;
	}
	
	private String getImage300Url(String url) {
		String newUrl = url.substring(0, url.lastIndexOf("/") + 1) + "small_300_" + url.substring(url.lastIndexOf("/")+1, url.length());
		return newUrl;
	}
	
	private ArticleCategoryForm toCategoryForm(IArticleCategory category) {
		ArticleCategoryForm form = new ArticleCategoryForm();
		form.setId(category.getId());
		form.setName(category.getName());
		form.setLikeUrl(category.getLikeUrl());
		form.setDescription(category.getDescription());
		form.setIconUrl(category.getIconUrl());
		form.setState(category.getState());
		IArticleCategory parent = category.getParent();
		if(parent != null) {
			form.setParentId(parent.getId());
			form.setParentName(parent.getName());
		}
		form.setSort(category.getSort());
		return form;
	}
	
}
