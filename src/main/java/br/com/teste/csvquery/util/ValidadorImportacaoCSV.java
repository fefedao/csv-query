package br.com.teste.csvquery.util;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by fefedo on 02/08/17.
 */
public class ValidadorImportacaoCSV{

    public static String validar(String pathParaArquivo){
        if (pathParaArquivo.equals(Comando.SAIR.getComando())) {
            System.out.println("Terminando execucao.");
            return pathParaArquivo;
        } else if (Files.isDirectory(Paths.get(pathParaArquivo))) {
            System.out.println("O caminho informado se trata de um diretorio.");
            return null;
        } else if (!(pathParaArquivo.endsWith(".csv") || pathParaArquivo.endsWith(".CSV"))){
            System.out.println("Arquivo com formato invalido.");
            return null;
        } else if (Files.isReadable(Paths.get(pathParaArquivo))) {
            System.out.println("Arquivo encontrado em " + pathParaArquivo);
            return pathParaArquivo;
        } else {
            System.out.println("Arquivo nao encontrado ou sem permissao para leitura.");
            return null;
        }
    }
}
