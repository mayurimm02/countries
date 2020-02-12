package com.amazing.countries.model.country;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Languages")
public class Languages implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2188388529555656495L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer languge_id;

	@Column
	private String iso639_1;
	@Column
	private String iso639_2;
	@Column
	private String name;
	@Column
	private String nativeName;

	@Column(name = "capital_id")
	private Integer capital_id;

	public Integer getCapital_id() {
		return capital_id;
	}

	public void setCapital_id(Integer capital_id) {
		this.capital_id = capital_id;
	}

	public Integer getLanguge_id() {
		return languge_id;
	}

	public void setLanguge_id(Integer languge_id) {
		this.languge_id = languge_id;
	}

	public String getIso639_1() {
		return iso639_1;
	}

	public void setIso639_1(String iso639_1) {
		this.iso639_1 = iso639_1;
	}

	public String getIso639_2() {
		return iso639_2;
	}

	public void setIso639_2(String iso639_2) {
		this.iso639_2 = iso639_2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNativeName() {
		return nativeName;
	}

	public void setNativeName(String nativeName) {
		this.nativeName = nativeName;
	}

	@Override
	public String toString() {
		return "Languages [iso639_1=" + iso639_1 + ", iso639_2=" + iso639_2 + ", name=" + name + ", nativeName="
				+ nativeName + "]";
	}

}
