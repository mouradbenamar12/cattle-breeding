package com.cattle.breeding.exception;


public class CattleNotFoundException extends RuntimeException {
    public CattleNotFoundException(String message) {
        super(message);
    }
}