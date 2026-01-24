package br.com.kubo.events.service;


import br.com.kubo.events.exception.NotFoundException;
import br.com.kubo.events.model.Conference;
import br.com.kubo.events.repo.ConferenceRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceServiceImpl implements IConferenceService {

    private ConferenceRepo repo;

    public ConferenceServiceImpl(ConferenceRepo repo) {
        this.repo = repo;
    }

    @Override
    public Conference addConference(Conference conference) {
        return repo.save(conference);
    }

    @Override
    public Conference getConferenceByid(Integer id) {
        return repo.findById(id).orElseThrow(() ->
                new NotFoundException("Conference "+ id +" not found"));
    }

    @Override
    public List<Conference> getAllConferences() {
        return repo.findAll();
    }
}
