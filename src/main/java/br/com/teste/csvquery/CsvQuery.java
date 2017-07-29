package br.com.teste.csvquery;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
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

    public static void main (String []args) throws IOException {
        System.out.println("===============================================");
        System.out.println("Importador CSV");
        System.out.println("-----------------------------------------------");
        System.out.println("Comandos aceitos: ");
        System.out.println("count * - escreve no console a contagem total de registros importados (não deve considerar a linha de cabeçalho");
        System.out.println("count distinct [propriedade] - escreve no console o total de valores distintos da propriedade (coluna) enviada");
        System.out.println("filter [propriedade] [valor] - escreve no console a linha de cabeçalho e todas as linhas em que a propriedade enviada ");
        System.out.println("===============================================");
        System.out.println("Digite o comando de consulta: ");
        Scanner scan = new Scanner(System.in);
        String comando = scan.nextLine();

        if (comando.equals("count *")){
            try (Stream<String> lines = Files.lines(Paths.get(CsvQuery.class.getResource("/"+DEFAULT_CSV_FILE).getPath()))) {
                System.out.println("Quantidade de registros encontrados: " + String.valueOf(lines.count() - 1));
            }
        }

        if (comando.contains("count distinct ")){
            FileInputStream inputStream = null;
            Scanner scanner = null;
            try {
                Pattern pattern = Pattern.compile("(\\w+)\\s(\\w+)\\s(\\w+)");
                Matcher matcher = pattern.matcher(comando);
                if (matcher.matches()){
                    String campoConsultado = matcher.group(3);
                    inputStream  = new FileInputStream(DEFAULT_FILE);
                    scanner = new Scanner(inputStream, "UTF-8");
                    String linhaColunas = scanner.nextLine();
                    String[] colunas = linhaColunas.split(DEFAULT_SEPARATOR);
                    Integer indiceColuna = IntStream.range(0, colunas.length).filter(i -> campoConsultado.equals(colunas[i])).findFirst().getAsInt();
                    Set<String> valoresUnicos = new HashSet<>();
                    while (scanner.hasNextLine()) {
                        valoresUnicos.add(scanner.nextLine().split(DEFAULT_SEPARATOR)[indiceColuna]);
                    }
                    System.out.println("Quantidade de registros encontrados: " + valoresUnicos.size());
                    if (scanner.ioException() != null) {
                        throw scanner.ioException();
                    }
                }
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (scanner != null) {
                    scanner.close();
                }
            }
        }

        if (comando.contains("filter ")){
            FileInputStream inputStream = null;
            Scanner scanner = null;
            try {
                Pattern pattern = Pattern.compile(REGEX_GRUPO_PALAVRAS_COMANDO);
                Matcher matcher = pattern.matcher(comando);
                if (matcher.matches()){
                    String campoConsultado = matcher.group(2);
                    String valorConsultado = matcher.group(3);
                    inputStream  = new FileInputStream(CsvQuery.class.getResource("/"+DEFAULT_CSV_FILE).getPath());
                    scanner = new Scanner(inputStream, "UTF-8");
                    String linhaColunas = scanner.nextLine();
                    System.out.println(linhaColunas);

                    String[] colunas = linhaColunas.split(DEFAULT_SEPARATOR);
                    Integer indiceColuna = IntStream.range(0, colunas.length).filter(i -> campoConsultado.equals(colunas[i])).findFirst().getAsInt();

                    while (scanner.hasNextLine()) {
                        String linha = scanner.nextLine();
                        if (linha.split(DEFAULT_SEPARATOR)[indiceColuna].equals(valorConsultado)){
                            System.out.println(linha);
                        }
                    }

                    if (scanner.ioException() != null) {
                        throw scanner.ioException();
                    }
                }
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (scanner != null) {
                    scanner.close();
                }
            }
        }
    }


}
