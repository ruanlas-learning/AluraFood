package com.example.ruan.alurafood.ui.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.ruan.alurafood.R;

public class FormularioCadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cadastro);

        inicializaCampos();
    }

    private void inicializaCampos() {
        configuraCampoNomeCompleto();
        configuraCampoCpf();
        configuraCampoTelefoneDDD();
        configuraCampoEmail();
        configuraCampoSenha();
    }

    private void configuraCampoSenha() {
        TextInputLayout textInputSenha =
                findViewById(R.id.formulario_cadastro_campo_senha);
        adicionaValidacaoPadrao(textInputSenha);
    }

    private void configuraCampoEmail() {
        TextInputLayout textInputEmail =
                findViewById(R.id.formulario_cadastro_campo_email);
        adicionaValidacaoPadrao(textInputEmail);
    }

    private void configuraCampoTelefoneDDD() {
        TextInputLayout textInputTelefoneComDDD =
                findViewById(R.id.formulario_cadastro_campo_telefone_com_ddd);
        adicionaValidacaoPadrao(textInputTelefoneComDDD);
    }

    private void configuraCampoCpf() {
        TextInputLayout textInputCpf =
                findViewById(R.id.formulario_cadastro_campo_cpf);
        adicionaValidacaoPadrao(textInputCpf);
    }

    private void configuraCampoNomeCompleto() {
        TextInputLayout textInputNomeCompleto =
                findViewById(R.id.formulario_cadastro_campo_nome_completo);
        adicionaValidacaoPadrao(textInputNomeCompleto);
    }

    private void adicionaValidacaoPadrao(final TextInputLayout textInputCampo){
        final EditText campo = textInputCampo.getEditText();
        campo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String texto = campo.getText().toString();
                if (!hasFocus){
                    validaCampoObrigatorio(texto, textInputCampo);
                }
            }
        });
    }

    private void validaCampoObrigatorio(String texto, TextInputLayout textInputCampo) {
        if (texto.isEmpty()){
            textInputCampo.setError("Campo obrigatório");
        }else {
            textInputCampo.setError(null);
            textInputCampo.setErrorEnabled(false);
        }
    }

//    private void adicionaValidacaoPadrao(final EditText campoEditText) {
//        campoEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                String texto = campoEditText.getText().toString();
//                if (!hasFocus){
//                    if (texto.isEmpty()){
//                        campoEditText.setError("Campo obrigatório");
//                    }
//                }
//            }
//        });
//    }
}
