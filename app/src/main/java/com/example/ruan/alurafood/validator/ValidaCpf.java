package com.example.ruan.alurafood.validator;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class ValidaCpf {

    private static final String CPF_INVALIDO = "CPF inválido";
    public static final String DEVE_TER_ONZE_DIGITOS = "O CPF precisa ter 11 dígitos";
    private final TextInputLayout textInputCpf;
    private final EditText campoCpf;
    private final ValidacaoPadrao validacaoPadrao;
    private final CPFFormatter formatador;

    public ValidaCpf(TextInputLayout textInputCpf) {
        this.textInputCpf = textInputCpf;
        this.campoCpf = this.textInputCpf.getEditText();
        this.validacaoPadrao = new ValidacaoPadrao(textInputCpf);
        this.formatador = new CPFFormatter();
    }

    public boolean estaValido(){
        if ( !validacaoPadrao.estaValido() ) return false;
        String cpf = getCpf();
        if ( !validaCampoComOnzeDigitos(cpf) ) return false;
        if ( !validaCalculoCpf(cpf) ) return false;

        adicionaFormatacao(cpf);
        return true;
    }

    private void adicionaFormatacao(String cpf) {
        String cpfFormatado = formatador.format(cpf);
        campoCpf.setText(cpfFormatado);
    }

    private boolean validaCalculoCpf(String cpf) {
        try {
//                        4Devs => gerador de cpfs válidos para teste
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.assertValid(cpf);
        }catch (InvalidStateException e){
            textInputCpf.setError(CPF_INVALIDO);
            return false;
        }
        return true;
    }

    @NonNull
    private String getCpf() {
        return campoCpf.getText().toString();
    }


    private boolean validaCampoComOnzeDigitos(String cpf) {
        if (cpf.length() != 11){
            textInputCpf.setError(DEVE_TER_ONZE_DIGITOS);
            return false;
        }
        return true;
    }
}
