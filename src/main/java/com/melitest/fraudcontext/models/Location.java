package com.melitest.fraudcontext.models;

public class Location {

  private String code;
  private String name;

  public Location(final String code, final String name) {
    super();
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(final String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Location [code=" + code + ", name=" + name + "]";
  }

}
