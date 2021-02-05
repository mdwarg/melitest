package com.melitest.fraudcontext.services;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
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
import com.melitest.fraudcontext.utils.DistanceUtils;

@Service
public class CountryInfoService {
  private static final Logger logger = LogManager.getLogger(CountryInfoService.class);

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

  private static <T> CountryInfo getCountryInfoFromAPI(final String code) {
    try {
      Instant now = Instant.now();
      HttpResponse<JsonNode> jsonResponse = Unirest.get(CountryInfoService.API_URL + code)
          .header("accept", "application/json").asJson();

      String name = jsonResponse.getBody().getObject().get("name").toString();
      String capital = jsonResponse.getBody().getObject().get("capital").toString();
      String region = jsonResponse.getBody().getObject().get("region").toString();
      String subregion = jsonResponse.getBody().getObject().get("subregion").toString();

      List<String> languages = new ArrayList<String>();
      JSONArray lenguagesResponse = jsonResponse.getBody().getObject().getJSONArray("languages");
      for (int i = 0; i < lenguagesResponse.length(); i++) {
        String isoCode = "";
        try {
          isoCode = lenguagesResponse.getJSONObject(i).getString("iso639_1");
        } catch (Exception e) {
          isoCode = lenguagesResponse.getJSONObject(i).getString("iso639_2");
        }
        languages.add(lenguagesResponse.getJSONObject(i).getString("name") + " (" + isoCode + ")");
      }

      double lat = jsonResponse.getBody().getObject().getJSONArray("latlng").getDouble(0);
      double lng = jsonResponse.getBody().getObject().getJSONArray("latlng").getDouble(1);
      Integer distanceToBA = DistanceUtils.calculateDistanceByHaversineFormula(DistanceUtils.LONG_BA,
          DistanceUtils.LAT_BA, lng, lat);

      List<String> timezones = new ArrayList<String>();
      JSONArray timezonesResponse = jsonResponse.getBody().getObject().getJSONArray("timezones");
      DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("HH:mm:ss ")
          .appendZoneText(TextStyle.NARROW).toFormatter();
      for (int i = 0; i < timezonesResponse.length(); i++) {
        String utc = timezonesResponse.getString(i);
        timezones.add(ZonedDateTime.ofInstant(now, ZoneId.of(utc)).format(formatter));
      }

      String currency = jsonResponse.getBody().getObject().getJSONArray("currencies").getJSONObject(0)
          .getString("code");

      return new CountryInfo(name, capital, region, subregion, distanceToBA, timezones, currency, languages);
    } catch (UnirestException e) {
      logger.error("Error parsing response: " + e.getMessage());
      throw new InternalServiceException("An error ocurred in Country info service", e);
    } catch (Exception e) {
      logger.error("Error building country info: " + e.getMessage());
      throw new InternalServiceException("Error ocurred parsing response", e);
    }
  }

}
