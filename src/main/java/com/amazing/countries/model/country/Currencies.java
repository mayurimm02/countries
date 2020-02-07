package com.amazing.countries.model.country;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Currencies")
public class Currencies {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Currencies_id")
	private Integer Currencies_id;

	@Column
	private String code;
	@Column
	private String name;
	@Column
	private String symbol;

	@Column(name = "capital_id")
	private Integer capital_id;

	public Integer getCapital_id() {
		return capital_id;
	}

	public void setCapital_id(Integer capital_id) {
		this.capital_id = capital_id;
	}

	public Integer getCurrencies_id() {
		return Currencies_id;
	}

	public void setCurrencies_id(Integer currencies_id) {
		Currencies_id = currencies_id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "Currencies [code=" + code + ", name=" + name + ", symbol=" + symbol + "]";
	}

}
