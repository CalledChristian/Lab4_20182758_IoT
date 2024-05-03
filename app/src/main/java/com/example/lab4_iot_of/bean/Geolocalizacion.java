package com.example.lab4_iot_of.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Geolocalizacion implements Serializable {

    @SerializedName("name")
    private String ciudad;

    @SerializedName("lat")
    private float latitud;

    @SerializedName("lon")
    private float longitud;

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }
}
