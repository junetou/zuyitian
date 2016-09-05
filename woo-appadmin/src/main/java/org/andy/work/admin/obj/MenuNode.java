package org.andy.work.admin.obj;

public class MenuNode {
	
	private String id;
	private String name;
	private Integer level;
	private String parent;
	private Boolean isLeaf;
	private Boolean expanded;
	private String likeUrl;
	private String classIcon;
	
	public MenuNode(String id, String name, Integer level, String parent, Boolean isLeaf, Boolean expanded, String likeUrl, String classIcon) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.parent = parent;
		this.isLeaf = isLeaf;
		this.expanded = expanded;
		this.likeUrl = likeUrl;
		this.classIcon = classIcon;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public String getLikeUrl() {
		return likeUrl;
	}

	public void setLikeUrl(String likeUrl) {
		this.likeUrl = likeUrl;
	}

	public String getClassIcon() {
		return classIcon;
	}

	public void setClassIcon(String classIcon) {
		this.classIcon = classIcon;
	}

	@Override
	public String toString() {
		return "MenuNode [id=" + id + ", name=" + name + ", level=" + level
				+ ", parent=" + parent + ", isLeaf=" + isLeaf + ", expanded="
				+ expanded + ", likeUrl=" + likeUrl + ", classIcon="
				+ classIcon + "]";
	}
	
}
