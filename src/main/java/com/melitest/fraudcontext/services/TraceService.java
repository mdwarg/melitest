package com.melitest.fraudcontext.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melitest.fraudcontext.dtos.ContextDto;

@Service
public class TraceService {
  @Autowired
  private CountryInfoService countryInfoService;
  @Autowired
  private CurrencyInfoService currencyInfoService;
  @Autowired
  private GeolocationService geolocationService;

  public ContextDto getContextFor(final String ip) {
    String[] languagues = { "Español (es)" };
    String[] times = { "20:01:23 (UTC)", "19:01:23 (UTC-01)" };
    return new ContextDto(ip, "21/11/2020 15:12:03", "España (spain)", "es", languagues, "EUR (1 EUR = 1.0631 U$S)",
        times, "10270 kms");
  }

}
