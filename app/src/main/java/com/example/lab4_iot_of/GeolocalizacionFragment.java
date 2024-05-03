package com.example.lab4_iot_of;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lab4_iot_of.bean.Geolocalizacion;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GeolocalizacionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GeolocalizacionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GeolocalizacionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GeolocalizacionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GeolocalizacionFragment newInstance(String param1, String param2) {
        GeolocalizacionFragment fragment = new GeolocalizacionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //datos
        TextView ciudad = findViewById(R.id.textView4);
        TextView latitud = findViewById(R.id.textView5);
        TextView longitud = findViewById(R.id.textView6);

        GeolocalizationInterface geolocalizationInterface = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GeolocalizationInterface.class);


        geolocalizationInterface.getGeolocalizacion(ciudad,"1","8dd6fc3be19ceb8601c2c3e811c16cf1").enqueue(new Callback<List<Geolocalizacion>>() {
            @Override
            public void onResponse(Call<List<Geolocalizacion>> call, Response<List<Geolocalizacion>> response) {
                if(response.isSuccessful()) {
                    List<Geolocalizacion> lista = response.body();
                    Geolocalizacion geolocalizacion = lista.get(1);
                    ciudad.setText(geolocalizacion.getCiudad());
                    String latitudStr = String.valueOf(geolocalizacion.getLatitud());
                    latitud.setText(latitudStr);
                    String longitudStr = String.valueOf(geolocalizacion.getLongitud());
                    longitud.setText(longitudStr);

                }else{
                    Log.d("msg-test", "error en la respuesta del webservice");
                }
            }
            @Override
            public void onFailure(Call<List<Geolocalizacion>> call, Throwable t) {
                t.printStackTrace();
            }


        });


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_geolocalizacion, container, false);

        /*Button goclima = findViewById(R.id.button4);

        goclima.setOnClickListener( view -> {*/

        }

}
