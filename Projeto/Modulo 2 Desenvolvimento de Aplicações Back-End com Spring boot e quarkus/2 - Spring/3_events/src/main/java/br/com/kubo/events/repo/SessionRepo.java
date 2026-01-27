package br.com.kubo.events.repo;

import br.com.kubo.events.model.Session;
import org.springframework.data.repository.ListCrudRepository;


// herdando a listcrudRepository apontando a model e o id

public interface SessionRepo extends ListCrudRepository<Session, Integer> {




}
