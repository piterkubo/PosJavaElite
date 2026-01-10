package mx.florinda.cardapio;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteItensCardapio {
    
    public static void main(String [] args) throws Exception {

        //URI  viaItensCardapioURI = URI.create("http://localhost:8000/itensCardapio.json");itens-cardapio
        URI  viaItensCardapioURI = URI.create("http://localhost:8000/itens-cardapio");
        try(HttpClient client = HttpClient.newBuilder().build()){

            // usando o pacote javanet.http

           HttpRequest request = HttpRequest.newBuilder().uri(viaItensCardapioURI).build();

           HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

           IO.println(response.statusCode());

           IO.println(response.body());



        }


    }

}
