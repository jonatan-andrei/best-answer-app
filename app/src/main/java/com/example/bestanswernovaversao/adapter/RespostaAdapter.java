package com.example.bestanswernovaversao.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bestanswernovaversao.R;
import com.example.bestanswernovaversao.model.Resposta;

import java.util.ArrayList;
import java.util.List;

public class RespostaAdapter extends RecyclerView.Adapter<RespostaAdapter.MyViewHolder> {
    List<Resposta> listaRespostas = new ArrayList<>();

    public RespostaAdapter(List<Resposta> respostas) {
        this.listaRespostas = respostas;
    }

    @Override
    public RespostaAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //chamado para criar as visualizações - somente as primeiras que aparecem para o usuário
        //convertendo o XML em uma visualização
        //cria um objeto do tipo view
        View itemList = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_card_resposta, viewGroup, false);
        //retorna o itemList que é passado para o construtor da MyViewHolder
        return new RespostaAdapter.MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(RespostaAdapter.MyViewHolder myViewHolder, @SuppressLint("RecyclerView") int position) {
        Resposta r = listaRespostas.get(position);
        myViewHolder.conteudo.setText(r.getConteudo());
    }

    @Override
    public int getItemCount() {
        return listaRespostas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView conteudo;

        public MyViewHolder(View itemView) {
            super(itemView);
            conteudo = itemView.findViewById(R.id.textViewRespostaLista);
        }
    }
}
