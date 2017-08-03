package br.com.teste.csvquery.service.impl;

import br.com.teste.csvquery.service.MunicipioService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by fefedo on 02/08/17.
 */
public class MunicipioServiceImplTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private MunicipioService municipioService = MunicipioServiceImpl.getInstance();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        municipioService.defineRecurso(this.getClass().getResource("/municipios.csv").getPath());
    }

    @Test
    public void consultarColunasTest(){
        municipioService.consultarColunas();
        Assert.assertTrue(outContent.toString().equals("Suas colunas sao: \n" +
                "ibge_id uf name capital lon lat no_accents alternative_names microregion mesoregion \n" +
                "\n"));
    }

    @Test
    public void countTotalTest(){
        municipioService.countTotal();
        Assert.assertTrue(outContent.toString().equals("Quantidade de registros encontrados: 9" + "\n"));
    }

    @Test
    public void countDistinctTest(){
        municipioService.countTotal("uf");
        Assert.assertTrue(outContent.toString().equals("Quantidade de registros encontrados: 3" + "\n"));
    }

    @Test
    public void filterTest(){
        municipioService.consultaPorFiltro("uf","SP");
        Assert.assertTrue(outContent.toString().equals("Suas colunas sao: \n" +
                "ibge_id uf name capital lon lat no_accents alternative_names microregion mesoregion \n" +
                "\n" +
                "3540259,SP,Pontalinda,,-50.5246020157,-20.4408338429,Pontalinda,,Jales,São José do Rio Preto\n" +
                "3540309,SP,Pontes Gestal,,-49.7035523558,-20.1823631062,Pontes Gestal,,Votuporanga,São José do Rio Preto\n" +
                "3540408,SP,Populina,,-50.5368531154,-19.9443331307,Populina,,Jales,São José do Rio Preto\n" +
                "3540507,SP,Porangaba,,-48.1267674358,-23.1753874993,Porangaba,,Tatuí,Itapetininga\n" +
                "3540606,SP,Porto Feliz,,-47.5245965731,-23.214412268,Porto Feliz,,Sorocaba,Macro Metropolitana Paulista\n" +
                "3540705,SP,Porto Ferreira,,-47.4814096434,-21.858362505,Porto Ferreira,,Piraçununga,Campinas\n"));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
}
