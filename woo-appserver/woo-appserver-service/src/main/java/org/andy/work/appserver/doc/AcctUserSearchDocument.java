package org.andy.work.appserver.doc;

import org.apache.solr.client.solrj.beans.Field;

public class AcctUserSearchDocument implements IDocument {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5306554931893304760L;
	@Field
	private String id;
	@Field
	private String title;
	@Field
	private String subject;
	@Field
	private String description;
	@Field
	private Integer comments;
	@Field
	private String author;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getComments() {
		return comments;
	}
	public void setComments(Integer comments) {
		this.comments = comments;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
}
