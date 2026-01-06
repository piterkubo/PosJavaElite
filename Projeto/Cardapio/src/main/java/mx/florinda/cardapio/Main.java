package mx.florinda.cardapio;


import static mx.florinda.cardapio.ItemCardapio.CategoriaCardapio.ENTRADAS;
import static mx.florinda.cardapio.ItemCardapio.CategoriaCardapio.PRATOS_PRINCIPAIS;
import static mx.florinda.cardapio.ItemCardapio.CategoriaCardapio.SOBREMESAS;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;



public class Main {
    public static void main(String[] args) throws InterruptedException {
        

        Database database = new Database();

        

        //List<ItemCardapio> itens = database.listaItensCardapio();        

        Optional<ItemCardapio> optionalItemCardapio = database.itemCardapioPorId(4l);
        
        if (optionalItemCardapio.isPresent()) 
        {
            ItemCardapio itemCardapio = optionalItemCardapio.get();
            System.out.println(itemCardapio);
        } 
    
        else 
        {
            System.out.printf("Item de id %d nao encontrado%n", 1);
        }


        IO.println("---------------------------------------------------------");
        IO.println("Precisa manter as categorias que estao em promocao O melhor EnumSet");

        // Precisa manter as categorias que estao em promocao

        /*Set<CategoriaCardapio>categoriasPromocao = new TreeSet<>();
        categoriasPromocao.add(ItemCardapio.CategoriaCardapio.SOBREMESAS);
        categoriasPromocao.add(ItemCardapio.CategoriaCardapio.ENTRADAS);
        categoriasPromocao.forEach(System.out::println);



        IO.println("---------------------------------------------------------");
        IO.println("Outra Maneira de fazer a declaracao em Java Mais antigos");
        
        Set<ItemCardapio.CategoriaCardapio> categoriaCardapio2 = Set.of(SOBREMESAS,ENTRADAS);
        categoriaCardapio2.forEach(System.out::println);*/


        IO.println("---------------------------------------------------------");
        IO.println("Usando o Enum Set para manter a ordem das categorias");

        Set<ItemCardapio.CategoriaCardapio> categoriaCardapio3 = EnumSet.of(SOBREMESAS,ENTRADAS);
        categoriaCardapio3.add(PRATOS_PRINCIPAIS);
        categoriaCardapio3.forEach(System.out::println);
        


        IO.println("---------------------------------------------------------");
        IO.println("Usando as descricoes associadas as categorias em promocao com EnumMap ");

        Map<ItemCardapio.CategoriaCardapio, String> promocoes = new 
            EnumMap<>(ItemCardapio.CategoriaCardapio.class);

        promocoes.put(SOBREMESAS, " O doce perfeito para voce!");
        promocoes.put(ENTRADAS, " Comece sua refeicao com um toque de sabor!");
        IO.print(promocoes);



        // Preciso de um historico de visualização do cardapio
        
        IO.println();
        IO.println();
        
        IO.println("Mostrando o historico de visualizacoes!!!!!");

         IO.println();

        HistoricoVisualizacao historico = new HistoricoVisualizacao(database);



        historico.registrarVisualizacao(1l);
        historico.registrarVisualizacao(2l);
        historico.registrarVisualizacao(3l);

        IO.println();

        IO.println("Mostrando historico de visualizacao");
        historico.mostrarTotalItensVisualizados();

        IO.println();


        IO.println("Listar Visualizacoes");
        historico.listarVisualizacoes();







        //REMOVENDO UM ITEM DE CARDAPIO

        long idParaRemover = 1l;

        boolean removido = database.removerItemCardapio(idParaRemover);

        if(removido){
            IO.println("Item removido: " + idParaRemover);
        }

        else{
            IO.println("Item não encontrado" + idParaRemover);
        }

        database.listaItensCardapio().forEach(System.out::println);


        // chamando o garban de collector
        System.gc();
        Thread.sleep(500);
       

        IO.println();

        IO.println("Mostrando historico de visualizacao");
        historico.mostrarTotalItensVisualizados();

        IO.println();


        IO.println("Listar Visualizacoes");
        historico.listarVisualizacoes();





        // Alterando os precos de itens de cardapio usando o identityhashmap no database


        //consultando se o id existe
        ItemCardapio itemCardapio90 = database.itemCardapioPorId(2l).orElseThrow();

        System.out.printf("\n%s (%d) R$ %.2f", itemCardapio90.nome(), itemCardapio90.id(), itemCardapio90.preco());

        
        // fazendo a alteracao
        database.alterarPrecoItemCardapio(2l, new BigDecimal(3.99));
        ItemCardapio itemCardapio91 = database.itemCardapioPorId(2l).orElseThrow();

        System.out.printf("\n%s (%d) R$ %.2f", itemCardapio91.nome(), itemCardapio91.id(), itemCardapio91.preco());


        database.alterarPrecoItemCardapio(2l, new BigDecimal(1.99));
        ItemCardapio itemCardapio92 = database.itemCardapioPorId(2l).orElseThrow();

        System.out.printf("\n%s (%d) R$ %.2f", itemCardapio92.nome(), itemCardapio92.id(), itemCardapio92.preco());

        database.alterarPrecoItemCardapio(2l, new BigDecimal(4.99));
        ItemCardapio itemCardapio93 = database.itemCardapioPorId(2l).orElseThrow();

        System.out.printf("\n%s (%d) R$ %.2f", itemCardapio93.nome(), itemCardapio93.id(), itemCardapio93.preco());












        // Auditar as mudanças de preco dos itens cardapio



        database.imprimirRastroAuditoriaPrecos();































       /*Database database = new Database();
       
       List<ItemCardapio> itens = database.listaDeItensCardapio();
       
       for(ItemCardapio item: itens) {
    	   IO.println(item);
       }
       
       IO.println("------------");
       

       // pegando um index

       ItemCardapio cardapio = itens.get(2);

       IO.println(cardapio.nome());


       // verificando o tamanho da lista
       IO.println(itens.size());
       
       
       // removendo um item
       itens.remove(0);

       IO.println(itens.size());


       IO.println("-----------------Usando foreach----------------");
       IO.println();

       itens.forEach(System.out:: println);*/




        
    }
}
