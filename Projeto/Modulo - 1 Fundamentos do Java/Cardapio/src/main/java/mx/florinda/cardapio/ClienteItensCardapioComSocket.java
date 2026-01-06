package mx.florinda.cardapio;


import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteItensCardapioComSocket {
    
    public static void main(String[] args) throws Exception{

        //Montando o socket via cliente
        
        try(Socket clientSocket = new Socket("localhost", 8000)) {
		    try (PrintStream out =
		    new PrintStream(clientSocket.getOutputStream());
		
		    // Lendo InputStream
		    Scanner in = new Scanner(clientSocket.getInputStream())) {
		    out.println("GET /itensCardapio.json HTTP/1.1\n");
		
                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    
                    //Imprime na saida padrao o que form enviado do servidor
                    System.out.println(line);
                }
	        }
        }

        

          

    }

}
