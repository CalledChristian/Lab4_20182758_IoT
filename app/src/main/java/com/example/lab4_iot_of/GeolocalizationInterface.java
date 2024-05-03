package com.example.lab4_iot_of;

import com.example.lab4_iot_of.bean.Geolocalizacion;

import java.lang.reflect.Array;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeolocalizationInterface {

    @GET("/geo/1.0/direct")
    Call<List<Geolocalizacion>> getGeolocalizacion (@Query("q") String ciudad,
                                                    @Query("limit") String limit,
                                                    @Query("appid") String apikey);

}
