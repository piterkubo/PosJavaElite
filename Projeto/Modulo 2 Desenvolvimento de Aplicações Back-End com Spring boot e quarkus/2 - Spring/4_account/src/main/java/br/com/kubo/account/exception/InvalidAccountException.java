package br.com.kubo.account.exception;

public class InvalidAccountException extends RuntimeException{
    public InvalidAccountException(String msg) {
        super(msg);
    }

}