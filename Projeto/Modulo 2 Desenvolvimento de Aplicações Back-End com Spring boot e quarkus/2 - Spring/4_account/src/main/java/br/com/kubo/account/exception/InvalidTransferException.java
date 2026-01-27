package br.com.kubo.account.exception;

public class InvalidTransferException extends RuntimeException{
    public InvalidTransferException(String msg) {
        super(msg);
    }

}