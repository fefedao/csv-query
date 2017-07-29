package br.com.teste.csvquery;

import com.sun.deploy.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by fefedo on 27/07/17.
 */
public class CsvQuery {

    public static final String DEFAULT_CSV_FILE = "cidades.csv";
    public static final String DEFAULT_SEPARATOR = ",";
    public static List<Municipio> municipios;

    //TODO Adicionar recebimento de arquivo por path
    public static void main (String []args) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(CsvQuery.class.getResource("/"+DEFAULT_CSV_FILE).getPath()))) {
            municipios = new ArrayList<>();
            stream.skip(1).forEach(s -> {
                String[] splitDados = s.split(DEFAULT_SEPARATOR);
                municipios.add(new Municipio(splitDados[0], splitDados[1], splitDados[2], splitDados[3], splitDados[4], splitDados[5]
                        , splitDados[6], splitDados[7], splitDados[8], splitDados[9]));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("===============================================");
        System.out.println("Importador CSV");
        System.out.println("===============================================");
        System.out.println("Digite o comando de consulta: ");
        Scanner scan = new Scanner(System.in);
        String comando = scan.nextLine();

        if (comando.equals("count *")){
            System.out.println("Quantidade de registros encontrados: " + municipios.size());
        }

        if (comando.contains("count distinct ")){
            FileInputStream inputStream = null;
            Scanner scanner = null;
            try {
                Pattern pattern = Pattern.compile("(\\w+)\\s(\\w+)\\s(\\w+)");
                Matcher matcher = pattern.matcher(comando);
                if (matcher.matches()){
                    String campoConsultado = matcher.group(3);
                    inputStream  = new FileInputStream(CsvQuery.class.getResource("/"+DEFAULT_CSV_FILE).getPath());
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
    }


}
