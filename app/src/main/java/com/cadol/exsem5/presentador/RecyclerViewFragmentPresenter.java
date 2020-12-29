package com.cadol.exsem5.presentador;

import android.content.Context;

import com.cadol.exsem5.VistaFragment.IRecyclerViewFragmentView;
import com.cadol.exsem5.model.ConstrutorMascota;
import com.cadol.exsem5.model.Mascota;

import java.util.ArrayList;

public class RecyclerViewFragmentPresenter implements IRecylerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstrutorMascota constructorMascota;
    private ArrayList<Mascota> mascota;

    public  RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerMascota();
    }

    @Override
    public void obtenerMascota() {
        constructorMascota = new ConstrutorMascota(context);
        mascota = constructorMascota.obtenerDatos();
        mostrarMascotaRV();
    }

    @Override
    public void mostrarMascotaRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascota));
        iRecyclerViewFragmentView.generarLinearLayoutVertical();
    }

    @Override
    public void CincoMejores() {
        constructorMascota = new ConstrutorMascota(context);
        mascota = constructorMascota.CincoMejores();
        mostrarMascotaRV();
    }


}
