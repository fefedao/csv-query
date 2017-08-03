package br.com.teste.csvquery.main;

import br.com.teste.csvquery.service.MunicipioService;
import br.com.teste.csvquery.service.impl.MunicipioServiceImpl;
import br.com.teste.csvquery.util.ComandoResolver;
import br.com.teste.csvquery.util.ValidadorImportacaoCSV;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;

/**
 * Created by fefedo on 27/07/17.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("===============================================");
        System.out.println("Importador Municipios");
        System.out.println("===============================================");
        Scanner scan = new Scanner(System.in);

        Matcher matcher;
        boolean continuar = true;

        String pathParaArquivo  = ValidadorImportacaoCSV.validar(scan);
        MunicipioService municipioService = MunicipioServiceImpl.getInstance();
        municipioService.defineRecurso(pathParaArquivo);

        while (continuar) {
            System.out.println("===============================================");
            System.out.println("Comando aceitos: ");
            System.out.println("count * - escreve no console a contagem total de registros importados.");
            System.out.println("count distinct [propriedade] - escreve no console o total de valores distintos da propriedade (coluna) enviada.");
            System.out.println("filter [propriedade] [valor] - escreve no console a linha de cabeçalho e todas as linhas em que a propriedade enviada.");
            System.out.println("sair - finaliza o aplicativo.");
            System.out.println("===============================================");
            System.out.println("Digite o comando de consulta:");
            String comando = scan.nextLine();
            switch (ComandoResolver.interpretarComando(comando)){
                case COUNT_TOTAL:
                    municipioService.countTotal();
                    break;
                case COUNT_DISTINCT:
                    matcher = ComandoResolver.getComandoMatch(comando);
                    if (matcher.matches()) {
                        String campoConsultado = matcher.group(3);
                        municipioService.countTotal(campoConsultado);
                    } else {
                        System.out.println("Nenhum dado encontrado para o filtro.");
                    }
                    break;
                case FILTER:
                    matcher = ComandoResolver.getComandoMatch(comando);
                    if (matcher.matches()) {
                        String campoConsultado = matcher.group(2);
                        String valorConsultado = matcher.group(3);
                        municipioService.consultaPorFiltro(campoConsultado, valorConsultado);
                    } else {
                        System.out.println("Nenhum dado encontrado para o filtro.");
                    }
                    break;
                case SAIR:
                    System.out.println("Terminando execucao.");
                    System.exit(0);
                case ERRO:
                    System.out.println("Comando desconhecido.");
                    break;
            }
        }
    }
}
