package br.com.teste.csvquery.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fefedo on 02/08/17.
 */
public class ComandoResolverTest {

    @Test
    public void retornoComandosTest(){
        Assert.assertEquals(Comando.COUNT_TOTAL, ComandoResolver.interpretarComando("count *"));
        Assert.assertEquals(Comando.COUNT_DISTINCT, ComandoResolver.interpretarComando("count distinct uf"));
        Assert.assertEquals(Comando.FILTER, ComandoResolver.interpretarComando("filter uf SC"));
        Assert.assertEquals(Comando.ERRO, ComandoResolver.interpretarComando("asdasd"));
        Assert.assertEquals(Comando.SAIR, ComandoResolver.interpretarComando("sair"));
    }

    @Test
    public void matchComandoCountDistinct(){
        Assert.assertTrue(ComandoResolver.getComandoMatch("count distinct uf").matches());
    }

    @Test
    public void matchComandoFilter(){
        Assert.assertTrue(ComandoResolver.getComandoMatch("filter uf SC").matches());
    }

    @Test
    public void notMatchComandoFilter(){
        Assert.assertFalse(ComandoResolver.getComandoMatch("filter uf ").matches());
    }

}
