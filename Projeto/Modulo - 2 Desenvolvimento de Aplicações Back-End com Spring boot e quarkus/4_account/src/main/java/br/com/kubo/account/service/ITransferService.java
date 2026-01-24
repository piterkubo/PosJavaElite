package br.com.kubo.account.service;

import br.com.kubo.account.dto.TransferDTO;
import br.com.kubo.account.model.Transaction;

public interface ITransferService {
    public Transaction transferValues(TransferDTO transferDto);

}