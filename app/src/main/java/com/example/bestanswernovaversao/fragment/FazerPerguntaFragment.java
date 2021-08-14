package com.example.bestanswernovaversao.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.bestanswernovaversao.R;
import com.example.bestanswernovaversao.dao.PerguntaRepository;
import com.example.bestanswernovaversao.model.Pergunta;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class FazerPerguntaFragment extends Fragment {

    private Button botaoPerguntar;

    private EditText titulo;

    private EditText descricao;

    private Spinner categoria;

    private TextInputLayout layoutTitulo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fazer_pergunta, container, false);

        botaoPerguntar = view.findViewById(R.id.botaoPerguntar);

        titulo = view.findViewById(R.id.txtTituloEdit);
        descricao = view.findViewById((R.id.txtDescricaoEdit));
        categoria = view.findViewById(R.id.txtCategoriaEdit);
        layoutTitulo = view.findViewById(R.id.textViewTitulo);

        botaoPerguntar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerguntaRepository perguntaRepository = new PerguntaRepository(getActivity().getBaseContext());
                if (!validarCamposObrigatorios(titulo.getText().toString())) {
                    return;
                }
                String resultado = perguntaRepository.insert(new Pergunta(-1, titulo.getText().toString(), descricao.getText().toString(), categoria.getSelectedItem().toString(), Calendar.getInstance().toString()));
                Toast.makeText(getActivity().getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    private boolean validarCamposObrigatorios(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            layoutTitulo.setErrorEnabled(true);
            layoutTitulo.setError("Informe o título da pergunta");
            return false;
        } else {
            layoutTitulo.setErrorEnabled(false);
        }
        return true;
    }


}