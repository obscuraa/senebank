package com.group.senebank.exception;

public class SendTransactionException extends RuntimeException {

    public SendTransactionException(String message, int id) {
        super(String.format(message, id));
    }
}
