package com.cadol.exsem5.VistaFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cadol.exsem5.R;
import com.cadol.exsem5.adapter.MascotaAdaptador;
import com.cadol.exsem5.model.Mascota;
import com.cadol.exsem5.presentador.IRecylerViewFragmentPresenter;
import com.cadol.exsem5.presentador.PerfilFragmentPresenter;

import java.util.ArrayList;

public class PerfilFragmen extends Fragment implements IRecyclerViewFragmentView {

    private ArrayList<Mascota> mascota;
    private RecyclerView rvMejores;
    private IRecylerViewFragmentPresenter presenter;

    public PerfilFragmen() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        rvMejores = (RecyclerView) v.findViewById(R.id.rvMejores);
        presenter = new PerfilFragmentPresenter(this, getContext());

        return v;
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMejores.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascota) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascota, getActivity()  );
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        rvMejores.setAdapter(adaptador);
    }
}