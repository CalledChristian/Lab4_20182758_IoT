package com.example.lab4_iot_of;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4_iot_of.bean.Geolocalizacion;
import com.example.lab4_iot_of.databinding.ActivityAppBinding;

import java.util.List;

public class AppActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //ActivityAppBinding binding;
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app);
        Log.d("msg-test","ingresa a app activty");
        //Button goClima = findViewById(R.id.button4);

        /*if (savedInstanceState == null) {
            goClima.setOnClickListener(view-> {
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .add(R.id.fragmentContainerView, ClimaFragment.class, null)
                        .commit();
            });
        }*/
    }
}