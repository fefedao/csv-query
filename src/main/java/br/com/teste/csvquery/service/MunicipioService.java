package br.com.teste.csvquery.service;

/**
 * Created by fefedo on 01/08/17.
 */
public interface MunicipioService {

    void consultarColunas();

    void countTotal();

    void countTotal(String coluna);

    void consultaPorFiltro(String coluna, String filtro);

    void defineRecurso(Object dataSource);
}
