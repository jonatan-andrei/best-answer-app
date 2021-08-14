package com.example.bestanswernovaversao.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestanswernovaversao.R;
import com.example.bestanswernovaversao.adapter.ArtigoAdapter;
import com.example.bestanswernovaversao.model.Artigo;
import com.example.bestanswernovaversao.util.ConfiguraFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListarArtigosFragment extends Fragment {

    RecyclerView recyclerView;

    List<Artigo> artigos;

    ArtigoAdapter artigoAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listar_artigos, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewListarArtigos);

        carregarArtigos(view.getContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        //definindo o layout do recycler
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    private void carregarArtigos(Context context) {
        final DatabaseReference reference = ConfiguraFirebase.getNo("artigos");
        artigos = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            //é chamado sempre que consegue recuperar algum dado
            //DataSnapshot é o retorno do Firebase => resultado da consulta
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Artigo artigo = ds.getValue(Artigo.class);
                    artigo.setId(ds.getKey());
                    artigos.add(artigo);
                }
                artigoAdapter = new ArtigoAdapter(context, artigos);
                recyclerView.setAdapter(artigoAdapter);
                recyclerView.setHasFixedSize(true);
                reference.removeEventListener(this);
            }

            @Override
            //chamado quando a requisição é cancelada
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}