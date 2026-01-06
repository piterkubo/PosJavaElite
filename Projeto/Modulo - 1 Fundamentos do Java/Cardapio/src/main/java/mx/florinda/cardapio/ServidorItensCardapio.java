package mx.florinda.cardapio;

import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;


public class ServidorItensCardapio {

    public static void main(String[] args) throws Exception{

        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8000), 0);

       // Criando o contexto do servidor
        httpServer.createContext("/itensCardapio.json", exchange -> { 
            
            // aponta para o arquivo
            Path path = Path.of("itensCardapio.json");

            // lendo o arquivo path
            String json = Files.readString(path);

            // passando os bytes da string
            byte[] bytes = json.getBytes();

            // pegando o exchange response headers
            Headers responseHeaders = exchange.getResponseHeaders();

            // Configurando o cabeçalho de UTF-8
            responseHeaders.add("Content-Type", "application/json; charset=UTF-8");

            // enviando o status e o numero de bytes
            exchange.sendResponseHeaders(200, bytes.length);
            
            // tentando pegar o corpo e escrevendo
            try(OutputStream responseBody = exchange.getResponseBody()){
                responseBody.write(bytes);
            }
        });

        IO.println("Subiu para o Servidor HTTTP");

        httpServer.start();

    }
    
}
