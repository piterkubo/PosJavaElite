package br.com.kubo.events.repo;

import br.com.kubo.events.model.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserRepo extends ListCrudRepository<User, Integer> {

    public Optional<User> findByEmail(String email);
}
