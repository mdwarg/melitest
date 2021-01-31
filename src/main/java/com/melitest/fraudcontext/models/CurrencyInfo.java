package com.melitest.fraudcontext.models;

public class CurrencyInfo {
// {"date":"2021-01-31","success":true,"rates":{"USD":1.21375},"timestamp":1612063986,"base":"EUR"}
  private String code;
  private String eurRate;

  public CurrencyInfo(String code, String eurRate) {
    super();
    this.code = code;
    this.eurRate = eurRate;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getEurRate() {
    return eurRate;
  }

  public void setEurRate(String eurRate) {
    this.eurRate = eurRate;
  }

  @Override
  public String toString() {
    return "CurrencyInfo [code=" + code + ", eurRate=" + eurRate + "]";
  }

}
