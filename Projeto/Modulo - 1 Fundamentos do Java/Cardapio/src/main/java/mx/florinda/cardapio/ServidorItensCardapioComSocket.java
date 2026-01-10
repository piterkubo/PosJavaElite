package mx.florinda.cardapio;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;



public class ServidorItensCardapioComSocket {

    
    //injetando database

    //Criando o logger
    private static final Logger logger = Logger.getLogger(ServidorItensCardapioComSocket.class.getName());

    private static final Database database = new SQLDatabase();
    
    public static void main(String[] args) throws Exception{

        // criando um pool de threads para limitar a quantide de requisição
        try(ExecutorService executorService = Executors.newFixedThreadPool(50)){

            
            // instanciando um seversocket com sua porta 8000
            try(ServerSocket serverSocket = new ServerSocket(8000)){


                // imprimindo o retorno
                logger.info("Subiu servidor!");

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

            logger.finest(request);

            logger.fine("\n\nChegou um novo Request");

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


           // colocar o () -> suplay p/ concatenar evita Computação desnecessaria 
            logger.finer(() -> "Method: " + method);
            logger.finer(() -> "Request URI" + requestURI);
            

            
            OutputStream clienteOS = clientSocket.getOutputStream();            
            PrintStream clientOut = new PrintStream(clienteOS);

            try{

                //Criando a condição para pegar o cabeçalho via GET

                if(method.equals("GET") && requestURI.equals("/itensCardapio.json")){

                    // Enviar o response para o cliente via json

                    logger.fine("Chamou arquivo JSON");

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

                    logger.fine("Chamou a listagem de itens Cardapio");

                    // pegando uma lista de cardapio                
                    List<ItemCardapio> listaItensCardapio = database.listaItensCardapio();


                    // preparando o ambiente para serialização em java
                    String mediaType = "aplication/json/";


                    for(int i = 1; i < requestLineAndHeadersChunks.length; i++){

                        String header = requestLineAndHeadersChunks[i];

                        logger.fine(header);

                        if(header.contains("Accept")){

                            mediaType = header.replace("Accept: ", "");

                            logger.fine(mediaType);
                        }




                    }


                    byte[] body;

                    if("application/x-java-serialized-object".equals(mediaType)){

                        var bos = new ByteArrayOutputStream();

                        var oos = new ObjectOutputStream(bos);

                        oos.writeObject(listaItensCardapio);

                        body = bos.toByteArray();
                    }

                    else{


                        // criando gson

                        Gson gson = new Gson();

                        String json = gson.toJson(listaItensCardapio);

                        body = json.getBytes(StandardCharsets.UTF_8);

                    }





                    // Criando o cabeçalho 
                    clienteOS.write("HTTP/1.1 200 OK\r\n".getBytes(StandardCharsets.UTF_8));
                    clienteOS.write(("Content-type:" + mediaType + " application/json; charset=UTF-8\r\n\r\n").getBytes(StandardCharsets.UTF_8));
                    clienteOS.write(body);



                }


                // pegando a listagem de cardapio

                else if(method.equals("GET") && requestURI.equals("/itens-cardapio/total")){

                    logger.fine("Chamou total de itens Cardapio");

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


                    IO.println(novoItemCardapio);


                    // Enviando via post               
                    database.adicionaItemCardapio(novoItemCardapio);                              
                    
                    // Criando o cabeçalho 
                    clientOut.println("HTTP/1.1 201 Created");                
                    
                      
                }

                // criando uma condição get para retornar um html
                else if(method.equals("GET") && requestURI.equals("/") ||requestURI.equals("/en") ){

                    // chamando a lsita de cardapio do banco
                    List<ItemCardapio> listaItemCardapios = database.listaItensCardapio();

                    // verificando a requisição é /en
                    Locale locale = "/en".equals(requestURI)? Locale.US: Locale.of("pt", "BR");


                    // declarando a formatação de moeda
                    NumberFormat formatadorMoeda = NumberFormat.getCurrencyInstance(locale);


                    //Declarando o resource bundle para deixar as descrição dos produtos em ingles
                    ResourceBundle mensagens = ResourceBundle.getBundle("mensagens", locale);

                    //Formatador de Hora e DATA
                    DateTimeFormatter formatterDataHora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withLocale(locale);
                    DateTimeFormatter formatterMesAno = DateTimeFormatter.ofPattern("MMMM/yyyy").withLocale(locale);

                    // acumulando o html com string builder

                    StringBuilder htmlTodosItens =  new StringBuilder();


                    for(ItemCardapio item : listaItemCardapios){

                        String htmlPrecoItem;

                        if(item.precoComDescricao() == null){
                            htmlPrecoItem = "<strong>" + formatadorMoeda.format(item.preco()) + "</strong>";
                        }
                        else{

                            htmlPrecoItem = "<mark>Em Promoção</mark><strong>" +
                                    item.precoComDescricao() +" </strong> <s>" + formatadorMoeda.format(item.preco()) + "</s>";

                        }


                        // pegando o nome da categoria para traduzir
                        String categoria = mensagens.getString("categoria.cardapio." + item.categoria().name().toLowerCase());

                        String htmlItem = """
                                 <article>
                                        <kbd>%s</kbd>
                                        <h3>%s</h3>
                                        <p>%s</p>
                                        %s
                                        
                                 </article>
                                
                                
                                """.formatted(categoria,item.nome(),item.descricao(), htmlPrecoItem);
                        htmlTodosItens.append(htmlItem);
                    }


                    // criando uma string  html de textblock

                    String html = """
                            <!DOCTYPE html>
                            <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2.1.1/css/pico.min.css">
                                <title>Florinda Eats - Cardapio</title>
                            </head>
                            <body>
                            
                                <header class="container">
                            
                                    <hgroup>
                                        <h1>Florinda Eats</h1>
                                        <p>O sabor da Vila direto pra você</p>
                                    </hgroup>
                            
                                </header>
                            
                            
                                <main class="container">
                                    <h2>Cardapio</h2>
                            
                            
                            
                               %s
                            
                            
                               </main>
                                   <footer class="container">
                                       <p><small><em>Preços de acordo com %s</em></small></p>
                                       <p><strong>Florinda Eats</strong> Todos os direitos reservados - %s</p>
                                   </footer>
                               </body>
                               </html>
                            """.formatted(htmlTodosItens.toString(), formatterDataHora.format(ZonedDateTime.now()),
                            formatterMesAno.format(YearMonth.now()));

                    // Criando o cabeçalho 
                    clientOut.print("HTTP/1.1 200 OK\r\n");
                    clientOut.print("Content-type: text/html; charset=UTF-8\r\n\r\n");
                    clientOut.print(html);
                    clientOut.print("\r\n");
                
                }
                else{

                    logger.warning(() -> "URI não encontrada: " + requestURI);
                    clientOut.println("HTTP/1.1 404 Not Found"); 

                


                }
                

                
            }

            catch(Exception e){

                logger.log( Level.SEVERE, e, ()-> "Erro ao tratar " + method + " " + requestURI );

                clientOut.println("HTTP/1.1 500 Internal Server Error");

                clientOut.println(e.getMessage());
            }
        





        }

        catch(Exception e)
        {
            //logger.severe("Erro no servidor");

            logger.log(Level.SEVERE, "Erro no servidor", e);

            throw new RuntimeException(e);
        }

    }

}
