import java.util.Scanner;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class BuscaEndereco {
    public static void buscar(String enderecoDigitado, Scanner scanner) throws Exception {
        String url = "https://nominatim.openstreetmap.org/search?q=";
        enderecoDigitado = enderecoDigitado.replace(" ", "+");
        url = url + enderecoDigitado + "&format=json";
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("\nBusca realizada com sucesso.\n");
        String body = response.body();

        System.out.println("Resultados encontrados:\n");
        String[] resultados = body.split("\\},\\{");

        for (int i = 0; i < resultados.length; i++) {
            String resultado = resultados[i];
            String displayName = resultado.split("\"display_name\":\"")[1].split("\"")[0];
            System.out.println((i + 1) + ". " + displayName);
        }

        System.out.println("\nDigite o número do resultado desejado (ou 0 para cancelar):");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 0) {
            System.out.println("Busca cancelada.");
            return; 
        }

        if (opcao < 1 || opcao > resultados.length) {
            System.out.println("Opção inválida.");
            return;
        }

        String displayName = resultados[opcao - 1].split("\"display_name\":\"")[1].split("\"")[0];
        String lat = resultados[opcao - 1].split("\"lat\":\"")[1].split("\"")[0];
        String lon = resultados[opcao - 1].split("\"lon\":\"")[1].split("\"")[0];
        String addresstype = resultados[opcao - 1].split("\"addresstype\":\"")[1].split("\"")[0];

        System.out.println("\nDetalhes do endereço selecionado:\n");
        System.out.println("Nome: " + displayName);
        System.out.println("Latitude: " + lat);
        System.out.println("Longitude: " + lon);
        System.out.println("Tipo de endereço: " + addresstype);
    }
}
