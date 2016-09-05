package org.andy.work.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.andy.work.paging.PagingManagement;
import org.apache.log4j.Logger;

public class WechatPagerTag extends TagSupport
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7694509755929823123L;
	private static final Logger log = Logger.getLogger(WechatPagerTag.class);
	//private static final int MAX_PAGE_NUM = 8;
	private PagingManagement pgm;
	
	@Override
	public int doStartTag() throws JspException {
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='new-pagenav c-flexbox'>");
		this.buildPreviewBtn(sb);
		this.buildPager(sb);
		this.buildNextBtn(sb);
		sb.append("</div>");

		
		try {
			super.pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			log.error(e);
		}
		
		return SKIP_BODY;
	}

	private void buildPreviewBtn(StringBuffer sb) {
		sb.append("<div class='new-pagenav-left'>");
		if (this.pgm.getCurrentPageNum() > 1) {
			sb.append("<a class='new-firstpage' href='").append(this.buildPageClickedLink(1)).append("'><i class='c-icon'>首页</i></a>");
			sb.append("<a class='new-prepage' href='").append(this.buildPageClickedLink(pgm.getCurrentPageNum() - 1)).append("'><i class='c-icon'>上一页</i></a>");
		}
		sb.append("</div>");
	}
	
	private void buildPager(StringBuffer sb) {
		sb.append("<div class='new-pagenav-center'>");
		sb.append("<span class='new-nowpage'>第&nbsp;").append(pgm.getCurrentPageNum()).append("&nbsp;页</span>");
		sb.append("</div>");
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
		sb.append("<div class='new-pagenav-right'>");
		if (this.pgm.getCurrentPageNum() < this.pgm.getTotalPageNum()) {
			sb.append("<a class='new-firstpage' href='").append(this.buildPageClickedLink(this.pgm.getCurrentPageNum()+1)).append("'><i class='c-icon'>下一页</i></a>");
			sb.append("<a class='new-prepage' href='").append(this.buildPageClickedLink(this.pgm.getTotalPageNum())).append("'><i class='c-icon'>尾页</i></a>");
		}
		sb.append("</div>");
		
	}
	
	public void setPgm(PagingManagement pgm) {
		this.pgm = pgm;
	}


}
