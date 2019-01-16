package com.example.ruan.alurafood.ui.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.ruan.alurafood.R;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

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
        final TextInputLayout textInputCpf =
                findViewById(R.id.formulario_cadastro_campo_cpf);
//        adicionaValidacaoPadrao(textInputCpf);
        final EditText campoCpf = textInputCpf.getEditText();
        final CPFFormatter cpfFormatter = new CPFFormatter();
        campoCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String cpf = campoCpf.getText().toString();
                if (!hasFocus){
                    if ( !validaCampoObrigatorio(cpf, textInputCpf) ) return;
                    if ( !validaCampoComOnzeDigitos(cpf, textInputCpf) ) return;
                    if ( !validaCalculoCpf(cpf, textInputCpf)) return;

                    removeErro(textInputCpf);


                    String cpfFormatado = cpfFormatter.format(cpf);
                    campoCpf.setText(cpfFormatado);
                }else {
                    try {
                        String cpfSemFormato = cpfFormatter.unformat(cpf);
                        campoCpf.setText(cpfSemFormato);
                    }catch (IllegalArgumentException e){
                        Log.e("erro formatação cpf", e.getMessage());
                    }
                }
            }
        });
    }

    private boolean validaCalculoCpf(String cpf, TextInputLayout textInputCpf) {
        try {
//                        4Devs => gerador de cpfs válidos para teste
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.assertValid(cpf);
        }catch (InvalidStateException e){
            textInputCpf.setError("CPF inválido");
            return false;
        }
        return true;
    }

    private void removeErro(TextInputLayout textInputCampo) {
        textInputCampo.setError(null);
        textInputCampo.setErrorEnabled(false);
    }

    private boolean validaCampoComOnzeDigitos(String cpf, TextInputLayout textInputCpf) {
        if (cpf.length() != 11){
            textInputCpf.setError("O CPF precisa ter 11 dígitos");
            return false;
        }
        return true;
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
                    if ( !validaCampoObrigatorio(texto, textInputCampo) ) return;

                    removeErro(textInputCampo);
                }
            }
        });
    }

    private boolean validaCampoObrigatorio(String texto, TextInputLayout textInputCampo) {
        if (texto.isEmpty()){
            textInputCampo.setError("Campo obrigatório");
            return false;
        }
        return true;
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
