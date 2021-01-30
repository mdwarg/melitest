package com.melitest.fraudcontext.dtos;

import java.util.Arrays;

public class ContextDto {

  private String ip;
  private String date;
  private String country;
  private String isoCode;
  private String[] languages;
  private String currency;
  private String[] times;
  private String estimatedDistance;

  public ContextDto(final String ip, final String date, final String country, final String isoCode,
      final String[] languages, final String currency, final String[] times, final String estimatedDistance) {
    super();
    this.ip = ip;
    this.date = date;
    this.country = country;
    this.isoCode = isoCode;
    this.languages = languages;
    this.currency = currency;
    this.times = times;
    this.estimatedDistance = estimatedDistance;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(final String ip) {
    this.ip = ip;
  }

  public String getDate() {
    return date;
  }

  public void setDate(final String date) {
    this.date = date;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(final String country) {
    this.country = country;
  }

  public String getIsoCode() {
    return isoCode;
  }

  public void setIsoCode(final String isoCode) {
    this.isoCode = isoCode;
  }

  public String[] getLanguages() {
    return languages;
  }

  public void setLanguages(final String[] languages) {
    this.languages = languages;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(final String currency) {
    this.currency = currency;
  }

  public String[] getTimes() {
    return times;
  }

  public void setTimes(final String[] times) {
    this.times = times;
  }

  public String getEstimatedDistance() {
    return estimatedDistance;
  }

  public void setEstimatedDistance(final String estimatedDistance) {
    this.estimatedDistance = estimatedDistance;
  }

  @Override
  public String toString() {
    return "ContextDto [ip=" + ip + ", date=" + date + ", country=" + country + ", isoCode=" + isoCode + ", languages="
        + Arrays.toString(languages) + ", currency=" + currency + ", times=" + Arrays.toString(times)
        + ", estimatedDistance=" + estimatedDistance + "]";
  }

}
