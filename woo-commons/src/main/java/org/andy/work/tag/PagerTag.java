package org.andy.work.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.andy.work.paging.PagingHelper;
import org.andy.work.paging.PagingManagement;
import org.apache.log4j.Logger;

public class PagerTag extends TagSupport
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7694509755929823123L;
	private static final Logger log = Logger.getLogger(PagerTag.class);
	//private static final int MAX_PAGE_NUM = 8;
	private PagingManagement pgm;
	private String ajax;
	
	@Override
	public int doStartTag() throws JspException {
		StringBuffer sb = new StringBuffer();
		sb.append("<ul class='pagination'>");
		this.buildPreviewBtn(sb);
		this.buildPager(sb);
		this.buildNextBtn(sb);
		sb.append("<li class='paginate_button previous disabled'><span>共 " + this.pgm.getTotalRecord() + " 条记录， " + this.pgm.getCurrentPageNum() + "/" + this.pgm.getTotalPageNum() + " 页</span></li>");
		if (this.pgm.getTotalPageNum() >= 10) {
			this.buildJump(sb);
		}
		sb.append("</ul>");
		
		try {
			super.pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			log.error(e);
		}
		
		return SKIP_BODY;
	}
	
	private void buildJump(StringBuffer sb) {
		sb.append("<li class='paginate_button'><a><input type='text' id='pageNum' value='"+this.pgm.getCurrentPageNum()+"' style='width:40px; text-align:center; height: 20px;'/></a></li>");
		sb.append("<li class='paginate_button'><a href='javascript:;' class='btnJumpPage'>跳转</a></li>");
		sb.append("<script type='text/javascript'>");
		sb.append("$('.btnJumpPage').on('click', function(){");
		sb.append("var pageNum = $('#pageNum').val(); var maxPage = ").append(this.pgm.getTotalPageNum()).append(";");
		sb.append("if (pageNum <=0) {$.dialog({title: '提示', content: '页码不能小于等于0！' });return;}");
		sb.append("if (pageNum > maxPage) {$.dialog({title: '提示', content: '页码不能大于最大页码！' });return;}");
		sb.append("if (pageNum == ").append(this.pgm.getCurrentPageNum()).append("){$.dialog({title: '提示', content: '不能跳转到当前页码' });return;}");
		sb.append("var url = '").append(this.buildJumpPageClickedLink()).append("' + pageNum;");;
		sb.append("location.href=url;");
		sb.append("})</script>");
	}
	
	private String buildJumpPageClickedLink() {
		//String action = StringUtil.hasValue(this.action) ? this.action : this.pgm.getAction();
		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
		String requestURI = request.getRequestURI();
		requestURI = request.getQueryString() != null ? "?" + request.getQueryString() : "";
		if (requestURI.contains("&pn")) {
			requestURI = requestURI.substring(0, requestURI.indexOf("&pn"));
		}
		if (requestURI.contains("?pn")) {
			requestURI = requestURI.substring(0, requestURI.indexOf("?pn"));
		}
		StringBuffer sb = new StringBuffer(requestURI);
		if (!requestURI.contains("?")) {
			sb.append("?");
		} else {
			sb.append("&");
		}
		sb.append("pn=");
		return sb.toString();
	}

	private void buildPreviewBtn(StringBuffer sb) {
		if (this.pgm.getCurrentPageNum() <= 1) {
			sb.append("<li class='paginate_button previous disabled'><a href='javascript:;'>上一页</a></li>");
		} else {
			int clicked = this.pgm.getCurrentPageNum() - 1;
			if (clicked <= 0) {
				clicked = 1;
			}
			if ("true".equals(ajax)) {
				sb.append("<li class='paginate_button previous'><a id='preview' data-page='" + clicked + "' href='javascript:void(0)'>上一页</a></li>");
			} else {
				sb.append("<li class='paginate_button previous'><a href='" + this.buildPageClickedLink(clicked) + "'>上一页</a></li>");
			}
		}
	}
	
	private void buildPager(StringBuffer sb) {
		int startPageNum = this.pgm.getCurrentPageNum() - 4;
		if (startPageNum <= 0) {
			startPageNum = 1;
		}
		int endPageNum = startPageNum + PagingHelper.MAX_PAGE_NUM;
		if (this.pgm.getTotalPageNum() < endPageNum) {
			endPageNum = this.pgm.getTotalPageNum();
		}
		if (this.pgm.getCurrentPageNum() > (PagingHelper.MAX_PAGE_NUM / 2) + 1) {
			if ("true".equals(this.ajax)) {
				sb.append("<li class='paginate_button'><a class='page' data-page='1' href='javascript:void(0)'>1</a></li><li class='paginate_button active'><a href='javascript:void(0)'>...</a></li>");
			} else {
				sb.append("<li class='paginate_button'><a href='" + this.buildPageClickedLink(1) + "'>1</a></li><li class='paginate_button active'><a href='javascript:void(0)'>...</a></li>");
			}
		}
		for (; startPageNum <= endPageNum; startPageNum++) {
			if (this.pgm.getCurrentPageNum() == startPageNum) {
				sb.append("<li class='paginate_button active'><a href='javascript:void(0)'>");
			} else {
				if ("true".equals(this.ajax)) {
					sb.append("<li class='paginate_button'><a class='page' data-page='" + startPageNum + "' href='javascript:void(0)'>");
				} else {
					sb.append("<li class='paginate_button'><a href='" + this.buildPageClickedLink(startPageNum) + "'>");
				}
			}
			sb.append(startPageNum + "</a></li>");
		}
		if ((this.pgm.getCurrentPageNum() + (PagingHelper.MAX_PAGE_NUM / 2)) < this.pgm.getTotalPageNum() && this.pgm.getTotalPageNum() > PagingHelper.MAX_PAGE_NUM + 1) {
			if ("true".equals(this.ajax)) {
				sb.append("<li class='paginate_button active'><a href='javascript:void(0)'>...</a></li><li class='paginate_button'><a class='page' data-page='" + this.pgm.getTotalPageNum() + "' href='javascript:void(0)'>"+this.pgm.getTotalPageNum()+"</a></li>");
			} else {
				sb.append("<li class='paginate_button active'><a href='javascript:void(0)'>...</a></li><li class='paginate_button'><a href='" + this.buildPageClickedLink(this.pgm.getTotalPageNum()) + "'>"+this.pgm.getTotalPageNum()+"</a></li>");
			}
		}
	}
	
	private String buildPageClickedLink(int pageNum) {
		//String action = StringUtil.hasValue(this.action) ? this.action : this.pgm.getAction();
		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
		String requestURI = request.getRequestURI();
		requestURI = request.getQueryString() != null ? "?" + request.getQueryString() : "";
		if (requestURI.contains("&pn")) {
			requestURI = requestURI.substring(0, requestURI.indexOf("&pn"));
		}
		if (requestURI.contains("?pn")) {
			requestURI = requestURI.substring(0, requestURI.indexOf("?pn"));
		}
		StringBuffer sb = new StringBuffer(requestURI);
		if (!requestURI.contains("?")) {
			sb.append("?");
		} else {
			sb.append("&");
		}
		sb.append("pn=" + pageNum);
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String path = "http://localhost/admin/authority/admin/index.htmls?mainMenuId=48&pn=2";
		System.out.println(path.contains("pn"));
		System.out.println(path.substring(0, path.indexOf("&pn")));
	}
	
	private void buildNextBtn(StringBuffer sb) {
		if (this.pgm.getCurrentPageNum() >= this.pgm.getTotalPageNum()) {
			sb.append("<li class='paginate_button next disabled'><a href='javascript:;'>下一页</a></li>");
		} else {
			int clicked = this.pgm.getCurrentPageNum() + 1;
			if ("true".equals(ajax)) {
				sb.append("<li class='paginate_button next'><a id='nextview' data-page='" + clicked + "' href='javascript:;'>下一页</a></li>");
			} else {
				sb.append("<li class='paginate_button next'><a href='" + this.buildPageClickedLink(clicked) + "'>下一页</a></li>");
			}
		}
		
	}
	
	public void setPgm(PagingManagement pgm) {
		this.pgm = pgm;
	}

	public void setAjax(String ajax) {
		this.ajax = ajax;
	}

}
