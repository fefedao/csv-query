package br.com.teste.csvquery.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fefedo on 02/08/17.
 */
public class ComandoResolverTest {

    @Test
    public void testaRetornoComandos(){
        Assert.assertEquals(Comando.COUNT_TOTAL, ComandoResolver.interpretarComando("count *"));
        Assert.assertEquals(Comando.COUNT_DISTINCT, ComandoResolver.interpretarComando("count distinct uf"));
        Assert.assertEquals(Comando.FILTER, ComandoResolver.interpretarComando("filter "));
        Assert.assertEquals(Comando.ERRO, ComandoResolver.interpretarComando("asdasd"));
        Assert.assertEquals(Comando.SAIR, ComandoResolver.interpretarComando("sair"));
    }

}
