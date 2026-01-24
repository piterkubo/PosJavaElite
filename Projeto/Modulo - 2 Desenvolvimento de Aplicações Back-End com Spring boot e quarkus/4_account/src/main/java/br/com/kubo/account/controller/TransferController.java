package br.com.kubo.account.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.kubo.account.dto.TransferDTO;
import br.com.kubo.account.model.Transaction;
import br.com.kubo.account.service.ITransferService;

@RestController
public class TransferController {

    private ITransferService service;

    public TransferController(ITransferService service) {
        super();
        this.service = service;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transferValue(@RequestBody TransferDTO dto){
        return ResponseEntity.status(201).body(service.transferValues(dto));
    }

}