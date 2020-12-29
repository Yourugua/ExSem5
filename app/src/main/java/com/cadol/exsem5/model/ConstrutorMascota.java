package com.cadol.exsem5.model;

import android.content.ContentValues;
import android.content.Context;
import com.cadol.exsem5.db.BaseDatos;
import com.cadol.exsem5.db.ConstantesBaseDatos;

import java.util.ArrayList;

public class ConstrutorMascota {

    private static final int LIKE = 1;
    private Context context;

    public ConstrutorMascota(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos() {
        BaseDatos db = new BaseDatos(context);
        return  db.obtenerTodasLasMascotas();
    }

    public void darLikeMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_L_MASCOTA_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_L_MASCOTA_NRO_LIKES, LIKE);
        db.insertarLikeMascota(contentValues);
    }

    public int obtenerLikesMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascota(mascota);
    }

    public ArrayList<Mascota> CincoMejores() {
        BaseDatos db = new BaseDatos(context);
        return  db.CincoMasQueridos();
    }
}


