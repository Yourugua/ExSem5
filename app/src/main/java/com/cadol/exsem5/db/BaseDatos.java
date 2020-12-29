package com.cadol.exsem5.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cadol.exsem5.model.Mascota;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascotas = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTA + "(" +
                ConstantesBaseDatos.TABLE_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTA_LIKES + " INTEGER, " +
                ConstantesBaseDatos.TABLE_MASCOTA_FOTO + " INTEGER" +
                ")";
        String queryCrearTablaLikesMascotas = "CREATE TABLE " + ConstantesBaseDatos.TABLE_L_MASCOTA + "(" +
                ConstantesBaseDatos.TABLE_L_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_L_MASCOTA_ID_MASCOTA + " INTEGER, " +
                ConstantesBaseDatos.TABLE_L_MASCOTA_NRO_LIKES + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_L_MASCOTA_ID_MASCOTA + ") " +
                "REFERENCES " + ConstantesBaseDatos.TABLE_MASCOTA + "(" + ConstantesBaseDatos.TABLE_MASCOTA_ID + ")" +
                ")";

        db.execSQL(queryCrearTablaMascotas);
        db.execSQL(queryCrearTablaLikesMascotas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_MASCOTA);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_L_MASCOTA);
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerTodasLasMascotas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setLikes(registros.getInt(2));
            mascotaActual.setFoto(registros.getInt(3));

            String queryLikes = "SELECT COUNT("+ConstantesBaseDatos.TABLE_L_MASCOTA_NRO_LIKES+") as likes " +
                    " FROM " + ConstantesBaseDatos.TABLE_L_MASCOTA +
                    " WHERE " + ConstantesBaseDatos.TABLE_L_MASCOTA_ID_MASCOTA + "=" + mascotaActual.getId();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext()){
                mascotaActual.setLikes(registrosLikes.getInt(0));
            }else {
                mascotaActual.setLikes(0);
            }

            mascotas.add(mascotaActual);

        }

        db.close();

        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTA,null, contentValues);
        db.close();
    }

    public void insertarLikeMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert( ConstantesBaseDatos.TABLE_L_MASCOTA, null, contentValues);
        db.close();
    }


    public int obtenerLikesMascota(Mascota mascota){
        int likes = 0;

        String query = "SELECT COUNT("+ConstantesBaseDatos.TABLE_L_MASCOTA_NRO_LIKES+")" +
                " FROM " + ConstantesBaseDatos.TABLE_L_MASCOTA +
                " WHERE " + ConstantesBaseDatos.TABLE_L_MASCOTA_ID_MASCOTA + "="+mascota.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }

        // Actualizo tabla mascotas
        String sql = "UPDATE " + ConstantesBaseDatos.TABLE_MASCOTA +
                     " SET likes=" + likes +
                     " WHERE id=" + mascota.getId();
        db.execSQL(sql);

        db.close();

        return likes;
    }

    public ArrayList<Mascota> CincoMasQueridos() {

        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTA +
                       " ORDER By " + ConstantesBaseDatos.TABLE_MASCOTA_LIKES +
                       " DESC ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        int contador =0 ;
        while ((registros.moveToNext()) && (contador<5)){
           Mascota mascotaActual = new Mascota();
           mascotaActual.setId(registros.getInt(0));
           mascotaActual.setNombre(registros.getString(1));
           mascotaActual.setLikes(registros.getInt(2));
           mascotaActual.setFoto(registros.getInt(3));

           contador = contador +1;
           mascotas.add(mascotaActual);
        }
        db.close();

        return mascotas;
    }
}