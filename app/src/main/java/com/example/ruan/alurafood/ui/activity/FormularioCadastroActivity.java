package com.example.ruan.alurafood.ui.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ruan.alurafood.R;
import com.example.ruan.alurafood.formatter.FormataTelefoneComDdd;
import com.example.ruan.alurafood.validator.ValidaCpf;
import com.example.ruan.alurafood.validator.ValidaEmail;
import com.example.ruan.alurafood.validator.ValidaTelefoneComDdd;
import com.example.ruan.alurafood.validator.ValidacaoPadrao;
import com.example.ruan.alurafood.validator.Validador;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.stella.format.CPFFormatter;

public class FormularioCadastroActivity extends AppCompatActivity {

    private static final String ERRO_FORMATACAO_CPF = "erro formatação cpf";
    private final List<Validador> validadores = new ArrayList<>();

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
        configuraBotaoCadastrar();
    }

    private void configuraBotaoCadastrar() {
        Button botaoCadastrar = findViewById(R.id.formulario_cadastro_botao_cadastrar);
        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean formularioEstaValido = validaTodosCampos();
                if (formularioEstaValido){
                    cadastroRealizado();
                }
            }
        });
    }

    private void cadastroRealizado() {
        Toast.makeText(
                FormularioCadastroActivity.this,
                "Cadastro realizado com sucesso!",
                Toast.LENGTH_SHORT).show();
    }

    private boolean validaTodosCampos() {
        boolean formularioEstaValido = true;
        for (Validador validador : validadores
             ) {
            if ( !validador.estaValido() ){
                formularioEstaValido = false;
            }
        }
        return formularioEstaValido;
    }

    private void configuraCampoSenha() {
        TextInputLayout textInputSenha =
                findViewById(R.id.formulario_cadastro_campo_senha);
        adicionaValidacaoPadrao(textInputSenha);
    }

    private void configuraCampoEmail() {
        TextInputLayout textInputEmail =
                findViewById(R.id.formulario_cadastro_campo_email);
        EditText campoEmail = textInputEmail.getEditText();
        final ValidaEmail validaEmail = new ValidaEmail(textInputEmail);
        validadores.add(validaEmail);
        campoEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if ( !hasFocus ){
                    validaEmail.estaValido();
                }
            }
        });
//        adicionaValidacaoPadrao(textInputEmail);
    }

    private void configuraCampoTelefoneDDD() {
        TextInputLayout textInputTelefoneComDDD =
                findViewById(R.id.formulario_cadastro_campo_telefone_com_ddd);
        final EditText campoTelefoneComDdd = textInputTelefoneComDDD.getEditText();
        final ValidaTelefoneComDdd validaTelefoneComDdd = new ValidaTelefoneComDdd(textInputTelefoneComDDD);
        validadores.add(validaTelefoneComDdd);
        final FormataTelefoneComDdd formatador = new FormataTelefoneComDdd();
        campoTelefoneComDdd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String telefoneComDdd = campoTelefoneComDdd.getText().toString();
                if ( hasFocus ){
                    String telefoneComDddSemFormatacao = formatador.remove(telefoneComDdd);
                    campoTelefoneComDdd.setText(telefoneComDddSemFormatacao);
                }else {
                    validaTelefoneComDdd.estaValido();
                }
            }
        });
//        adicionaValidacaoPadrao(textInputTelefoneComDDD);
    }

    private void configuraCampoCpf() {
        TextInputLayout textInputCpf =
                findViewById(R.id.formulario_cadastro_campo_cpf);

        final EditText campoCpf = textInputCpf.getEditText();
        final CPFFormatter formatador = new CPFFormatter();
        final ValidaCpf validaCpf = new ValidaCpf(textInputCpf);
        validadores.add(validaCpf);
        campoCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    removeFormatacao(formatador, campoCpf);
                }else {
                    //se estiver saindo do focus
                    validaCpf.estaValido();
                }
            }
        });
    }

    private void removeFormatacao(CPFFormatter formatador, EditText campoCpf) {
        String cpf = campoCpf.getText().toString();
        try {
            String cpfSemFormato = formatador.unformat(cpf);
            campoCpf.setText(cpfSemFormato);
        }catch (IllegalArgumentException e){
            Log.e(ERRO_FORMATACAO_CPF, e.getMessage());
        }
    }


    private void configuraCampoNomeCompleto() {
        TextInputLayout textInputNomeCompleto =
                findViewById(R.id.formulario_cadastro_campo_nome_completo);
        adicionaValidacaoPadrao(textInputNomeCompleto);
    }

    private void adicionaValidacaoPadrao(final TextInputLayout textInputCampo){
        final EditText campo = textInputCampo.getEditText();
        final ValidacaoPadrao validacaoPadrao = new ValidacaoPadrao(textInputCampo);
        validadores.add(validacaoPadrao);
        campo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    validacaoPadrao.estaValido();
                }
            }
        });
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
