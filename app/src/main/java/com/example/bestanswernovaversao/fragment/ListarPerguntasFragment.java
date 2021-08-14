package com.example.bestanswernovaversao.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bestanswernovaversao.R;
import com.example.bestanswernovaversao.adapter.PerguntaAdapter;
import com.example.bestanswernovaversao.dao.PerguntaRepository;
import com.example.bestanswernovaversao.model.Pergunta;

import java.util.List;

public class ListarPerguntasFragment extends Fragment {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listar_perguntas, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewListarPerguntas);

        //configurar o adapter - que formata que o layout de cada item do recycler
        PerguntaRepository perguntaRepository = new PerguntaRepository(getActivity());
        List<Pergunta> perguntas = perguntaRepository.getAll();
        PerguntaAdapter myAdapter = new PerguntaAdapter(perguntas, getActivity().getApplicationContext());
        recyclerView.setAdapter(myAdapter);
        //linha de código usada para otimizar o recycler
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        //definindo o layout do recycler
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }
}