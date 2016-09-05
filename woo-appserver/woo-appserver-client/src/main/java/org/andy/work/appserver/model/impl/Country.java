package org.andy.work.appserver.model.impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.andy.work.appserver.model.ICountry;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 国家表
 * @author JIANG
 *
 */
@Entity
@Table(name = "country")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.Country")
public class Country implements Serializable, ICountry {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7252180077603751546L;
	private Integer id;
	private String name;
	private String code;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="country_name", length=30, nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="namecountry_code", length=30)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
