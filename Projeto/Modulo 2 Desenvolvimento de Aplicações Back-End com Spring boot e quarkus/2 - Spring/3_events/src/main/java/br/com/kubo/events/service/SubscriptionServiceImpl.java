package br.com.kubo.events.service;

import br.com.kubo.events.model.Session;
import br.com.kubo.events.model.Subscription;
import br.com.kubo.events.model.User;
import br.com.kubo.events.repo.SubscriptionRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;


@Service
public class SubscriptionServiceImpl implements ISubscriptionService  {

    private SubscriptionRepo repo;

    public SubscriptionServiceImpl(SubscriptionRepo repo) {
        this.repo = repo;
    }



    @Override
    public Subscription addSubscription(Subscription subscription) {
        subscription.setCreateAt(LocalTime.from(LocalDateTime.now()));
        subscription.setUniqueID(UUID.randomUUID().toString());
        return repo.save(subscription);
    }


    @Override
    public List<Subscription> getAllByUser(User user) {
        return repo.findByIdUser(user);
    }

    @Override
    public List<Subscription> getAllBySession(Session session) {
        return repo.findByIdSession(session);
    }
}
