package com.melitest.fraudcontext.models;

import java.util.List;

public class CountryInfo {
  private String name;
  private String capital;
  private String region;
  private String subregion;
  private Integer distanceToBA;
  private List<String> timezones;
  private String currencyCode;
  private List<String> languages;

  public CountryInfo(final String name, final String capital, final String region, final String subregion,
      final Integer distanceToBA, final List<String> timezones, final String currencyCode,
      final List<String> languages) {
    super();
    this.name = name;
    this.capital = capital;
    this.region = region;
    this.subregion = subregion;
    this.distanceToBA = distanceToBA;
    this.timezones = timezones;
    this.currencyCode = currencyCode;
    this.languages = languages;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getCapital() {
    return capital;
  }

  public void setCapital(final String capital) {
    this.capital = capital;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(final String region) {
    this.region = region;
  }

  public String getSubregion() {
    return subregion;
  }

  public void setSubregion(final String subregion) {
    this.subregion = subregion;
  }

  public Integer getDistanceToBA() {
    return distanceToBA;
  }

  public void setDistanceToBA(final Integer distanceToBA) {
    this.distanceToBA = distanceToBA;
  }

  public List<String> getTimezones() {
    return timezones;
  }

  public void setTimezones(final List<String> timezones) {
    this.timezones = timezones;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(final String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public List<String> getLanguages() {
    return languages;
  }

  public void setLanguages(final List<String> languages) {
    this.languages = languages;
  }

  @Override
  public String toString() {
    return "CountryInfo [name=" + name + ", capital=" + capital + ", region=" + region + ", subregion=" + subregion
        + ", distanceToBA=" + distanceToBA + ", timezones=" + timezones + ", currencyCode=" + currencyCode
        + ", languages=" + languages + "]";
  }

}
