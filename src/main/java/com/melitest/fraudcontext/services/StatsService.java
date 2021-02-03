package com.melitest.fraudcontext.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.melitest.fraudcontext.dtos.StatsDto;

@Service
public class StatsService {

  private static final Logger logger = LogManager.getLogger(TraceService.class);

  private Integer requestCounter = 0;
  private Integer maxLenght;
  private Integer minLenght;
  private Integer accumulatedLenght = 0;

  public StatsDto getStats() {
    logger.info("Calculating stats");
    int avgLenght = this.requestCounter != 0 ? this.accumulatedLenght / this.requestCounter : 0;
    return new StatsDto(this.maxLenght, this.minLenght, avgLenght);
  }

  public void updateStats(Integer distanceToBA) {
    logger.info("Updating stats with distance: {}", distanceToBA);
    this.requestCounter++;
    logger.info("Amount of traced request: {}", this.requestCounter);

    if (this.maxLenght == null || distanceToBA > this.maxLenght) {
      this.maxLenght = distanceToBA;
    }

    if (this.minLenght == null || distanceToBA < this.minLenght) {
      this.minLenght = distanceToBA;
    }

    this.accumulatedLenght += distanceToBA;
  }
}
