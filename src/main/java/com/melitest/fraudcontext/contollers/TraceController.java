package com.melitest.fraudcontext.contollers;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.melitest.fraudcontext.dtos.ContextDto;
import com.melitest.fraudcontext.services.TraceService;

@RestController
@RequestMapping("/trace")
public class TraceController {
  private static final Logger logger = LogManager.getLogger(TraceController.class);

  @Autowired
  private TraceService traceService;

  @PostMapping("/")
  public ResponseEntity<ContextDto> getContext(@RequestBody Map<String, String> body) {
    String ip = body.get("ip");
    logger.info("Trace ip: {}", ip);
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<ContextDto>(this.traceService.getContextFor(ip), headers, HttpStatus.OK);
  }
}
