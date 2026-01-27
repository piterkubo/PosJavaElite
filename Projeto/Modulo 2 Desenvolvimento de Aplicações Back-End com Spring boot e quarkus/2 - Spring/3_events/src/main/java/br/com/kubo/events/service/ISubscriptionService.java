package br.com.kubo.events.service;

import br.com.kubo.events.model.Session;
import br.com.kubo.events.model.Subscription;
import br.com.kubo.events.model.User;

import java.util.List;

public interface ISubscriptionService {

    public Subscription addSubscription(Subscription subscription);

    public List<Subscription> getAllByUser(User user);

    public List<Subscription> getAllBySession(Session session);

}
