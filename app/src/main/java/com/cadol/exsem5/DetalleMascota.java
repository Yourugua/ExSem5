package com.cadol.exsem5;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleMascota extends AppCompatActivity {

    private static final String KEY_EXTRA_URL = "url";
    private static final String KEY_EXTRA_LIKE = "like";
    private TextView tvNombre;
    private TextView tvLike;
    private ImageView tvfoto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mascota);

        Bundle extras      = getIntent().getExtras();
        String nombre      = extras.getString("nombre");
        Integer like        = extras.getInt("likes");
        Integer Foto        = extras.getInt("fotito");

        tvNombre    = (TextView) findViewById(R.id.tvNombre);
        tvLike      = (TextView) findViewById(R.id.tvLike);
        tvfoto      = (ImageView) findViewById(R.id.tvfoto);

        tvNombre.setText(nombre);
        tvLike.setText(String.valueOf(like));
        tvfoto.setImageResource(Foto);
    }
}
