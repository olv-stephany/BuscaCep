import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws Exception {
        boolean rodando = true;
        
        System.out.println("------------ BUSCA CEP/ENDEREÇOS ------------");
        Scanner scanner = new Scanner(System.in);

        while (rodando) {

            System.out.print("\n[1] Buscar por CEP\n[2] Buscar por endereço\n[3] Sair\n\nDigite a opção desejada: ");
            String opcao = scanner.nextLine();

            if (opcao.equals("1")) {
                System.out.print("Digite o CEP: ");
                String cepDigitado = scanner.nextLine();
                BuscaCep.buscar(cepDigitado, scanner);

                System.out.println("\nDeseja realizar outra busca? (s/n)");
                String resposta = scanner.nextLine();

                if (resposta.equalsIgnoreCase("n")) {
                    rodando = false;
                    System.out.println("Saindo...");
                    Historico.salvar();
                }
            }
            else if (opcao.equals("2")) {
                System.out.print("Digite o endereço: ");
                String enderecoDigitado = scanner.nextLine();
                BuscaEndereco.buscar(enderecoDigitado, scanner);

                System.out.println("\nDeseja realizar outra busca? (s/n)");
                String resposta = scanner.nextLine();
                if (resposta.equalsIgnoreCase("n")) {
                    rodando = false;
                    System.out.println("Saindo...");
                    Historico.salvar();
                }
            }
            else if (opcao.equals("3")) {
                rodando = false;
                System.out.println("Saindo...");
                Historico.salvar();
            }
            else {
                System.out.println("Opção inválida. Por favor, digite '1', '2' ou '3'.");
            }
        }
    }
}