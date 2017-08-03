package br.com.teste.csvquery.dao.impl;

import br.com.teste.csvquery.dao.MunicipioDAO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by fefedo on 01/08/17.
 */
public class MunicipioCsvDAOImpl extends AbstractDAO implements MunicipioDAO  {

    private static MunicipioCsvDAOImpl municipioCsvDAOImpl = new MunicipioCsvDAOImpl();
    public static MunicipioCsvDAOImpl getInstance() {
        return municipioCsvDAOImpl;
    }
    private MunicipioCsvDAOImpl() {
    }

    private static final String DEFAULT_SEPARATOR = ",";

    @Override
    public String[] consultarColunas() {
        try {
            String[] colunas = Files.lines(Paths.get((String) dataSource)).findFirst().get().split(DEFAULT_SEPARATOR);
            return colunas;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String countTotal() {
        return countTotal(null);
    }

    @Override
    public String countTotal(String colunaConsultada) {
        try {
            Stream<String> linhas = Files.lines(Paths.get((String) dataSource));
            if (colunaConsultada == null) {
                String s = String.valueOf(linhas.count() - 1);
                return s;
            }
            String[] colunas = consultarColunas();
            Integer indiceColuna = IntStream.range(0, colunas.length).filter(i -> colunaConsultada.equals(colunas[i])).findFirst().getAsInt();
            Set<String> valoresUnicos = new HashSet<>();
            linhas.skip(1).forEach(s -> valoresUnicos.add(s.split(DEFAULT_SEPARATOR)[indiceColuna]));
            return String.valueOf(valoresUnicos.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer indiceColunaConsultada(String colunaConsultada) {
        String[] colunas = consultarColunas();
        return IntStream.range(0, colunas.length).filter(i -> colunaConsultada.equals(colunas[i])).findFirst().getAsInt();
    }

    @Override
    public Stream<String> consultaPorFiltro(String colunaConsultada, String filtro) {
        try {
            return Files.lines(Paths.get((String) dataSource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setDataDource(Object dataSource) {
        setDataSource(dataSource);
    }
}
