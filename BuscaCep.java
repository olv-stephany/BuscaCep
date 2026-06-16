import java.util.Scanner;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class BuscaCep{
    public static void main(String[] args) throws Exception {
        System.out.println("------------ BUSCA CEP ------------");

        Scanner scanner = new Scanner(System.in);
        System.out.print("CEP: ");
        String cepDigitado = scanner.nextLine();

        String url = "https://brasilapi.com.br/api/cep/v2/" + cepDigitado;

        //client
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        String body = response.body();
        String city = body.split("\"city\":\"")[1].split("\"")[0];
        String state = body.split("\"state\":\"")[1].split("\"")[0];
        String neighborhood = body.split("\"neighborhood\":\"")[1].split("\"")[0];
        String street = body.split("\"street\":\"")[1].split("\"")[0];

        System.out.println("Cidade: " + city);
        System.out.println("Estado: " + state);
        System.out.println("Bairro: " + neighborhood);
        System.out.println("Rua: " + street);
    }
}