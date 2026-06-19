import java.util.Scanner;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class BuscaCep{
    public static void buscar(String cepDigitado, Scanner scanner) throws Exception {
        String url = "https://brasilapi.com.br/api/cep/v2/";
        cepDigitado = cepDigitado.replace("-", "");
       
        if (cepDigitado.length() == 8 && cepDigitado.matches("[0-9]+")) {
            url = url + cepDigitado;
        }
        else{
            while (cepDigitado.length() != 8 || !cepDigitado.matches("[0-9]+")) {
                System.out.println("CEP inválido. \nDicas para buscar um CEP válido: \n- O CEP deve conter 8 dígitos numéricos. \n- Não utilize letras ou caracteres especiais, apenas (-). \n- Verifique se o CEP está completo e correto.");
                System.out.print("\nCEP: ");
                cepDigitado = scanner.nextLine();
            }
            url = url + cepDigitado;
        }

        //client
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("\nBusca realizada com sucesso.\n");
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