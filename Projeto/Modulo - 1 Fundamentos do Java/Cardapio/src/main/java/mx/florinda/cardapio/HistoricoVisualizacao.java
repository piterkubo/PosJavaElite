package mx.florinda.cardapio;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;

public class HistoricoVisualizacao{


    // injetando dependencias 

    private final Database database; // banco de dados 


    // Hora e data do java com hashMap
    private final Map<ItemCardapio, LocalDateTime> visualizacao = new WeakHashMap<>();

    public HistoricoVisualizacao(Database database){

        this.database = database;

    }


    public void registrarVisualizacao(Long itemId){
        Optional<ItemCardapio> optionalItemCardapio = database.itemCardapioPorId(itemId);
        if(optionalItemCardapio.isEmpty()){
            IO.println("\nItem não Encontrado :" + itemId);
            return ;
        }

       

        ItemCardapio itemCardapio = optionalItemCardapio.get();
        LocalDateTime agora = LocalDateTime.now();
        visualizacao.put(itemCardapio, LocalDateTime.now());

        System.out.printf(" '%s' visualizado em '%s'\n", itemCardapio.nome(), agora);

        
    }


    public void mostrarTotalItensVisualizados(){
        IO.println("\nTotal de itens visualizados: " + visualizacao.size());
    }



    public void listarVisualizacoes(){
        if(visualizacao.isEmpty()){
            IO.println("Nenhum item foi visualizado ainda .");

            return;
        }

        IO.println("\n Historico de visualizacoes");
        visualizacao.forEach((item, hora) -> System.out.printf("- %s em %s\n", item.nome(), hora));
        IO.println();
    }




}