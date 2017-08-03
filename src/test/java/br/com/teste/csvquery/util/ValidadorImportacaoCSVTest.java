package br.com.teste.csvquery.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by fefedo on 02/08/17.
 */
public class ValidadorImportacaoCSVTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testRetornoArquivoEncontrado(){
        String path = this.getClass().getResource("/cidades.csv").getPath();
        Assert.assertNotNull(ValidadorImportacaoCSV.validar(path));
        Assert.assertTrue(outContent.toString().equals("Arquivo encontrado em " + path + "\n"));
    }

    @Test
    public void testRetornoArquivoNaoEncontrado(){
        String path = "/erro/cidades.csv";
        Assert.assertNull(ValidadorImportacaoCSV.validar(path));
        Assert.assertTrue(outContent.toString().equals("Arquivo nao encontrado ou sem permissao para leitura." + "\n"));
    }

    @Test
    public void testRetornoArquivoFormatoInvalido(){
        String path = this.getClass().getResource("/cidades").getPath();
        Assert.assertNull(ValidadorImportacaoCSV.validar(path));
        Assert.assertTrue(outContent.toString().equals("Arquivo com formato invalido." + "\n"));
    }

    @Test
    public void testRetornoArquivoDiretorio(){
        String path = this.getClass().getResource("").getPath();
        Assert.assertNull(ValidadorImportacaoCSV.validar(path));
        Assert.assertTrue(outContent.toString().equals("O caminho informado se trata de um diretorio." + "\n"));
    }

    @Test
    public void testSair(){
        Assert.assertTrue(Comando.SAIR.getComando().equals(ValidadorImportacaoCSV.validar("sair")));
        Assert.assertTrue(outContent.toString().equals("Terminando execucao." + "\n"));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
}
