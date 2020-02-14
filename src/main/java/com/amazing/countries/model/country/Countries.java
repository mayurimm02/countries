package com.amazing.countries.model.country;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//@JsonIgnoreProperties(value = "ignoreUnknown")
@Entity
@Table(name = "CapitalCity")
public class Countries implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "capital_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer capital_id;

	@Column
	private String name;
	@Lob
	@Column
	private ArrayList<String> topLevelDomain;
	@Column
	private String alpha2Code;
	@Column
	private String alpha3Code;
	@Lob
	@Column
	private ArrayList<String> callingCodes;
	@Column
	private String capital;
	@Lob
	@Column
	private ArrayList<String> altSpellings;
	@Column
	private String region;
	@Column
	private String subregion;
	@Column
	private Long population;
	@Lob
	@Column
	private ArrayList<String> latlng;
	@Column
	private String demonym;
	@Column
	private Double area;
	@Column
	private Double gini;
	@Lob
	@Column
	private ArrayList<String> timezones;
	@Lob
	@Column
	private ArrayList<String> borders;
	@Column
	private String nativeName;
	@Column
	private Integer numericCode;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "capital_id", referencedColumnName = "capital_id")
	private List<Currencies> currencies;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "capital_id", referencedColumnName = "capital_id")

	private List<Languages> languages;
	@Embedded
	private Translations translations;

	@Column
	private String flag;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "capital_id", referencedColumnName = "capital_id")

	private List<RegionalBlocs> regionalBlocs;

	@Column
	private String cioc;

	public Translations getTranslations() {
		return translations;
	}

	public void setTranslations(Translations translations) {
		this.translations = translations;
	}

	public Integer getCapital_id() {
		return capital_id;
	}

	public void setCapital_id(Integer capital_id) {
		this.capital_id = capital_id;
	}

	public List<RegionalBlocs> getRegionalBlocs() {
		return regionalBlocs;
	}

	public void setRegionalBlocs(List<RegionalBlocs> regionalBlocs) {
		this.regionalBlocs = regionalBlocs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getTopLevelDomain() {
		return topLevelDomain;
	}

	public void setTopLevelDomain(ArrayList<String> topLevelDomain) {
		this.topLevelDomain = topLevelDomain;
	}

	public String getAlpha2Code() {
		return alpha2Code;
	}

	public void setAlpha2Code(String alpha2Code) {
		this.alpha2Code = alpha2Code;
	}

	public String getAlpha3Code() {
		return alpha3Code;
	}

	public void setAlpha3Code(String alpha3Code) {
		this.alpha3Code = alpha3Code;
	}

	public ArrayList<String> getCallingCodes() {
		return callingCodes;
	}

	public void setCallingCodes(ArrayList<String> callingCodes) {
		this.callingCodes = callingCodes;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public ArrayList<String> getAltSpellings() {
		return altSpellings;
	}

	public void setAltSpellings(ArrayList<String> altSpellings) {
		this.altSpellings = altSpellings;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSubregion() {
		return subregion;
	}

	public void setSubregion(String subregion) {
		this.subregion = subregion;
	}

	public Long getPopulation() {
		return population;
	}

	public void setPopulation(Long population) {
		this.population = population;
	}

	public ArrayList<String> getLatlng() {
		return latlng;
	}

	public void setLatlng(ArrayList<String> latlng) {
		this.latlng = latlng;
	}

	public String getDemonym() {
		return demonym;
	}

	public void setDemonym(String demonym) {
		this.demonym = demonym;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Double getGini() {
		return gini;
	}

	public void setGini(Double gini) {
		this.gini = gini;
	}

	public ArrayList<String> getTimezones() {
		return timezones;
	}

	public void setTimezones(ArrayList<String> timezones) {
		this.timezones = timezones;
	}

	public ArrayList<String> getBorders() {
		return borders;
	}

	public void setBorders(ArrayList<String> borders) {
		this.borders = borders;
	}

	public String getNativeName() {
		return nativeName;
	}

	public void setNativeName(String nativeName) {
		this.nativeName = nativeName;
	}

	public Integer getNumericCode() {
		return numericCode;
	}

	public void setNumericCode(Integer numericCode) {
		this.numericCode = numericCode;
	}

	public List<Languages> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Languages> languages) {
		this.languages = languages;
	}

	/*
	 * public Translations getTranslations() { return translations; }
	 * 
	 * public void setTranslations(Translations translations) { this.translations =
	 * translations; }
	 */

	public List<Currencies> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<Currencies> currencies) {
		this.currencies = currencies;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCioc() {
		return cioc;
	}

	public void setCioc(String cioc) {
		this.cioc = cioc;
	}

	@Override
	public String toString() {
		return "CapitalCity [capital_id=" + capital_id + ", name=" + name + ", topLevelDomain=" + topLevelDomain
				+ ", alpha2Code=" + alpha2Code + ", alpha3Code=" + alpha3Code + ", callingCodes=" + callingCodes
				+ ", capital=" + capital + ", altSpellings=" + altSpellings + ", region=" + region + ", subregion="
				+ subregion + ", population=" + population + ", latlng=" + latlng + ", demonym=" + demonym + ", area="
				+ area + ", gini=" + gini + ", timezones=" + timezones + ", borders=" + borders + ", nativeName="
				+ nativeName + ", numericCode=" + numericCode + ", currencies=" + currencies + ", languages="
				+ languages + ", translations=" + translations + ", flag=" + flag + ", regionalBlocs=" + regionalBlocs
				+ ", cioc=" + cioc + "]";
	}

}
