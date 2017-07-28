package br.com.teste.csvquery;

import com.sun.deploy.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by fefedo on 27/07/17.
 */
public class CsvQuery {

    public static final String DEFAULT_CSV_FILE = "cidades.csv";
    public static final String DEFAULT_SEPARATOR = ",";

    //TODO Adicionar recebimento de arquivo por path
    public static void main (String []args){
        try (Stream<String> stream = Files.lines(Paths.get(CsvQuery.class.getResource("/"+DEFAULT_CSV_FILE).getPath()))) {
            List<Municipio> municipios = new ArrayList<>();
            stream.skip(1).forEach(s -> {
                String[] splitDados = StringUtils.splitString(s, DEFAULT_SEPARATOR);
                municipios.add(new Municipio(Integer.parseInt(splitDados[0]), splitDados[1], splitDados[2], splitDados[3], Long.parseLong(splitDados[4]), Long.parseLong(splitDados[5])
                        , splitDados[6], splitDados[7], splitDados[8], splitDados[9]));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
