package com.example.bestanswernovaversao.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestanswernovaversao.R;
import com.example.bestanswernovaversao.adapter.RespostaAdapter;
import com.example.bestanswernovaversao.dao.RespostaRepository;
import com.example.bestanswernovaversao.model.Resposta;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.List;

public class ResponderPerguntaFragment extends Fragment {

    private TextView txtTitulo;

    private TextView txtDescricao;

    private TextInputEditText txtResposta;

    private TextInputLayout layoutResponder;

    private Button botaoResponder;

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_responder_pergunta, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewListarRespostas);

        Bundle bundle = getArguments();
        String titulo = bundle.getString("TITULO");
        txtTitulo = view.findViewById(R.id.textTituloPerguntaPaginaResponder);
        txtTitulo.setText(titulo);
        txtDescricao = view.findViewById(R.id.textDescricaoPerguntaPaginaResponder);
        String descricao = bundle.getString("DESCRICAO");
        txtDescricao.setText(descricao);
        int idPergunta = bundle.getInt("ID");
        txtResposta = view.findViewById(R.id.txtRespostaEdit);
        layoutResponder = view.findViewById(R.id.textViewResposta);
        botaoResponder = view.findViewById(R.id.botaoResponder);

        RespostaRepository respostaRepository = new RespostaRepository(getActivity().getBaseContext());
        List<Resposta> respostas = respostaRepository.findByPergunta(idPergunta);

        RespostaAdapter myAdapter = new RespostaAdapter(respostas);
        recyclerView.setAdapter(myAdapter);
        //linha de código usada para otimizar o recycler
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        //definindo o layout do recycler
        recyclerView.setLayoutManager(layoutManager);

        botaoResponder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validarCamposObrigatorios(txtResposta.getText().toString())) {
                    return;
                }
                String resultado = respostaRepository.insert(new Resposta(-1, idPergunta, txtResposta.getText().toString(), Calendar.getInstance().toString()));
                Toast.makeText(getActivity().getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    private boolean validarCamposObrigatorios(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            layoutResponder.setErrorEnabled(true);
            layoutResponder.setError("Informe sua resposta");
            return false;
        } else {
            layoutResponder.setErrorEnabled(false);
        }
        return true;
    }
}