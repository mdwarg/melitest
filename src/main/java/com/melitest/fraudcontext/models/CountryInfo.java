package com.melitest.fraudcontext.models;

import java.util.Arrays;

public class CountryInfo {
  private String name;
  private String capital;
  private String region;
  private String subregion;
  private Integer[] latLng;
  private String[] timezones;
  private String currencyCode;
  private String[] languages;

  public CountryInfo(final String name, final String capital, final String region, final String subregion,
      final Integer[] latLng, final String[] timezones, final String currencyCode, final String[] languages) {
    super();
    this.name = name;
    this.capital = capital;
    this.region = region;
    this.subregion = subregion;
    this.latLng = latLng;
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

  public Integer[] getLatLng() {
    return latLng;
  }

  public void setLatLng(final Integer[] latLng) {
    this.latLng = latLng;
  }

  public String[] getTimezones() {
    return timezones;
  }

  public void setTimezones(final String[] timezones) {
    this.timezones = timezones;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(final String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public String[] getLanguages() {
    return languages;
  }

  public void setLanguages(final String[] languages) {
    this.languages = languages;
  }

  @Override
  public String toString() {
    return "CountryInfo [name=" + name + ", capital=" + capital + ", region=" + region + ", subregion=" + subregion
        + ", latLng=" + Arrays.toString(latLng) + ", timezones=" + Arrays.toString(timezones) + ", currencyCode="
        + currencyCode + ", languages=" + Arrays.toString(languages) + "]";
  }

}
