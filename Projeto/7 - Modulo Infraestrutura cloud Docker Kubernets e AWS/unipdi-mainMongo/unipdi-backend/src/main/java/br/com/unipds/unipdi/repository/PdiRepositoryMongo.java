package br.com.unipds.unipdi.repository;

import br.com.unipds.unipdi.model.PdiMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PdiRepositoryMongo extends MongoRepository<PdiMongo, String>  {
    List<PdiMongo> findByPessoaMatricula(String matricula);
}
