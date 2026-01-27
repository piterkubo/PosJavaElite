package br.com.kubo.events.model;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable // inf a classe encapsula 2 atributos para formar uma identificacao
public class SubscriptionId {

    @ManyToOne // incluindo a relaçaõ de N to 1
    @JoinColumn(name = "subscribed_user_id") // name column do banco
    private User user;

    @ManyToOne // incluindo a relaçaõ de N to 1
    @JoinColumn(name = "session_id") // name column do banco
    private Session session;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
