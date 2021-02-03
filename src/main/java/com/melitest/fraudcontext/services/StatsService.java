package com.melitest.fraudcontext.services;

import org.springframework.stereotype.Service;

import com.melitest.fraudcontext.dtos.StatsDto;

@Service
public class StatsService {

  private Integer requestCounter = 0;
  private Integer maxLenght = 0;
  private Integer minLenght = 99999;
  private Integer accumulatedLenght = 0;

  public StatsDto getStats() {
    int avgLenght = this.requestCounter != 0 ? this.accumulatedLenght / this.requestCounter : 0;
    return new StatsDto(this.maxLenght, this.minLenght, avgLenght);
  }

  public void updateStats(Integer distanceToBA) {
    this.requestCounter++;

    if (distanceToBA > this.maxLenght) {
      this.maxLenght = distanceToBA;
    }

    if (distanceToBA < this.minLenght) {
      this.minLenght = distanceToBA;
    }

    this.accumulatedLenght += distanceToBA;
  }
}
