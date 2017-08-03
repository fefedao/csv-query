package br.com.teste.csvquery.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fefedo on 02/08/17.
 */
public class ComandoResolver {

    public static final String REGEX_GRUPO_PALAVRAS_COMANDO = "(\\w+)\\s(\\w+)\\s(\\w+)";
    public static final Pattern PATTERN = Pattern.compile(REGEX_GRUPO_PALAVRAS_COMANDO);

    public static Comando interpretarComando(String comando){
        if (comando.equals(Comando.COUNT_TOTAL.getComando())) {
            return Comando.COUNT_TOTAL;
        } else if (comando.contains(Comando.COUNT_DISTINCT.getComando())) {
            return Comando.COUNT_DISTINCT;
        } else if (comando.contains(Comando.FILTER.getComando())) {
            return Comando.FILTER;
        } else if (comando.contains(Comando.SAIR.getComando())) {
            return Comando.SAIR;
        }
        return Comando.ERRO;
    }

    public static Matcher getComandoMatch(String comando){
        Matcher matcher = PATTERN.matcher(comando);
        return matcher;
    }
}
