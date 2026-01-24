package br.com.kubo.account.repo;

import org.springframework.data.repository.ListCrudRepository;

import br.com.kubo.account.model.Transaction;

public interface TransactionRepo extends ListCrudRepository<Transaction, Integer>{

}