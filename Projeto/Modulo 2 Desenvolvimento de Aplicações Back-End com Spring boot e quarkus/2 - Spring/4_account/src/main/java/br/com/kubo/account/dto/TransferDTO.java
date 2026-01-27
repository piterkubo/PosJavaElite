package br.com.kubo.account.dto;

public record TransferDTO(Integer debitAccountNumber, Integer creditAccountNumber, Double amount) {

}