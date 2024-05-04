package com.example.lab4_iot_of.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Main implements Serializable {

    @SerializedName("temp")
    private float temperatura;

    @SerializedName("temp_min")
    private float temperaturaMinima;

    @SerializedName("temp_max")
    private float temperaturaMaxima;


    public float getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public void setTemperaturaMinima(float temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    public float getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public void setTemperaturaMaxima(float temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }
}
