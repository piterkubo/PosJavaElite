package mx.florinda.cardapio;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface Database {

    List<ItemCardapio> listaItensCardapio();

    Optional<ItemCardapio> itemCardapioPorId(Long id);

    boolean removerItemCardapio(Long itemId);

    boolean alterarPrecoItemCardapio(long id, BigDecimal novoPreco);

    void adicionaItemCardapio(ItemCardapio itemCardapio);
    
    int totalItensCardapio();

}