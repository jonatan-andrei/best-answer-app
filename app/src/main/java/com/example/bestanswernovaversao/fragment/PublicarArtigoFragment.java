package com.example.bestanswernovaversao.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.bestanswernovaversao.R;
import com.example.bestanswernovaversao.model.Artigo;
import com.example.bestanswernovaversao.util.ConfiguraFirebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;

public class PublicarArtigoFragment extends Fragment {

    private DatabaseReference reference = ConfiguraFirebase.getNoRaiz();

    private EditText tituloEdit;

    private EditText conteudoEdit;

    private Button botaoCadastrarArtigo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_publicar_artigo, container, false);

        tituloEdit = view.findViewById(R.id.txtTituloArtigoEdit);
        conteudoEdit = view.findViewById(R.id.txtConteudoArtigoEdit);

        botaoCadastrarArtigo = view.findViewById(R.id.botaoPublicarArtigo);
        botaoCadastrarArtigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarArtigo(view);
            }
        });

        return view;
    }

    private void cadastrarArtigo(View view) {
        String titulo = tituloEdit.getText().toString();
        String conteudo = conteudoEdit.getText().toString();
        Artigo artigo = new Artigo(null, titulo, conteudo);
        DatabaseReference artigos = reference.child("artigos");
        artigos.push().setValue(artigo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(view.getContext(), "Artigo publicado com sucesso!", Toast.LENGTH_SHORT).show();
                limparCampos(view);
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(view.getContext(), "Erro ao publicar artigo!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void limparCampos(View view) {
        tituloEdit.setText("");
        conteudoEdit.setText("");
    }
}