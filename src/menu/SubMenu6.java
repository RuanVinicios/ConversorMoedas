package menu;

import com.google.gson.Gson;
import converter.Conversao;
import converter.Requests;

import java.io.IOException;
import java.util.Scanner;

public class SubMenu6 {
    public void mostrarSubMenu() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("#########################################################");
            System.out.println("Conversão de Euro para Dollar");
            System.out.println("1 - Inserir valor");
            System.out.println("2 - Voltar");
            System.out.println("0 - Sair");
            System.out.println("#########################################################");
            System.out.print("Opção: ");


            while (!scanner.hasNextInt()){
                System.out.println("Por favor, insira um número inteiro válido.");
                scanner.next();
            }

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Valor em Euro = € ");
                    String valor;

                    do {
                        valor = scanner.next();
                        if (!valor.matches("[0-9.,]+")) {
                            System.out.println("Por favor, insira um número válido.");
                            System.out.print("Valor em Euro = € ");
                        }
                    } while (!valor.matches("[0-9.,]+"));

                    valor = valor.replace(',', '.');
                    String busca = "https://v6.exchangerate-api.com/v6/" +
                            "a8727190333dd9ba847ac1e6/pair/EUR/" +
                            "USD/" + valor;

                    Requests novabusca = new Requests(busca);
                    String buscaNova = novabusca.consulta();

                    Gson gson = new Gson();
                    Conversao conversao = gson.fromJson(buscaNova, Conversao.class);

                    System.out.println(conversao.toString());

                    break;
                case 2:
                    return;
                case 0:
                    System.out.println("Saindo...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Por favor, digite novamente.");
                    break;
            }
        } while (true);
    }
}