package br.com.kubo.events.service;

import br.com.kubo.events.model.Session;
import java.util.List;

public interface ISessionService {
    public Session addSession(Session session);
    public Session getSessionById(Integer id);
    public List<Session> getAllSessions();
}
