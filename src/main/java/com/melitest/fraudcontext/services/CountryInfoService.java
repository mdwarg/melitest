package com.melitest.fraudcontext.services;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.melitest.fraudcontext.exceptions.InternalServiceException;
import com.melitest.fraudcontext.models.CountryInfo;

@Service
public class CountryInfoService {
  private static final String API_URL = "https://restcountries.eu/rest/v2/alpha/";

  static LoadingCache<String, CountryInfo> countryInfoCache = CacheBuilder.newBuilder().maximumSize(300)
      .expireAfterAccess(10, TimeUnit.DAYS).build(new CacheLoader<String, CountryInfo>() {
        public CountryInfo load(String code) {
          return CountryInfoService.getCountryInfoFromAPI(code);
        }
      });

  public CountryInfo getCountryInfoFromCode(final String code) {
    try {
      return CountryInfoService.countryInfoCache.get(code);
    } catch (ExecutionException e) {
      throw new InternalServiceException("An error ocurred in Country info cache", e);
    }
  }

  private static CountryInfo getCountryInfoFromAPI(final String code) {
    try {
      HttpResponse<JsonNode> jsonResponse = Unirest.get(CountryInfoService.API_URL + code)
          .header("accept", "application/json").asJson();
      String name = jsonResponse.getBody().getObject().get("name").toString();
      String capital = jsonResponse.getBody().getObject().get("capital").toString();
      String region = jsonResponse.getBody().getObject().get("region").toString();
      String subregion = jsonResponse.getBody().getObject().get("subregion").toString();
      Integer[] latlng = { 1, -1 };// jsonResponse.getBody().getObject().get("latlng");
      String[] timezones = { "UTC-05:00" };
      jsonResponse.getBody().getObject().get("timezones");
      String currency = "COP"; // jsonResponse.getBody().getObject().get("currencies")[0].toString();
      String[] laguages = { "Español (es)" }; // jsonResponse.getBody().getObject().get("languages"));
      return new CountryInfo(name, capital, region, subregion, latlng, timezones, currency, laguages);
    } catch (UnirestException e) {
      throw new InternalServiceException("An error ocurred in Country info service", e);
    }
  }

}
