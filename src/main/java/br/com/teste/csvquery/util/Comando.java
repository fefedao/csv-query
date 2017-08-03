package br.com.teste.csvquery.util;

/**
 * Created by fefedo on 02/08/17.
 */
public enum Comando {

    COUNT_TOTAL("count *"),
    COUNT_DISTINCT("count distinct"),
    FILTER("filter"),
    ERRO("erro"),
    SAIR("sair");

    private String comando;

    Comando(String comando) {
        this.comando = comando;
    }

    public String getComando(){
        return comando;
    }
}
