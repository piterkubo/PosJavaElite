package br.com.kubo.events.repo;

import br.com.kubo.events.model.Conference;
import org.springframework.data.repository.ListCrudRepository;

public interface ConferenceRepo extends ListCrudRepository<Conference, Integer> {
}
