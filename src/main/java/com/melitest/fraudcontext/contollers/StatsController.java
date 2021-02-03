package com.melitest.fraudcontext.contollers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.melitest.fraudcontext.dtos.StatsDto;
import com.melitest.fraudcontext.services.StatsService;

@RestController
@RequestMapping("/stats")
public class StatsController {

  private static final Logger logger = LogManager.getLogger(StatsController.class);

  @Autowired
  private StatsService statsService;

  @GetMapping("/")
  public ResponseEntity<StatsDto> getStats() {
    logger.info("Getting stats");
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<StatsDto>(this.statsService.getStats(), headers, HttpStatus.OK);
  }
}
