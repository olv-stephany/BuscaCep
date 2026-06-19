import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws Exception {
        System.out.println("------------ BUSCA CEP/ENDEREÇOS ------------");

        Scanner scanner = new Scanner(System.in);
        System.out.print("\n[1] Buscar por CEP\n[2] Buscar por endereço\n\nDigite a opção desejada: ");
        String opcao = scanner.nextLine();

        while (!opcao.equals("1") && !opcao.equals("2")) {
            System.out.println("Opção inválida. Por favor, digite '1' para buscar por CEP ou '2' para buscar por endereço.");
            System.out.print("\nDigite a opção desejada: ");
            opcao = scanner.nextLine();
        }

        if (opcao.equals("1")) {
            System.out.print("Digite o CEP: ");
            String cepDigitado = scanner.nextLine();
            BuscaCep.buscar(cepDigitado, scanner);
        }
        else if (opcao.equals("2")) {
            System.out.print("Digite o endereço: ");
            String enderecoDigitado = scanner.nextLine();
            BuscaEndereco.buscar(enderecoDigitado, scanner);
        }
    }
}