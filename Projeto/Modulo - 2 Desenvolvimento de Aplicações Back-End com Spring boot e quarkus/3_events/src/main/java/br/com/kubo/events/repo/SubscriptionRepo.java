package br.com.kubo.events.repo;

import br.com.kubo.events.model.Session;
import br.com.kubo.events.model.Subscription;
import br.com.kubo.events.model.SubscriptionId;
import br.com.kubo.events.model.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface SubscriptionRepo extends ListCrudRepository<Subscription, SubscriptionId> {

    public List<Subscription> findByIdUser(User user);
    public List<Subscription> findByIdSession(Session session);

}
