package org.andy.work.suggest;

import java.io.Serializable;

public class SuggestionDataSource implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1714157762988734924L;
	private Integer value;
	private String label;
	private String alias;
	
	public SuggestionDataSource()
	{
		super();
	}
	
	/**
	 * @param value
	 * @param alias
	 */
	public SuggestionDataSource(Integer value, String label)
	{
		super();
		this.value = value;
		this.label = label;
	}

	/**
	 * @param value
	 * @param label
	 * @param alias
	 */
	public SuggestionDataSource(Integer value, String label, String alias)
	{
		super();
		this.value = value;
		this.label = label + "【" + alias + "】";
		this.alias = alias;
	}

	public Integer getValue()
	{
		return value;
	}
	public void setValue(Integer value)
	{
		this.value = value;
	}
	public String getLabel()
	{
		return label;
	}
	public void setLabel(String label)
	{
		this.label = label;
	}
	public String getAlias()
	{
		return alias;
	}
	public void setAlias(String alias)
	{
		this.alias = alias;
	}
	
}
