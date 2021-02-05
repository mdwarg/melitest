package com.melitest.fraudcontext.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melitest.fraudcontext.dtos.ContextDto;
import com.melitest.fraudcontext.exceptions.InternalServiceException;
import com.melitest.fraudcontext.exceptions.TraceNotFoundException;
import com.melitest.fraudcontext.models.CountryInfo;
import com.melitest.fraudcontext.models.CurrencyInfo;
import com.melitest.fraudcontext.models.Location;

@Service
public class TraceService {
  private static final Logger logger = LogManager.getLogger(TraceService.class);

  @Autowired
  private CountryInfoService countryInfoService;
  @Autowired
  private CurrencyInfoService currencyInfoService;
  @Autowired
  private GeolocationService geolocationService;
  @Autowired
  private StatsService statsService;

  public ContextDto getContextFor(final String ip) throws InternalServiceException, TraceNotFoundException {

    logger.info("Get location for ip: {}", ip);
    Location location = this.geolocationService.getLocationFromIp(ip);
    logger.info("Location: {}", location);
    if (location.getCode().equals("")) {
      throw new TraceNotFoundException("Couldn't trace the country from ip: " + ip);
    }

    logger.info("Get country info for code: {}", location.getCode());
    CountryInfo countryInfo = this.countryInfoService.getCountryInfoFromCode(location.getCode());
    logger.info("Country info: {}", countryInfo);
    String currency = "";

    LocalDateTime dateTime = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String date = dateTime.format(dateTimeFormatter);
    String country = countryInfo.getName() + " (" + countryInfo.getCapital() + ")";
    Integer distanceToBA = countryInfo.getDistanceToBA();

    if (!countryInfo.getCurrencyCode().equals("")) {
      logger.info("Get currency info for symbol: {}", countryInfo.getCurrencyCode());
      CurrencyInfo currencyInfo = this.currencyInfoService.getEuroValueFor(countryInfo.getCurrencyCode());
      logger.info("Currency info: {}", currencyInfo);
      currency = currencyInfo.getCode() + " (1 EUR = " + currencyInfo.getEurRate() + " " + currencyInfo.getCode() + ")";
    }

    this.statsService.updateStats(distanceToBA);
    return new ContextDto(ip, date, country, location.getCode(), countryInfo.getLanguages(), currency,
        countryInfo.getTimezones(), distanceToBA.toString() + " kms");
  }

}
