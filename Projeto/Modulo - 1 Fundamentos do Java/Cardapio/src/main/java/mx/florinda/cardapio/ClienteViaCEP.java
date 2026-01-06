package mx.florinda.cardapio;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteViaCEP {
    
    public static void main(String [] args) throws Exception {

        URI  viaCepURI = URI.create("https://viacep.com.br/ws/01001000/json/");

        try(HttpClient client = HttpClient.newBuilder().build()){

            // usando o pacote javanet.http

           HttpRequest request = HttpRequest.newBuilder().uri(viaCepURI).build();

           HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

           IO.println(response.statusCode());

           IO.println(response.body());



        }


    }

}
