package com.example.bestanswernovaversao.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.bestanswernovaversao.R;
import com.example.bestanswernovaversao.dao.PerguntaRepository;
import com.example.bestanswernovaversao.domain.Categoria;
import com.example.bestanswernovaversao.model.Pergunta;
import com.google.android.material.textfield.TextInputEditText;

public class EditarPerguntaFragment extends Fragment {

    private Button botaoEditar;

    private TextInputEditText txtTitulo;

    private TextInputEditText txtDescricao;

    private Spinner spinnerCategoria;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_editar_pergunta, container, false);

        Bundle bundle = getArguments();
        String titulo = bundle.getString("TITULO");
        txtTitulo = view.findViewById(R.id.textTituloPerguntaPaginaEditar);
        txtTitulo.setText(titulo);
        txtDescricao = view.findViewById(R.id.textDescricaoPerguntaPaginaEditar);
        String descricao = bundle.getString("DESCRICAO");
        txtDescricao.setText(descricao);
        spinnerCategoria = view.findViewById(R.id.textCategoriaPerguntaPaginaEditar);
        String categoria = bundle.getString("CATEGORIA");
        spinnerCategoria.setSelection(Categoria.getIndiceByNome(categoria));
        String data = bundle.getString("DATA");
        int idPergunta = bundle.getInt("ID");
        botaoEditar = view.findViewById(R.id.botaoEditar);

        botaoEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pergunta pergunta = new Pergunta(idPergunta, txtTitulo.getText().toString(), txtDescricao.getText().toString(), spinnerCategoria.getSelectedItem().toString(), data);
                PerguntaRepository perguntaRepository = new PerguntaRepository(getActivity().getBaseContext());
                perguntaRepository.update(pergunta);
                Toast.makeText(getActivity().getApplicationContext(), "Pergunta editada!", Toast.LENGTH_LONG).show();
                Navigation.createNavigateOnClickListener(R.id.nav_todas_perguntas, null);
            }
        });

        return view;
    }
}