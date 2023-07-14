package com.cryptotal.service.core.exception;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException() {
        super("Account not found.");
    }
    public AccountNotFoundException(String s) {
        super(s);
    }
}
