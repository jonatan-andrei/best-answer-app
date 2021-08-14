package com.example.bestanswernovaversao.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.bestanswernovaversao.R;
import com.example.bestanswernovaversao.model.Artigo;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditarArtigoFragment extends Fragment implements Button.OnClickListener {

    Button botaoEditar;
    String chave;
    TextInputEditText txtTitulo;
    TextInputEditText txtConteudo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editar_artigo, container, false);
        Bundle bundle = getArguments();
        String titulo = bundle.getString("titulo");
        txtTitulo = view.findViewById(R.id.txtTituloArtigoEditPaginaEdicao);
        txtTitulo.setText(titulo);
        String conteudo = bundle.getString("conteudo");
        txtConteudo = view.findViewById(R.id.txtConteudoArtigoEditPaginaEdicao);
        txtConteudo.setText(conteudo);
        chave = bundle.getString("chave");
        botaoEditar = view.findViewById(R.id.botaoEditarArtigoPaginaEdicao);
        botaoEditar.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        new AlertDialog.Builder(view.getContext())
                .setTitle("Editando artigo")
                .setMessage("Tem certeza que deseja editar esse artigo?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("artigos").child(chave);
                        Artigo artigo = new Artigo(null, txtTitulo.getText().toString(), txtConteudo.getText().toString());
                        reference.setValue(artigo);
                        Toast.makeText(view.getContext(), "Artigo editado!", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Não", null).show();
    }
}