package com.cadol.exsem5.VistaFragment;

import com.cadol.exsem5.adapter.MascotaAdaptador;
import com.cadol.exsem5.model.Mascota;

import java.util.ArrayList;

public interface IRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascota);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
