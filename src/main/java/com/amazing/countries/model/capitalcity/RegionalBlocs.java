package com.amazing.countries.model.capitalcity;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "RegionalBlocs")
@JsonIgnoreProperties(value = "ignoreUnknown")
public class RegionalBlocs {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RegionalBlocs_id")
	private Integer regionalBlocsid;

	@Column
	private String acronym;
	@Column
	private String name;
	@Column
	private ArrayList<String> otherAcronyms;
	@Column
	private ArrayList<String> otherNames;

	@Column(name = "capital_id")
	private Integer capital_id;

	public Integer getCapital_id() {
		return capital_id;
	}

	public void setCapital_id(Integer capital_id) {
		this.capital_id = capital_id;
	}

	public Integer getRegionalBlocsid() {
		return regionalBlocsid;
	}

	public void setRegionalBlocsid(Integer regionalBlocsid) {
		this.regionalBlocsid = regionalBlocsid;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getOtherAcronyms() {
		return otherAcronyms;
	}

	public void setOtherAcronyms(ArrayList<String> otherAcronyms) {
		this.otherAcronyms = otherAcronyms;
	}

	public ArrayList<String> getOtherNames() {
		return otherNames;
	}

	public void setOtherNames(ArrayList<String> otherNames) {
		this.otherNames = otherNames;
	}

	@Override
	public String toString() {
		return "CapitalCityRegionalBlocs [acronym=" + acronym + ", name=" + name + ", otherAcronyms=" + otherAcronyms
				+ ", otherNames=" + otherNames + "]";
	}

}
