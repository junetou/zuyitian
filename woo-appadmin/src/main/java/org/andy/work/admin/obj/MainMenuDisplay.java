package org.andy.work.admin.obj;

import java.io.Serializable;
import java.util.LinkedList;

public class MainMenuDisplay implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1476690566765144217L;
	private Integer type;
	private String name;
	private String classIcon;
	private LinkedList<MenuNode> nodes;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassIcon() {
		return classIcon;
	}
	public void setClassIcon(String classIcon) {
		this.classIcon = classIcon;
	}
	public LinkedList<MenuNode> getNodes() {
		return nodes;
	}
	public void setNodes(LinkedList<MenuNode> nodes) {
		this.nodes = nodes;
	}
}

