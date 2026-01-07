package mx.florinda.cardapio;


import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;



public class ServidorItensCardapioComSocket {

    //injetando database

    private static final Database database = new Database();
    
    public static void main(String[] args) throws Exception{

        // criando um pool de threads para limitar a quantide de requisição
        try(ExecutorService executorService = Executors.newFixedThreadPool(50)){

            
            // instanciando um seversocket com sua porta 8000
            try(ServerSocket serverSocket = new ServerSocket(8000)){


                // imprimindo o retorno
                IO.println("Subiu servidor!");

                // criando um loop para fazer as requisição repetetitiva
                while(true){
                
                    //todas vez que vem uma requisição do cliente é gerado um clientSocket
                    Socket clientSocket = serverSocket.accept();

                    // declarando a thread
                executorService.execute(() -> TrataRequisicao(clientSocket));
                

                        
                        
                            
                }
                

            }


        }

        
        
         

    }

    private static void TrataRequisicao(Socket clientSocket) {
        
        // verifica o que o cliente escreveu
        try(clientSocket){

            
            InputStream clienteIS = clientSocket.getInputStream();

            StringBuilder requestBuilder = new StringBuilder();
        
        
            int data;

            do{
                data = clienteIS.read();
                requestBuilder.append((char)data);
            }
            while(clienteIS.available() > 0);
        
            String request = requestBuilder.toString();

            IO.println("------------------------------------------------");

            IO.println(request);

            IO.println("\n\nChegou um novo Request");

            Thread.sleep(250); 
            
            // Criando um cabeçalho para as requisições GET/POST

            String[] requestChunks = request.split("\r\n\r\n");
            
            String requestLineAndHeaders = requestChunks[0];

            String [] requestLineAndHeadersChunks = requestLineAndHeaders.split("\r\n");

            String requestLine = requestLineAndHeadersChunks[0];

            String[] requestLineChunks = requestLine.split(" ");


            //Criando metodo (GET/Post)

            String method = requestLineChunks[0];
            String requestURI = requestLineChunks[1];

           
            IO.println(method);
            IO.println(requestURI);

            
            OutputStream clienteOS = clientSocket.getOutputStream();            
            PrintStream clientOut = new PrintStream(clienteOS);



            //Criando a condição para pegar o cabeçalho via GET

            if(method.equals("GET") && requestURI.equals("/itensCardapio.json")){

                // Enviar o response para o cliente via json

                IO.println("Chamou arquivo JSON");

                // pegando path
                Path path = Path.of("itensCardapio.json");
            

                //lendo o arquivo json
                String json =  Files.readString(path);


                

                // Criando o cabeçalho 
                clientOut.println("HTTP/1.1 200 OK");                
                clientOut.println("Content-type: application/json; charset=UTF-8");
                clientOut.println();
                clientOut.println(json);

            }

            // pegando a listagem de cardapio

            else if(method.equals("GET") && requestURI.equals("/itens-cardapio")){

                IO.println("Chamou a listagem de itens Cardapio");

                // pegando uma lista de cardapio                
                List<ItemCardapio> listaItensCardapio = database.listaItensCardapio();

                // criando gson

                Gson gson = new Gson();

                String json = gson.toJson(listaItensCardapio);



                // Criando o cabeçalho 
                clientOut.println("HTTP/1.1 200 OK");                
                clientOut.println("Content-type: application/json; charset=UTF-8");
                clientOut.println();
                clientOut.println(json);


            }


            // pegando a listagem de cardapio

            else if(method.equals("GET") && requestURI.equals("/itens-cardapio/total")){

                IO.println("Chamou total de itens Cardapio");

                // pegando uma lista de cardapio                
                List<ItemCardapio> listaItensCardapio = database.listaItensCardapio();
                int total = listaItensCardapio.size();
              
                
                // Criando o cabeçalho 
                clientOut.println("HTTP/1.1 200 OK");                
                clientOut.println("Content-type: application/json; charset=UTF-8");
                clientOut.println();
                IO.println(total);
                


            }

            // pegando a listagem de cardapio

            else if(method.equals("POST") && requestURI.equals("/itens-cardapio")){

                IO.println("Chamou adição de itens Cardapio");

                // Caso apresente erro
                if(requestChunks.length == 1){
                    clientOut.println("HTTP/1.1 400 Bad Request");      
                    return;

                }

                String body = requestChunks[1];

                Gson gson = new Gson();

                ItemCardapio novoItemCardapio = gson.fromJson(body, ItemCardapio.class);              

                // Enviando via post               
                database.adicionaItemCardapio(novoItemCardapio);                              
                
                // Criando o cabeçalho 
                clientOut.println("HTTP/1.1 201 Created");                
                
                


            }






            else{

                IO.println("URI não encontrada: " + requestURI);
                clientOut.println("HTTP/1.1 404 Not Found");   
            }


        





        }

        catch(Exception e)
        {
            throw new RuntimeException(e);
        }

    }

}
