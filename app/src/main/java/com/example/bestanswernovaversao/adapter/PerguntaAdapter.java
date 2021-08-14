package com.example.bestanswernovaversao.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestanswernovaversao.R;
import com.example.bestanswernovaversao.dao.PerguntaRepository;
import com.example.bestanswernovaversao.dao.RespostaRepository;
import com.example.bestanswernovaversao.model.Pergunta;

import java.util.ArrayList;
import java.util.List;

public class PerguntaAdapter extends RecyclerView.Adapter<PerguntaAdapter.MyViewHolder> {
    List<Pergunta> listaPerguntas = new ArrayList<>();
    private Context context;

    public PerguntaAdapter(List<Pergunta> perguntas, Context context) {
        this.listaPerguntas = perguntas;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //chamado para criar as visualizações - somente as primeiras que aparecem para o usuário
        //convertendo o XML em uma visualização
        //cria um objeto do tipo view
        View itemList = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_card, viewGroup, false);
        //retorna o itemList que é passado para o construtor da MyViewHolder
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, @SuppressLint("RecyclerView") int position) {
        Pergunta p = listaPerguntas.get(position);
        myViewHolder.linhaTitulo.setText(p.getTitulo());
        myViewHolder.linhaCategoria.setText("Categoria: " + p.getCategoria());

        myViewHolder.botaoDeletar.setOnClickListener((v -> {
            removerItem(position, p.get_id());
        }));

        Bundle bundle = new Bundle();
        bundle.putString("TITULO", listaPerguntas.get(position).getTitulo());
        bundle.putString("DESCRICAO", listaPerguntas.get(position).getDescricao());
        bundle.putInt("ID", listaPerguntas.get(position).get_id());
        bundle.putString("DATA", listaPerguntas.get(position).getData());
        bundle.putString("CATEGORIA", String.valueOf(listaPerguntas.get(position).getCategoria()));

        myViewHolder.botaoResponder.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.nav_responder_pergunta, bundle));

        myViewHolder.botaoEditar.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.nav_editar_pergunta, bundle));
    }

    @Override
    public int getItemCount() {
        return listaPerguntas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //dados do pedido que serão exibidos no recycler
        TextView linhaTitulo;
        TextView linhaCategoria;
        ImageButton botaoResponder;
        ImageButton botaoDeletar;
        ImageButton botaoEditar;

        public MyViewHolder(View itemView) {
            super(itemView);
            //passa uma referência para os componentes que estão na interface
            linhaTitulo = itemView.findViewById(R.id.textViewTituloPergunta);
            linhaCategoria = itemView.findViewById(R.id.textViewCategoriaPergunta);
            botaoResponder = itemView.findViewById(R.id.btnResponderPergunta);
            botaoDeletar = itemView.findViewById(R.id.btnDeletarPergunta);
            botaoEditar = itemView.findViewById(R.id.btnEditarPergunta);
        }
    }

    private void removerItem(int position, Integer idPergunta) {
        PerguntaRepository perguntaRepository = new PerguntaRepository(context);
        RespostaRepository respostaRepository = new RespostaRepository(context);
        respostaRepository.deleteByPergunta(idPergunta);
        perguntaRepository.delete(idPergunta);
        notifyItemRemoved(position);
        listaPerguntas.remove(position);
        notifyItemRangeChanged(position, listaPerguntas.size());
    }
}
