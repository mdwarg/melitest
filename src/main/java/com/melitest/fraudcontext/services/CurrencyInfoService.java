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
import com.melitest.fraudcontext.models.CurrencyInfo;

@Service
public class CurrencyInfoService {

  static LoadingCache<String, CurrencyInfo> currencyInfoCache = CacheBuilder.newBuilder().maximumSize(300)
      .expireAfterAccess(10, TimeUnit.DAYS).build(new CacheLoader<String, CurrencyInfo>() {
        public CurrencyInfo load(String currency) {
          return CurrencyInfoService.getCurrencyInfoFromAPI(currency);
        }
      });

  // API access key: 8df12c260cb0f21c45dfb70eed70cb68
  // API endpoint: http://data.fixer.io/api/
  // http://data.fixer.io/api/latest?access_key=8df12c260cb0f21c45dfb70eed70cb68

  private static final String API_URL = "http://data.fixer.io/api/latest?access_key=8df12c260cb0f21c45dfb70eed70cb68&base=EUR&symbols=";

  public CurrencyInfo getEuroValueFor(final String currency) {
    try {
      return CurrencyInfoService.currencyInfoCache.get(currency);
    } catch (ExecutionException e) {
      throw new InternalServiceException("An error ocurred in Currency info cache", e);
    }
  }

  private static CurrencyInfo getCurrencyInfoFromAPI(final String currency) {
    try {
      HttpResponse<JsonNode> jsonResponse = Unirest.get(CurrencyInfoService.API_URL + currency)
          .header("accept", "application/json").asJson();
      return new CurrencyInfo(currency, "EUR RATE 1.111");
    } catch (UnirestException e) {
      throw new InternalServiceException("An error ocurred in Currency info service", e);
    }
  }
}
