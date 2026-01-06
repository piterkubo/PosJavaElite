package mx.florinda.cardapio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.google.gson.Gson;

public class GeradorItensCardapioJson {
    
    public static void main(String[] args) throws IOException{

        
        Database database = new Database();

        List<ItemCardapio> itensCardapio = database.listaItensCardapio();

        Gson gson = new Gson();

        String json = gson.toJson(itensCardapio);

        Path arquivoItens = Path.of("itensCardapio.json");

        Files.writeString(arquivoItens, json);

    }
}
