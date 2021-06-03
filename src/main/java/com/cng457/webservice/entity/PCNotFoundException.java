package com.cng457.webservice.entity;

public class PCNotFoundException extends RuntimeException {

    public PCNotFoundException(Long id) {
        super("Could not find pc " + id);
    }
}
