package com.example.lab4_iot_of;

import com.example.lab4_iot_of.bean.Clima;
import com.example.lab4_iot_of.bean.Geolocalizacion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ClimaInterface {

    @GET("/data/2.5/weather")
    Call<Clima> getClima (@Query("lat") String latitud,
                        @Query("lon") String longitud,
                      @Query("appid") String apikey);


}
