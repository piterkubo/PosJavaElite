package mx.florinda.cardapio;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.management.RuntimeErrorException;

public class ServidorItensCardapioComSocket {
    
    public static void main(String[] args) throws Exception{

        // instanciando um seversocket com sua porta 8000
        try(ServerSocket serverSocket = new ServerSocket(8000)){


            // imprimindo o retorno
            IO.println("Subiu servidor!");



            // criando um loop para fazer as requisição repetetitiva
            while(true){
            
                //todas vez que vem uma requisição do cliente é gerado um clientSocket
                Socket clientSocket = serverSocket.accept();
                // declarando a thread
                Thread thread = new Thread(() -> TrataRequisicao(clientSocket));
                thread.start();

                    
                      
                        
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

            IO.println(request);

            Thread.sleep(250);          
        

            // Enviar o response para o cliente via json


            // pegando path
            Path path = Path.of("itensCardapio.json");
        

             //lendo o arquivo json
            String json =  Files.readString(path);


            OutputStream clienteOS = clientSocket.getOutputStream();
        
            PrintStream clientOut = new PrintStream(clienteOS);

            // Criando o cabeçalho 
            clientOut.println("HTTP/1.1 200 OK");                
            clientOut.println("Content-type: application/json; charset=UTF-8");
            clientOut.println();
            clientOut.println(json);





        }

        catch(Exception e)
        {
            throw new RuntimeException(e);
        }

    }

}
