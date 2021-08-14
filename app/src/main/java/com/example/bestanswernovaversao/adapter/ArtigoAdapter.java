package com.example.bestanswernovaversao.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestanswernovaversao.R;
import com.example.bestanswernovaversao.model.Artigo;
import com.example.bestanswernovaversao.util.ConfiguraFirebase;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class ArtigoAdapter extends RecyclerView.Adapter<ArtigoAdapter.MyViewHolder> {

    List<Artigo> artigos;
    Context context;

    public ArtigoAdapter(Context context, List<Artigo> artigos) {
        this.artigos = artigos;
        this.context = context;
    }

    @Override
    public ArtigoAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //chamado para criar as visualizações - somente as primeiras que aparecem para o usuário
        //convertendo o XML em uma visualização
        //cria um objeto do tipo view
        View itemList = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_card_artigo, viewGroup, false);
        //retorna o itemList que é passado para o construtor da MyViewHolder
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(ArtigoAdapter.MyViewHolder myViewHolder, @SuppressLint("RecyclerView") final int position) {
        //exibe os itens no Recycler
        final Artigo artigo = artigos.get(position);
        myViewHolder.titulo.setText(artigo.getTitulo());
        myViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removerItem(position);
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("chave", artigo.getId());
        bundle.putString("titulo", artigo.getTitulo());
        bundle.putString("conteudo", artigo.getConteudo());
        myViewHolder.btnEdit.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_editar_artigo, bundle));

    }

    public void removerItem(final int position) {
        new AlertDialog.Builder(context)
                .setTitle("Deletando artigo")
                .setMessage("Tem certeza que deseja deletar esse artigo?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final DatabaseReference reference = ConfiguraFirebase.getNo("artigos");
                        reference.child(artigos.get(position).getId()).removeValue();
                        artigos.remove(position);
                        notifyItemRemoved(position);

                    }
                }).setNegativeButton("Não", null).show();
    }

    @Override
    public int getItemCount() {
        //retorna a quantidade de itens que será exibida
        return artigos != null ? artigos.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        ImageButton btnDelete;
        ImageButton btnEdit;

        public MyViewHolder(View itemView) {
            super(itemView);
            //passa uma referência para os componentes que estão na interface
            titulo = itemView.findViewById(R.id.textViewTItuloArtigoListagem);
            btnDelete = itemView.findViewById(R.id.botaoDeletarArtigo);
            btnEdit = itemView.findViewById(R.id.botaoEditarArtigo);
        }
    }


}
