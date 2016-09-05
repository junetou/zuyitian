package org.andy.work.criteria;

import java.io.Serializable;

public class AcctUserSearchCriteria implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3761475495003906908L;
	private String id;
	private String title;
	private String subject;
	private String description;
	private Integer comments;
	private String author;
	private String keyWork;
	
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
	public void setKeyWork(String keywork){
		this.keyWork=keywork;
	}
	public String getKeyWord(){
		return this.keyWork;
	}
	
}
