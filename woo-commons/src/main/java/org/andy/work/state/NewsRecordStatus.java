package org.andy.work.state;

public class NewsRecordStatus {
	
	//set state
	public final static String PENDING = "10";//等待发布
	public final static String PUBLISHED = "20";//已发布
	public final static String DELETED = "30";//已删除
	
	//set examine state
	public final static String PENDING_EXAMINE = "10";//等待审核
	public final static String PUBLISHED_EXAMINE = "20";//已审核
	
	//set hotspot type 
	public final static String HOTSPOT = "10";
	public final static String NOTHOTSPOT = "20";
	
}
