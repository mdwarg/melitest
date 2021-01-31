package com.melitest.fraudcontext.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melitest.fraudcontext.dtos.ContextDto;
import com.melitest.fraudcontext.models.CountryInfo;
import com.melitest.fraudcontext.models.CurrencyInfo;
import com.melitest.fraudcontext.models.Location;

@Service
public class TraceService {
  @Autowired
  private CountryInfoService countryInfoService;
  @Autowired
  private CurrencyInfoService currencyInfoService;
  @Autowired
  private GeolocationService geolocationService;

  public ContextDto getContextFor(final String ip) {
    Location location = this.geolocationService.getLocationFromIp(ip);
    System.out.println("LOCATION: " + location);
    CountryInfo countryInfo = this.countryInfoService.getCountryInfoFromCode(location.getCode());
    System.out.println("COUNTRY INFO: " + countryInfo);
    CurrencyInfo currencyInfo = this.currencyInfoService.getEuroValueFor(countryInfo.getCurrencyCode());
    System.out.println("CURRENCY INFO: " + currencyInfo);
    String[] languagues = { "Español (es)" };
    String[] times = { "20:01:23 (UTC)", "19:01:23 (UTC-01)" };
    return new ContextDto(ip, "21/11/2020 15:12:03", "España (spain)", "es", languagues, "EUR (1 EUR = 1.0631 U$S)",
        times, "10270 kms");
  }

}
