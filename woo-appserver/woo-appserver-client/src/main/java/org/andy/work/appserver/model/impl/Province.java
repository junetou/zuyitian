package org.andy.work.appserver.model.impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.andy.work.appserver.model.ICountry;
import org.andy.work.appserver.model.IProvince;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 省份表
 * @author JIANG
 *
 */
@Entity
@Table(name = "province")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.andy.work.appserver.model.impl.Province")
public class Province implements Serializable, IProvince{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5730444952581562785L;
	private Integer id;
	private String name;
	private ICountry country;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="province_name", length=30, nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne(fetch=FetchType.LAZY, optional=false, targetEntity=Country.class)
	@JoinColumn(name="country_id")
	public ICountry getCountry() {
		return country;
	}
	public void setCountry(ICountry country) {
		this.country = country;
	}
}
