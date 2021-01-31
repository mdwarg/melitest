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
import com.melitest.fraudcontext.models.Location;

@Service
public class GeolocationService {
  private static final String API_URL = "https://api.ip2country.info/ip?";

  static LoadingCache<String, Location> locationsCache = CacheBuilder.newBuilder().maximumSize(10000)
      .expireAfterAccess(1, TimeUnit.HOURS).build(new CacheLoader<String, Location>() {
        public Location load(String ip) {
          return GeolocationService.getLocationFromAPI(ip);
        }
      });

  public Location getLocationFromIp(final String ip) throws InternalServiceException {
    try {
      return GeolocationService.locationsCache.get(ip);
    } catch (ExecutionException e) {
      throw new InternalServiceException("An error ocurred in location cache", e);
    }
  }

  private static Location getLocationFromAPI(final String ip) {
    try {
      HttpResponse<JsonNode> jsonResponse = Unirest.get(GeolocationService.API_URL + ip)
          .header("accept", "application/json").asJson();
      return new Location(jsonResponse.getBody().getObject().get("countryCode3").toString(),
          jsonResponse.getBody().getObject().get("countryName").toString());
    } catch (UnirestException e) {
      throw new InternalServiceException("An error ocurred in Geolocation service", e);
    }
  }
}
