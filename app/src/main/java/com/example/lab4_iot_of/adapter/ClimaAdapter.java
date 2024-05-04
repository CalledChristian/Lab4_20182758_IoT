package com.example.lab4_iot_of.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4_iot_of.R;
import com.example.lab4_iot_of.bean.Geolocalizacion;

import java.util.List;

public class ClimaAdapter extends RecyclerView.Adapter<ClimaAdapter.ClimaViewHolder>{
    private Context context;

    private List<Geolocalizacion> listaGeo;

    public class ClimaViewHolder extends RecyclerView.ViewHolder{

        Geolocalizacion geolocalizacion;
        public ClimaViewHolder(@NonNull View itemView) {

            super(itemView);

        }

    }

    @NonNull
    @Override
    //Para inflar la vista
    public ClimaAdapter.ClimaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_clima, parent, false);
        return new ClimaAdapter.ClimaViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ClimaAdapter.ClimaViewHolder holder, int position) {


        Geolocalizacion geo = listaGeo.get(position) ;
        holder.geolocalizacion = geo;
        TextView ciudad = holder.itemView.findViewById(R.id.textView4);
        ciudad.setText(geo.getName());
        TextView latitud = holder.itemView.findViewById(R.id.textView5);
        String latitudStr = String.valueOf(latitud);
        latitud.setText(latitudStr);
        TextView longitud = holder.itemView.findViewById(R.id.textView6);
        String longitudStr = String.valueOf(longitud);
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
