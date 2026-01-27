package br.com.kubo.events.service;


import br.com.kubo.events.exception.NotFoundException;
import br.com.kubo.events.model.Session;
import br.com.kubo.events.repo.SessionRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements ISessionService {

    private final SessionRepo repo;

    public SessionServiceImpl(SessionRepo repo) {
        this.repo = repo;
    }

    @Override
    public Session addSession(Session session) {
        return repo.save(session);
    }

    @Override
    public Session getSessionById(Integer id) {
        return repo.findById(id).orElseThrow(()-> new NotFoundException("Session " + id + " not found"));
    }

    @Override
    public List<Session> getAllSessions() {
        return repo.findAll();
    }
}
