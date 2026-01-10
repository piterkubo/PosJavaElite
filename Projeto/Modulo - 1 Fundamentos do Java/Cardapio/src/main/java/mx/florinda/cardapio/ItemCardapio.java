package mx.florinda.cardapio;

import java.io.Serializable;
import java.math.BigDecimal;


public record ItemCardapio(Long id, String nome, String descricao, CategoriaCardapio categoria, BigDecimal preco,
			BigDecimal precoComDescricao) implements Serializable
{



	public ItemCardapio alterarPreco(BigDecimal novoPreco){
		return new ItemCardapio(id, nome, descricao, categoria, novoPreco, precoComDescricao);

	}
	
	
	public enum CategoriaCardapio{
		ENTRADAS,
		PRATOS_PRINCIPAIS,
		BEBIDAS,
		SOBREMESAS;
	}

}
	
	
	
		
	
	
	
	
	
	
	
	

	
	
	
	
	
	


