package br.com.kubo.events.service;

import br.com.kubo.events.model.Conference;

import java.util.List;

public interface IConferenceService {

    public Conference addConference(Conference conference);
    public Conference getConferenceByid(Integer id);
    public List<Conference>getAllConferences();
}
