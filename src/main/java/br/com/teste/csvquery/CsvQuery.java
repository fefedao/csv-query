package br.com.teste.csvquery;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by fefedo on 27/07/17.
 */
public class CsvQuery {

    public static final String DEFAULT_CSV_FILE = "cidades.csv";
    public static final String DEFAULT_FILE = CsvQuery.class.getResource("/" + DEFAULT_CSV_FILE).getPath();
    public static final String DEFAULT_SEPARATOR = ",";
    public static final String REGEX_GRUPO_PALAVRAS_COMANDO = "(\\w+)\\s(\\w+)\\s(\\w+)";

    public static void main(String[] args) throws IOException {
        System.out.println("===============================================");
        System.out.println("Importador CSV");
        System.out.println("-----------------------------------------------");
        System.out.println("Comandos aceitos: ");
        System.out.println("count * - escreve no console a contagem total de registros importados.");
        System.out.println("count distinct [propriedade] - escreve no console o total de valores distintos da propriedade (coluna) enviada");
        System.out.println("filter [propriedade] [valor] - escreve no console a linha de cabeçalho e todas as linhas em que a propriedade enviada");
        System.out.println("sair - finaliza o aplicativo");
        System.out.println("===============================================");
        Scanner scan = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Digite o comando de consulta:");
            String comando = scan.nextLine();
            if (comando.equals("count *")) {
                Stream<String> lines = Files.lines(Paths.get(DEFAULT_FILE));
                System.out.println("Quantidade de registros encontrados: " + String.valueOf(lines.count() - 1));

            } else if (comando.contains("count distinct ")) {
                Pattern pattern = Pattern.compile(REGEX_GRUPO_PALAVRAS_COMANDO);
                Matcher matcher = pattern.matcher(comando);
                if (matcher.matches()) {
                    String campoConsultado = matcher.group(3);
                    Stream<String> linhasColunas = Files.lines(Paths.get(DEFAULT_FILE));
                    String[] colunas = linhasColunas.findFirst().get().split(DEFAULT_SEPARATOR);
                    Integer indiceColuna = IntStream.range(0, colunas.length).filter(i -> campoConsultado.equals(colunas[i])).findFirst().getAsInt();
                    Set<String> valoresUnicos = new HashSet<>();
                    Stream<String> linhas = Files.lines(Paths.get(CsvQuery.class.getResource("/" + DEFAULT_CSV_FILE).getPath()));
                    linhas.skip(1).forEach(s -> valoresUnicos.add(s.split(DEFAULT_SEPARATOR)[indiceColuna]));
                    System.out.println("Quantidade de registros encontrados: " + (valoresUnicos.size()));
                }

            } else if (comando.contains("filter ")) {
                Pattern pattern = Pattern.compile(REGEX_GRUPO_PALAVRAS_COMANDO);
                Matcher matcher = pattern.matcher(comando);
                if (matcher.matches()) {
                    String campoConsultado = matcher.group(2);
                    String valorConsultado = matcher.group(3);
                    Stream<String> linhasColunas = Files.lines(Paths.get(DEFAULT_FILE));
                    String[] colunas = linhasColunas.findFirst().get().split(DEFAULT_SEPARATOR);
                    Integer indiceColuna = IntStream.range(0, colunas.length).filter(i -> campoConsultado.equals(colunas[i])).findFirst().getAsInt();
                    Stream<String> linhas = Files.lines(Paths.get(CsvQuery.class.getResource("/" + DEFAULT_CSV_FILE).getPath()));
                    linhas.skip(1).forEach(s -> {
                        boolean algumValorEncontrado = false;
                        String linha = s.split(DEFAULT_SEPARATOR)[indiceColuna];
                        if(linha.equals(valorConsultado)){
                            System.out.println(s);
                            algumValorEncontrado = true;
                        };
                        if (algumValorEncontrado){
                            System.out.println("Nenhum registro encontrado para pesquisa.");
                        }
                    });
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
