package com.example.ruan.alurafood.validator;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import com.example.ruan.alurafood.formatter.FormataTelefoneComDdd;

public class ValidaTelefoneComDdd {

    private static final String DEVE_TER_10_A_11_DIGITOS = "Telefone deve ter entre 10 a 11 dígitos";
    private final TextInputLayout textInputTelefoneComDdd;
    private ValidacaoPadrao validacaoPadrao;
    private EditText campoTelefoneComDdd;
    private final FormataTelefoneComDdd formatador;

    public ValidaTelefoneComDdd(TextInputLayout textInputTelefoneComDdd) {
        this.textInputTelefoneComDdd = textInputTelefoneComDdd;
        this.validacaoPadrao = new ValidacaoPadrao(textInputTelefoneComDdd);
        this.campoTelefoneComDdd = textInputTelefoneComDdd.getEditText();
        this.formatador = new FormataTelefoneComDdd();
    }

    private boolean validaEntreDezOuOnzeDigitos(String telefoneComDdd){
        int digitos = telefoneComDdd.length();
        if ( digitos < 10 || digitos > 11 ){
            textInputTelefoneComDdd.setError(DEVE_TER_10_A_11_DIGITOS);
            return false;
        }
        return true;

    }

    public boolean estaValido(){
        if ( !validacaoPadrao.estaValido() ) return false;
        String telefoneComDdd = campoTelefoneComDdd.getText().toString();
        if ( !validaEntreDezOuOnzeDigitos(telefoneComDdd) ) return false;
        adicionaFormatacao(telefoneComDdd);
        return true;
    }

    private void adicionaFormatacao(String telefoneComDdd) {
        String telefoneComDDDFormatado = formatador.formata(telefoneComDdd);
        campoTelefoneComDdd.setText(telefoneComDDDFormatado);
    }


}
