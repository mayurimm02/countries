package com.amazing.countries.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Entity(name = "DataInsertionCheck")
@Component
public class DataInsertionCheck {
	@Id
	@Column(name = "Id")
	private Integer id;
	@Column(name = "isDataInserted")
	@NotNull
	private Boolean isDataIntersted;

	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "insertedOnDate")
	private Date insertedOnDate;

	public Boolean getIsDataIntersted() {
		return isDataIntersted;
	}

	public void setIsDataIntersted(Boolean isDataIntersted) {
		this.isDataIntersted = isDataIntersted;
	}

	public Date getInsertedOnDate() {
		return insertedOnDate;
	}

	public void setInsertedOnDate(Date insertedOnDate) {
		this.insertedOnDate = insertedOnDate;
	}
}
