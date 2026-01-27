package br.com.kubo.account.repo;

import org.springframework.data.repository.ListCrudRepository;

import br.com.kubo.account.model.Account;

public interface AccountRepo extends ListCrudRepository<Account, Integer>{


}