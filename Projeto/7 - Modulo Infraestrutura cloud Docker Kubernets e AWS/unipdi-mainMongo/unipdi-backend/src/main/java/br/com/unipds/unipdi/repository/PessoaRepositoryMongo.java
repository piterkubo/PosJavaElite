package br.com.unipds.unipdi.repository;

import br.com.unipds.unipdi.model.PessoaMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PessoaRepositoryMongo extends MongoRepository<PessoaMongo, String> {
    Optional<PessoaMongo> findByMatricula(String matricula);
    boolean existsByMatricula(String matricula);
}
