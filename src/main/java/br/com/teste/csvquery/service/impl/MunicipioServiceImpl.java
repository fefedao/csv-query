package br.com.teste.csvquery.service.impl;

import br.com.teste.csvquery.dao.MunicipioDAO;
import br.com.teste.csvquery.dao.impl.MunicipioCsvDAOImpl;
import br.com.teste.csvquery.service.MunicipioService;

import java.util.stream.Stream;

/**
 * Created by fefedo on 01/08/17.
 */
public class MunicipioServiceImpl implements MunicipioService {

    private static final String DEFAULT_SEPARATOR = ",";

    private MunicipioDAO municipioDAO;


    @Override
    public void consultarColunas() {
        String[] colunas = municipioDAO.consultarColunas();
        System.out.println("Suas colunas sao: ");
        Stream.of(colunas).forEach(s -> System.out.print(s+ " "));
        System.out.println("\n");
    }

    @Override
    public void countTotal() {
        countTotal(null);
    }

    @Override
    public void countTotal(String coluna) {
        System.out.println("Quantidade de registros encontrados: " + (municipioDAO.countTotal(coluna)));
    }

    @Override
    public void consultaPorFiltro(String coluna, String filtro) {
        consultarColunas();
        Stream<String> linhas = municipioDAO.consultaPorFiltro(coluna, filtro);
        Integer indiceColunaConsultada = municipioDAO.indiceColunaConsultada(coluna);
        linhas.forEach(s -> {
            String linha = s.split(DEFAULT_SEPARATOR)[indiceColunaConsultada];
            if(linha.equals(filtro)){
                System.out.println(s);
            }
        });
    }

    @Override
    public void defineRecurso(Object recurso) {
        if (recurso instanceof String && ((String) recurso).endsWith(".csv") || ((String) recurso).endsWith(".CSV")){
            municipioDAO = new MunicipioCsvDAOImpl();
            municipioDAO.setDataDource(recurso);
        }
    }
}
