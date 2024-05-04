package com.example.lab4_iot_of.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4_iot_of.R;
import com.example.lab4_iot_of.bean.Geolocalizacion;

import java.util.List;

public class GeolocalizacionAdapter extends RecyclerView.Adapter<GeolocalizacionAdapter.GeolocalViewHolder>{
    private Context context;

    private List<Geolocalizacion> listaGeo;

    public class GeolocalViewHolder extends RecyclerView.ViewHolder{

        Geolocalizacion geolocalizacion;
        public GeolocalViewHolder(@NonNull View itemView) {

            super(itemView);

        }

    }

    @NonNull
    @Override
    //Para inflar la vista
    public GeolocalizacionAdapter.GeolocalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_geo, parent, false);
        return new GeolocalizacionAdapter.GeolocalViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull GeolocalizacionAdapter.GeolocalViewHolder holder, int position) {


        Geolocalizacion geo = listaGeo.get(position) ;
        holder.geolocalizacion = geo;
        TextView ciudad = holder.itemView.findViewById(R.id.textView4);
        ciudad.setText(geo.getCiudad());
        TextView latitud = holder.itemView.findViewById(R.id.textView5);
        String latitudStr = String.valueOf(geo.getLatitud());
        latitud.setText(latitudStr);
        TextView longitud = holder.itemView.findViewById(R.id.textView6);
        String longitudStr = String.valueOf(geo.getLatitud());
        latitud.setText(longitudStr);

        context = holder.itemView.getContext();
        /*ImageButton flecha1 = holder.itemView.findViewById(R.id.flecha1);
        flecha1.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), AdminInformacionSitioActivity.class); // Reemplaza "TuActivity" con el nombre de tu Activity
            context.startActivity(intent);
        });*/
    }

    @Override
    public int getItemCount() {
        //Este método debe indicar la cantidad total de elementos, en nuestro caso, del
        //arreglo “data”.
        return listaGeo.size();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Geolocalizacion> getListaGeo() {
        return listaGeo;
    }

    public void setListaGeo(List<Geolocalizacion> listaGeo) {
        this.listaGeo = listaGeo;
    }
}
