package com.amazing.countries.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Entity(name = "DataInsertionCheck")
@Component
public class DataInsertionCheck implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1636171295729542492L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "isDataInserted")
	@NotNull
	private String isDataIntersted;

	@Column(name = "isDataAlreadyPresent")
	private String isDataAlreadyPresent;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsDataAlreadyPresent() {
		return isDataAlreadyPresent;
	}

	public void setIsDataAlreadyPresent(String isDataAlreadyPresent) {
		this.isDataAlreadyPresent = isDataAlreadyPresent;
	}

	@Column(name = "insertedOnDate")
	private Date insertedOnDate;

	public String getIsDataIntersted() {
		return isDataIntersted;
	}

	public void setIsDataIntersted(String isDataIntersted) {
		this.isDataIntersted = isDataIntersted;
	}

	public Date getInsertedOnDate() {
		return insertedOnDate;
	}

	public void setInsertedOnDate(Date insertedOnDate) {
		this.insertedOnDate = insertedOnDate;
	}
}
