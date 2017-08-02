package br.com.teste.csvquery.main;

import br.com.teste.csvquery.service.MunicipioService;
import br.com.teste.csvquery.service.impl.MunicipioServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fefedo on 27/07/17.
 */
public class Main {

    public static final String REGEX_GRUPO_PALAVRAS_COMANDO = "(\\w+)\\s(\\w+)\\s(\\w+)";

    public static void main(String[] args) throws IOException {
        System.out.println("===============================================");
        System.out.println("Importador Municipios");
        System.out.println("===============================================");
        Scanner scan = new Scanner(System.in);

        String pathParaArquivo = null;
        boolean arquivoEncontrado = false;
        while (!arquivoEncontrado) {
            System.out.println("Informe o caminho para o arquivo .csv, ou digite sair para finalizar o aplicativo: ");
            pathParaArquivo = scan.nextLine();

            if (pathParaArquivo.equals("sair")){
            } else if (Files.isDirectory(Paths.get(pathParaArquivo))) {
                System.out.println("O caminho informado se trata de um diretorio.");
            } else if (!(pathParaArquivo.endsWith(".csv") || pathParaArquivo.endsWith(".CSV"))){
                System.out.println("Arquivo com formato invalido.");
            } else if (Files.isReadable(Paths.get(pathParaArquivo))) {
                arquivoEncontrado = true;
            } else {
                System.out.println("Arquivo nao encontrado ou sem permissao para leitura.");
            }
        }

        System.out.println("Arquivo encontrado em " + pathParaArquivo);
        MunicipioService municipioService = new MunicipioServiceImpl();
        municipioService.defineRecurso(pathParaArquivo);

        boolean continuar = true;
        while (continuar) {
            System.out.println("===============================================");
            System.out.println("Comandos aceitos: ");
            System.out.println("count * - escreve no console a contagem total de registros importados.");
            System.out.println("count distinct [propriedade] - escreve no console o total de valores distintos da propriedade (coluna) enviada.");
            System.out.println("filter [propriedade] [valor] - escreve no console a linha de cabeçalho e todas as linhas em que a propriedade enviada.");
            System.out.println("sair - finaliza o aplicativo.");
            System.out.println("===============================================");
            System.out.println("Digite o comando de consulta:");

            String comando = scan.nextLine();
            if (comando.equals("count *")) {
                municipioService.countTotal();
            } else if (comando.contains("count distinct ")) {
                Pattern pattern = Pattern.compile(REGEX_GRUPO_PALAVRAS_COMANDO);
                Matcher matcher = pattern.matcher(comando);
                if (matcher.matches()) {
                    String campoConsultado = matcher.group(3);
                    municipioService.countTotal(campoConsultado);
                }

            } else if (comando.contains("filter ")) {
                Pattern pattern = Pattern.compile(REGEX_GRUPO_PALAVRAS_COMANDO);
                Matcher matcher = pattern.matcher(comando);
                if (matcher.matches()) {
                    String campoConsultado = matcher.group(2);
                    String valorConsultado = matcher.group(3);
                    municipioService.consultaPorFiltro(campoConsultado, valorConsultado);
                }
            } else if (comando.contains("sair")) {
                System.out.println("Terminando execucao.");
                continuar = false;
            } else {
                System.out.println("Comando desconhecido.");
            }
        }
    }
}
