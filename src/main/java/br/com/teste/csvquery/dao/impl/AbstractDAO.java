package br.com.teste.csvquery.dao.impl;

/**
 * Created by fefedo on 01/08/17.
 */
abstract class AbstractDAO {

    protected Object dataSource;

    public void setDataSource(Object dataSource) {
        this.dataSource = dataSource;
    }
}
