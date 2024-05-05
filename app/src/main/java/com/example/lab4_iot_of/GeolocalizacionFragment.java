package com.example.lab4_iot_of;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab4_iot_of.adapter.GeolocalizacionAdapter;
import com.example.lab4_iot_of.bean.Geolocalizacion;
import com.example.lab4_iot_of.databinding.FragmentGeolocalizacionBinding;

import java.util.ArrayList;
import java.util.Arrays;
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
public class GeolocalizacionFragment extends Fragment implements SensorEventListener{

    public SensorManager mSensorManager ;

    public Sensor mAccelerometer;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String ciudadStr;

    private EditText ciudad;

    private Button buscarButton;

    private int scrollPosition = 0;


    private GeolocalizacionAdapter geoAdapter;

    private List<Geolocalizacion> listaGeo = new ArrayList<>();

    private LinearLayoutManager layoutManager;




    private RecyclerView recyclerView;

    public FragmentGeolocalizacionBinding binding;

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

        binding = FragmentGeolocalizacionBinding.inflate(inflater,container,false);

        mSensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);





        Log.d("msg-test2","ingresa a fragment");


        NavController navController = NavHostFragment.findNavController(GeolocalizacionFragment.this);


        // ciudad = getActivity().findViewById(R.id.editTextText2);

        //buscarButton = getActivity().findViewById(R.id.button2);

        //recyclerView = getActivity().findViewById(R.id.recyclerview_geolocalizacion);

        geoAdapter = new GeolocalizacionAdapter();

        GeolocalizationInterface geolocalizationInterface = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GeolocalizationInterface.class);
        //inicio
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.editTextText2 != null) {
                    ciudadStr = binding.editTextText2.getEditableText().toString();
                    geolocalizationInterface.getGeolocalizacion(ciudadStr, "1", "8dd6fc3be19ceb8601c2c3e811c16cf1").enqueue(new Callback<List<Geolocalizacion>>() {
                        @Override
                        public void onResponse(Call<List<Geolocalizacion>> call, Response<List<Geolocalizacion>> response) {
                            if (response.isSuccessful()) {
                                List<Geolocalizacion> lista = response.body();
                                Geolocalizacion geo = lista.get(0);

                                listaGeo.add(geo);
                                geoAdapter.setContext(getContext());
                                geoAdapter.setListaGeo(listaGeo);
                                binding.recyclerviewGeolocalizacion.setAdapter(geoAdapter);
                                binding.recyclerviewGeolocalizacion.setLayoutManager(new LinearLayoutManager(getContext()));

                            } else {
                                Log.d("msg-test", "error en la respuesta del webservice");
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Geolocalizacion>> call, Throwable t) {
                            t.printStackTrace();
                        }

                    });
                } else {
                    Toast.makeText(getContext(), "Ingrese una ciudad", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button goclima = getActivity().findViewById(R.id.button4);

        goclima.setOnClickListener( view -> {
            navController.navigate(R.id.action_geolocalizacionFragment_to_climaFragment);
        });

        /*layoutManager = new LinearLayoutManager(requireContext());
        binding.recyclerviewGeolocalizacion.setLayoutManager(layoutManager);

        // Restaurar la posición de desplazamiento si hay un estado guardado
        if (savedInstanceState != null) {
            scrollPosition = savedInstanceState.getInt("scroll_position", 0);
        }*/


        // Inflate the layout for this fragment
        return binding.getRoot();

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }


        @Override
        public void onResume() {
            super.onResume();
            if (mSensorManager != null) {
                mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                if (mAccelerometer != null) {
                    mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
                }
            }
        }
        @Override
        public void onStop() {
            super.onStop();
            mSensorManager.unregisterListener(this);
        }

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

        //Se debe validar qué sensor está enviando la información pues se puede tener un
        //listener para todos los sensores.

            int sensorType = sensorEvent.sensor.getType();

            if(sensorType == Sensor.TYPE_ACCELEROMETER) {

                //datos acelerometro
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];
                float aceleracionTotal = (float) Math.sqrt(x * x + y * y + z * z);
                LinearLayoutManager layoutManager = (LinearLayoutManager) binding.recyclerviewGeolocalizacion.getLayoutManager();

               // AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());

                if (aceleracionTotal > 15.0 && binding.recyclerviewGeolocalizacion.getAdapter() != null) {
                    //Toast.makeText(getContext(), "Su velocidad: " + aceleracionTotal + " m/s^2", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setMessage("Deshacer Acción");
                    alertDialog.setPositiveButton("Deshacer", (dialogInterface, i) -> {
                        int lastPosition = layoutManager.getItemCount() - 1;
                        binding.recyclerviewGeolocalizacion.smoothScrollToPosition(lastPosition);
                    });

                    alertDialog.setNegativeButton("Cancelar", ((dialogInterface, i) -> {
                        Log.d("msg-test", "Cancelado");
                    }));

                    alertDialog.show();
                }
            }
        }


        /*@Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            layoutManager = new LinearLayoutManager(requireContext());
            binding.recyclerviewGeolocalizacion.setLayoutManager(layoutManager);

            // Restaurar la posición de desplazamiento si hay un estado guardado
            if (savedInstanceState != null) {
                scrollPosition = savedInstanceState.getInt("scroll_position", 0);
            }


            geoAdapter = new GeolocalizacionAdapter();

            GeolocalizationInterface geolocalizationInterface = new Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(GeolocalizationInterface.class);


            binding.button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (binding.editTextText2 != null) {
                        ciudadStr = binding.editTextText2.getEditableText().toString();
                        geolocalizationInterface.getGeolocalizacion(ciudadStr, "1", "8dd6fc3be19ceb8601c2c3e811c16cf1").enqueue(new Callback<List<Geolocalizacion>>() {
                            @Override
                            public void onResponse(Call<List<Geolocalizacion>> call, Response<List<Geolocalizacion>> response) {
                                if (response.isSuccessful()) {
                                    List<Geolocalizacion> lista = response.body();
                                    Geolocalizacion geo = lista.get(0)
                                    listaGeo.add(geo);
                                    geoAdapter.setContext(getContext());
                                    geoAdapter.setListaGeo(listaGeo);
                                    binding.recyclerviewGeolocalizacion.setAdapter(geoAdapter);
                                    binding.recyclerviewGeolocalizacion.setLayoutManager(new LinearLayoutManager(getContext()));

                                } else {
                                    Log.d("msg-test", "error en la respuesta del webservice");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<Geolocalizacion>> call, Throwable t) {
                                t.printStackTrace();
                            }

                        });
                    } else {
                        Toast.makeText(getContext(), "Ingrese una ciudad", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }

        @Override
        public void onResume() {
            super.onResume();

            // Restaurar la posición de desplazamiento cuando el fragmento se vuelve visible
            binding.recyclerviewGeolocalizacion.scrollToPosition(scrollPosition);
        }

        @Override
        public void onSaveInstanceState(@NonNull Bundle outState) {
            super.onSaveInstanceState(outState);

            // Guardar la posición de desplazamiento cuando el fragmento se destruye
            outState.putInt("scroll_position", layoutManager.findFirstVisibleItemPosition());
        }*/



}
