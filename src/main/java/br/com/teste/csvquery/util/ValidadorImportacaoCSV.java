package br.com.teste.csvquery.util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by fefedo on 02/08/17.
 */
public class ValidadorImportacaoCSV{

    public static String validar(Scanner scan){
        boolean arquivoEncontrado = false;
        String pathParaArquivo = null;
        while (!arquivoEncontrado) {
            System.out.println("Informe o caminho para o arquivo .csv, ou digite sair para finalizar o aplicativo: ");
            pathParaArquivo = scan.nextLine();
            if (pathParaArquivo.equals(Comando.SAIR.getComando())) {
                System.out.println("Terminando execucao.");
                System.exit(0);
            } else if (Files.isDirectory(Paths.get(pathParaArquivo))) {
                System.out.println("O caminho informado se trata de um diretorio.");
            } else if (!(pathParaArquivo.endsWith(".csv") || pathParaArquivo.endsWith(".CSV"))){
                System.out.println("Arquivo com formato invalido.");
            } else if (Files.isReadable(Paths.get(pathParaArquivo))) {
                arquivoEncontrado = true;
            } else {
                System.out.println("Arquivo nao encontrado ou sem permissao para leitura.");
                continue;
            }
        }
        System.out.println("Arquivo encontrado em " + pathParaArquivo);
        return pathParaArquivo;
    }
}
