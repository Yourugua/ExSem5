package com.cadol.exsem5.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import com.cadol.exsem5.DetalleMascota;
import com.cadol.exsem5.R;

import com.cadol.exsem5.model.ConstrutorMascota;
import com.cadol.exsem5.model.Mascota;
import com.google.android.material.tabs.TabLayout;


import java.util.ArrayList;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder>{

    ArrayList<Mascota> mascotas;
    Activity activity;
    TabLayout tabLayout;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas= mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);

        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvNombre.setText(mascota.getNombre());
        mascotaViewHolder.tvLikes.setText(String.valueOf(mascota.getLikes()) );

        mascotaViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, mascota.getNombre(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, DetalleMascota.class);
                intent.putExtra("nombre", mascota.getNombre());
                intent.putExtra("likes", mascota.getLikes());
                intent.putExtra("fotito", mascota.getFoto());
                activity.startActivity(intent);

            }
        });

        mascotaViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Diste like a " + mascota.getNombre(),
                        Toast.LENGTH_SHORT).show();

                ConstrutorMascota constructorMascota = new ConstrutorMascota(activity);
                constructorMascota.darLikeMascota(mascota);
                mascotaViewHolder.tvLikes.setText(String.valueOf(constructorMascota.obtenerLikesMascota(mascota)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView   imgFoto;
        private TextView    tvNombre;
        private ImageButton btnLike;
        private TextView    tvLikes;

        public MascotaViewHolder(View itemView) {
            super(itemView);

            imgFoto     = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombre    = (TextView) itemView.findViewById(R.id.tvNombreCV);
            btnLike     = (ImageButton) itemView.findViewById(R.id.btnLike);
            tvLikes     = (TextView) itemView.findViewById(R.id.tvLikes);

        }
    }
}
