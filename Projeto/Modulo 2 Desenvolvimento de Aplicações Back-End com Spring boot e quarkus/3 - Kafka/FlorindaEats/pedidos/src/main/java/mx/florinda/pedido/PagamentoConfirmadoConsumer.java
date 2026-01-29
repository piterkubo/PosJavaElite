package mx.florinda.pedido;


import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni; // quarkus reativo
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;



@ApplicationScoped  // fazendo o quarkus a gerenciar a instancia da classe
public class PagamentoConfirmadoConsumer {

    @Incoming("pagamentoConfirmados") // ref o nome do topico

    public Uni<Void> consome(PagamentoConfirmadoEvent evento){

        return Panache.withTransaction(() ->
        Pedido.<Pedido>findById(evento.pedidoId)
                .onItem()
                .ifNotNull()
                .invoke(pedido -> {pedido.status = StatusPedido.PAGO;}))
                .replaceWithVoid();
    }
// retornando o reativo separado id se não for null invocar o pedido setar o status do pedido


}
