package com.melitest.fraudcontext.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
  @Autowired
  private StatsService statsService;

  public ContextDto getContextFor(final String ip) {

    Location location = this.geolocationService.getLocationFromIp(ip);
    CountryInfo countryInfo = this.countryInfoService.getCountryInfoFromCode(location.getCode());
    CurrencyInfo currencyInfo = this.currencyInfoService.getEuroValueFor(countryInfo.getCurrencyCode());
    LocalDateTime dateTime = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String date = dateTime.format(dateTimeFormatter);
    String country = countryInfo.getName() + " (" + countryInfo.getCapital() + ")";
    String currency = currencyInfo.getCode() + " (1 EUR = " + currencyInfo.getEurRate() + " " + currencyInfo.getCode()
        + ")";
    Integer distanceToBA = countryInfo.getDistanceToBA();
    this.statsService.updateStats(distanceToBA);
    return new ContextDto(ip, date, country, location.getCode(), countryInfo.getLanguages(), currency,
        countryInfo.getTimezones(), distanceToBA.toString() + " kms");
  }

}
