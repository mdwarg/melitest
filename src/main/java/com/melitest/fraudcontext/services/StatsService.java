package com.melitest.fraudcontext.services;

import org.springframework.stereotype.Service;

import com.melitest.fraudcontext.dtos.StatsDto;

@Service
public class StatsService {

  public StatsDto getStats() {
    return new StatsDto(15232, 526, 785);
  }
}
