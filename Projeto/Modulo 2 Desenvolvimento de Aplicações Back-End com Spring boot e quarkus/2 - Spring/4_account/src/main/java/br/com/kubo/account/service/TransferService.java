package br.com.kubo.account.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.kubo.account.dto.TransferDTO;
import br.com.kubo.account.exception.InvalidAccountException;
import br.com.kubo.account.model.Account;
import br.com.kubo.account.model.Transaction;
import br.com.kubo.account.repo.AccountRepo;
import br.com.kubo.account.repo.TransactionRepo;
import jakarta.transaction.Transactional;

@Service
public class TransferService implements ITransferService {

    // Injetando dependencia
    private AccountRepo accountRepo;
    private TransactionRepo transactionRepo;

    //Construtor
    public TransferService(AccountRepo accountRepo, TransactionRepo transactionRepo) {
        super();
        this.accountRepo = accountRepo;
        this.transactionRepo = transactionRepo;
    }



    @Override
    @Transactional
    public Transaction transferValues(TransferDTO transferDto) {
        // TODO Auto-generated method stub
        Account src = accountRepo.findById(transferDto.debitAccountNumber())
                .orElseThrow(() -> new InvalidAccountException
                        ("Account #"+transferDto.debitAccountNumber()+" does not exists"));
        Account dst = accountRepo.findById(transferDto.creditAccountNumber())
                .orElseThrow(() -> new InvalidAccountException
                        ("Account #"+transferDto.creditAccountNumber()+ " does not exists"));


        dst.setBalance(dst.getBalance() + transferDto.amount());
        accountRepo.save(dst);
        System.out.println("Account "+dst.getNumber()+ " New Balance $ "+dst.getBalance());

        src.setBalance(src.getBalance() - transferDto.amount());
        accountRepo.save(src);
        System.out.println("Account "+src.getNumber()+ " New Balance $ "+src.getBalance());

        Transaction transaction = new Transaction();

        transaction.setDebitAccount(src);

        transaction.setCreditAccount(dst);

        transaction.setAmount(transferDto.amount());

        transaction.setTimestamp(LocalDateTime.now());

        return transactionRepo.save(transaction);
    }


}