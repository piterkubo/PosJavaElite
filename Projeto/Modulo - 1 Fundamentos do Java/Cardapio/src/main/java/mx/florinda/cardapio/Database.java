package mx.florinda.cardapio;

import static mx.florinda.cardapio.ItemCardapio.CategoriaCardapio.*;

import java.math.BigDecimal;




import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Database {

	private final Map <Long, ItemCardapio> itensPorId = new HashMap<>();
	private final Map<ItemCardapio, BigDecimal>auditoriaPrecos = new IdentityHashMap<>();


	public Database(){   // construtores

		var refrescoDoChaves = new ItemCardapio(1l, "Refresco do Chaves",
        		"Suco de limão que parece de tamarindo, mas tem gosto de groselha",
        		BEBIDAS, new BigDecimal("2.99"), null);
		
		itensPorId.put(refrescoDoChaves.id(),refrescoDoChaves);




		var  pirulito = new ItemCardapio(2l, "Perulito da Chiquinha",
        		"Doce",	SOBREMESAS, new BigDecimal("1.99"), null);
		
		itensPorId.put(pirulito.id(),pirulito);

		
		
		var sanduichePresunto = new ItemCardapio(3l, "Sanduiche de Presunto",
        		"Soboroso Sanduiche de Presunto",PRATOS_PRINCIPAIS, new BigDecimal("7.99"), null);
		
		itensPorId.put(sanduichePresunto.id(),sanduichePresunto);


	}


	public List<ItemCardapio> listaItensCardapio() {
		return new LinkedList<>(itensPorId.values());
	}


	public Optional<ItemCardapio> itemCardapioPorId(Long id) {
		return Optional.ofNullable(itensPorId.get(id));
	}


	public boolean removerItemCardapio(Long itemId){

		ItemCardapio itemCardapioRemovido = itensPorId.remove(itemId);

		return itemCardapioRemovido != null;

	}

	public boolean alterarPrecoItemCardapio(long id, BigDecimal novoPreco)
	{

		ItemCardapio itemCardapio = itensPorId.get(id);

		if(itemCardapio == null){
			return false;
		}

		ItemCardapio itemPrecoAlterado = itemCardapio.alterarPreco(novoPreco);
		itensPorId.put(id, itemPrecoAlterado);
		auditoriaPrecos.put(itemCardapio, novoPreco);

		return true;
	}

	public void imprimirRastroAuditoriaPrecos(){
		IO.println("\n Auditoria de precos :");
		auditoriaPrecos.forEach((itemCardapio, novoPreco) -> 
		System.out.printf("- %s: %.2f => %.2f", itemCardapio.nome(), itemCardapio.preco(), novoPreco ));
	
		IO.println();

	}



	




	/*public List<ItemCardapio> listaDeItensCardapio(){
		
		ArrayList<ItemCardapio> itens = new ArrayList<>();
		
		var refrescoDoChaves = new ItemCardapio(1l, "Refresco do Chaves",
        		"Suco de limão que parece de tamarindo, mas tem gosto de groselha",
        		BEBIDAS, new BigDecimal("2.99"), null);
		
		itens.add(refrescoDoChaves);
		
		var  Pirulito = new ItemCardapio(2l, "Perulito da Chiquinha",
        		"Doce",	SOBREMESAS, new BigDecimal("1.99"), null);
		
		itens.add(Pirulito);
		
		
		var SanduichePresunto = new ItemCardapio(3l, "Sanduiche de Presunto",
        		"Soboroso Sanduiche de Presunto",PRATOS_PRINCIPAIS, new BigDecimal("7.99"), null);
		
		itens.add(SanduichePresunto);
		
			
		
		
		
		
		return itens;
		
	}*/
	
}
