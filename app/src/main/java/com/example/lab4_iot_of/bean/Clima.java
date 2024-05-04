package com.example.lab4_iot_of.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Clima implements Serializable {

    @SerializedName("main")
    private Main main;

    @SerializedName("name")
    private String ciudad;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
