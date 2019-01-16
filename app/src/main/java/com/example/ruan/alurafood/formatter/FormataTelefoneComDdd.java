package com.example.ruan.alurafood.formatter;

import android.support.annotation.NonNull;

public class FormataTelefoneComDdd {

    @NonNull
    public String formata(String telefoneComDdd) {
        //        https://www.regextester.com/  => verifica expressões regulares

//        StringBuilder sb = new StringBuilder();
//        int digitos = telefoneComDdd.length();
//        for (int i = 0; i < digitos; i++) {
//            if (i == 0){
//                sb.append("(");
//            }
//            char digito = telefoneComDdd.charAt(i);
//            sb.append(digito);
//            if (i == 1){
//                sb.append(") ");
//            }
//            if (digitos == 10 && i == 5 || digitos == 11 && i == 6){
//                sb.append("-");
//            }
//        }
//        String telefoneComDDDFormatado = sb.toString();
        return telefoneComDdd.replaceAll("([0-9]{2})([0-9]{4,5})([0-9]{4})", "($1) $2-$3");
    }

    @NonNull
    public String remove(String telefoneComDdd) {
        return telefoneComDdd
                .replace("(", "")
                .replace(")", "")
                .replace(" ", "")
                .replace("-", "");
    }
}
