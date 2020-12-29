package com.cadol.exsem5.presentador;

import android.content.Context;

import com.cadol.exsem5.VistaFragment.IRecyclerViewFragmentView;
import com.cadol.exsem5.model.ConstrutorMascota;
import com.cadol.exsem5.model.Mascota;

import java.util.ArrayList;

public class PerfilFragmentPresenter implements IRecylerViewFragmentPresenter {

   private IRecyclerViewFragmentView iRecyclerViewFragmentView;
   private Context context;
   private ConstrutorMascota constructorMascota;
   private ArrayList<Mascota> mascota;

   public  PerfilFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
      this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
      this.context = context;
      CincoMejores();
   }

    @Override
    public void CincoMejores() {
        constructorMascota = new ConstrutorMascota(context);
        mascota = constructorMascota.CincoMejores();
        mostrarMascotaRV();
    }

    @Override
    public void obtenerMascota() {
    }

    @Override
    public void mostrarMascotaRV() {
      iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascota));
      iRecyclerViewFragmentView.generarLinearLayoutVertical();
    }


}
