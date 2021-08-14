package com.example.bestanswernovaversao.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.bestanswernovaversao.R;
import com.google.android.material.snackbar.Snackbar;

public class ConvidarAmigoFragment extends Fragment {

    private Button botaoConvidar;

    private EditText emailAmigo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_convidar_amigo, container, false);
        emailAmigo = view.findViewById(R.id.emailAmigo);
        botaoConvidar = view.findViewById(R.id.botaoConvidarAmigo);
        botaoConvidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirEnviarEmail(view, emailAmigo);
            }
        });

        return view;
    }

    public void abrirEnviarEmail(View view, EditText emailAmigo) {
        String emailString = emailAmigo.getText().toString();
        if (emailString == null || emailString.isEmpty()) {
            informarUsuarioCampoEmBranco(view);
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailString});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Baixe o aplicativo Best Answer!");
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void informarUsuarioCampoEmBranco(View view) {
        Snackbar snackbar = Snackbar.make(view, "Digite o email do amigo que deseja convidar!", Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}