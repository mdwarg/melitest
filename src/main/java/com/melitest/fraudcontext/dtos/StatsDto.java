package com.melitest.fraudcontext.dtos;

public class StatsDto {

  private Integer maxLenght;
  private Integer minLenght;
  private Integer avgLenght;

  public StatsDto(final Integer maxLenght, final Integer minLenght, final Integer avgLenght) {
    super();
    this.maxLenght = maxLenght;
    this.minLenght = minLenght;
    this.avgLenght = avgLenght;
  }

  public Integer getMaxLenght() {
    return maxLenght;
  }

  public void setMaxLenght(final Integer maxLenght) {
    this.maxLenght = maxLenght;
  }

  public Integer getMinLenght() {
    return minLenght;
  }

  public void setMinLenght(final Integer minLenght) {
    this.minLenght = minLenght;
  }

  public Integer getAvgLenght() {
    return avgLenght;
  }

  public void setAvgLenght(final Integer avgLenght) {
    this.avgLenght = avgLenght;
  }

  @Override
  public String toString() {
    return "StatsDto [maxLenght=" + maxLenght + ", minLenght=" + minLenght + ", avgLenght=" + avgLenght + "]";
  }

}
