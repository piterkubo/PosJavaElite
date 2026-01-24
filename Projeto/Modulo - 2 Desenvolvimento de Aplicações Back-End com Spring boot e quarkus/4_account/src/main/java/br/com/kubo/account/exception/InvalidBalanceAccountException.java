package br.com.kubo.account.exception;

public class InvalidBalanceAccountException extends RuntimeException{
    public InvalidBalanceAccountException(String msg) {
        super(msg);
    }

}