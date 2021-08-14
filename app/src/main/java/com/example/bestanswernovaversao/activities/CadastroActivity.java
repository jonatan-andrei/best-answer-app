package com.example.bestanswernovaversao.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bestanswernovaversao.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextInputEditText edtEmail;
    private TextInputEditText edtSenha;
    private TextInputEditText edtConfSenha;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtEmail = findViewById(R.id.txtEmailCadastroEdit);
        edtSenha = findViewById(R.id.txtSenhaCadastroEdit);
        edtConfSenha = findViewById(R.id.txtConfirmarSenhaCadastroEdit);
        btnCadastrar = findViewById(R.id.botaoCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals("") && !edtConfSenha.getText().toString().equals("")) {
                    if (edtSenha.getText().length() <= 5) {
                        Toast.makeText(CadastroActivity.this, "Senha deve ter no mínimo 6 caracteres!", Toast.LENGTH_SHORT).show();
                    } else if (!edtEmail.getText().toString().contains("@") || !edtEmail.getText().toString().contains(".")) {
                        Toast.makeText(CadastroActivity.this, "Email em formato inválido!", Toast.LENGTH_SHORT).show();
                    } else if (edtSenha.getText().toString().equals(edtConfSenha.getText().toString())) {
                        mAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(), edtSenha.getText().toString()).
                                addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                            Toast.makeText(CadastroActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(CadastroActivity.this, "Erro ao cadastrar usuário!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(CadastroActivity.this, "Senha e confirmação de senha devem ser iguais!", Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(CadastroActivity.this, "Informe os dados para o cadastro!", Toast.LENGTH_SHORT).show();
            }
        });

        mAuth = FirebaseAuth.getInstance();
    }
}