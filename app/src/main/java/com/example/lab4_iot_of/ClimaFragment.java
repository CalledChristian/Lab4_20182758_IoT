package com.example.lab4_iot_of;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab4_iot_of.adapter.ClimaAdapter;
import com.example.lab4_iot_of.adapter.GeolocalizacionAdapter;
import com.example.lab4_iot_of.bean.Clima;
import com.example.lab4_iot_of.bean.Geolocalizacion;
import com.example.lab4_iot_of.databinding.FragmentClimaBinding;
import com.example.lab4_iot_of.databinding.FragmentGeolocalizacionBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClimaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClimaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private FragmentClimaBinding binding;

    private String latitudStr;

    private String longitudStr;

    private EditText ciudad;

    private Button buscarButton;

    private List<Clima> listaClima = new ArrayList<>();



    private RecyclerView recyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ClimaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClimaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClimaFragment newInstance(String param1, String param2) {
        ClimaFragment fragment = new ClimaFragment();
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

        binding = FragmentClimaBinding.inflate(inflater,container,false);

        NavController navController = NavHostFragment.findNavController(ClimaFragment.this);


        ClimaAdapter climaAdapter = new  ClimaAdapter();

        ClimaInterface climaInterface = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ClimaInterface.class);

        //inicio

        binding.button5.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (binding.editTextText4 != null && binding.editTextText5 != null) {
                    latitudStr = binding.editTextText4.getEditableText().toString();
                    longitudStr = binding.editTextText5.getEditableText().toString();
                    Log.d("msg-test4", latitudStr);
                    Log.d("msg-test4", longitudStr);

                    climaInterface.getClima(latitudStr, longitudStr, "792edf06f1f5ebcaf43632b55d8b03fe").enqueue(new Callback<Clima>() {
                        @Override
                        public void onResponse(Call<Clima> call, Response<Clima> response) {
                            if (response.isSuccessful()) {
                                /*Log.d("msg-test3",geo.getCiudad());

                                Log.d("msg-test3", String.valueOf(geo.getLatitud()));
                                Log.d("msg-test3", String.valueOf(geo.getLongitud()));*/
                                Clima clima = response.body();
                                listaClima.add(clima);
                                climaAdapter.setContext(getContext());
                                climaAdapter.setListaClima(listaClima);
                                binding.recyclerviewClima.setAdapter(climaAdapter);
                                binding.recyclerviewClima.setLayoutManager(new LinearLayoutManager(getContext()));

                            } else {
                                Log.d("msg-test", "error en la respuesta del webservice");
                            }
                        }

                        @Override
                        public void onFailure(Call<Clima> call, Throwable t) {
                            t.printStackTrace();
                        }

                    });

                } else {
                    Toast.makeText(getActivity(), "Ingrese la latitud y longitud", Toast.LENGTH_SHORT).show();
                }
            }
        });



        //cambiar vista
        Button goGeo = getActivity().findViewById(R.id.button3);

        goGeo.setOnClickListener( view -> {
            navController.navigate(R.id.action_climaFragment_to_geolocalizacionFragment);
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}