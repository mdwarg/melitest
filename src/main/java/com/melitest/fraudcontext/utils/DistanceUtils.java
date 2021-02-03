package com.melitest.fraudcontext.utils;

public class DistanceUtils {
  private static final int EARTH_RADIO = 6371; // Km

  public static final double LAT_BA = -34.599722;

  public static final double LONG_BA = -58.381944;

  public static int calculateDistanceByHaversineFormula(double lon1, double lat1, double lon2, double lat2) {

    lat1 = Math.toRadians(lat1);
    lon1 = Math.toRadians(lon1);
    lat2 = Math.toRadians(lat2);
    lon2 = Math.toRadians(lon2);

    double dlon = (lon2 - lon1);
    double dlat = (lat2 - lat1);

    double sinlat = Math.sin(dlat / 2);
    double sinlon = Math.sin(dlon / 2);

    double a = (sinlat * sinlat) + Math.cos(lat1) * Math.cos(lat2) * (sinlon * sinlon);
    double c = 2 * Math.asin(Math.min(1.0, Math.sqrt(a)));

    double distanceInMeters = EARTH_RADIO * c;

    return (int) distanceInMeters;

  }
}
