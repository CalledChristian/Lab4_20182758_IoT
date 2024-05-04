package com.example.lab4_iot_of;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }

    public void irAppActivity(View view) {

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        boolean tieneInternet = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        if (tieneInternet) {
            Log.d("msg-test", "Internet: " + tieneInternet);
            //primero crear el intento
            Intent intent = new Intent(this, AppActivity.class);
            //iniciar activity
            startActivity(intent);

        } else {
            mostrarDialog();
            //Toast.makeText(this, "No se pudo establecer una conexión con internet", Toast.LENGTH_SHORT).show();
        }
    }

    public void mostrarDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("No se pudo establecer una conexión con internet");

        alertDialog.setPositiveButton("Ok", (dialogInterface, i) -> {
            Log.d("msg-test", "Ok presionado");
        });

        alertDialog.setNegativeButton("Configuración", ((dialogInterface, i) -> {
            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            startActivity(intent);
            Log.d("msg-test", "Configuración presionado");
        }));

        alertDialog.show();
    }

}