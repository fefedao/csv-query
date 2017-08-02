package br.com.teste.csvquery.dao;

import java.util.stream.Stream;

/**
 * Created by fefedo on 01/08/17.
 */
public interface MunicipioDAO {

    String[] consultarColunas();

    String countTotal();

    String countTotal(String coluna);

    Stream<String> consultaPorFiltro(String coluna, String filtro);

    Integer indiceColunaConsultada(String colunaConsultada);

    void setDataDource(Object dataSource);
}
