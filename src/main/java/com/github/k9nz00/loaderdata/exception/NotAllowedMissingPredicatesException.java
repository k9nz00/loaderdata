package com.github.k9nz00.loaderdata.exception;

public class NotAllowedMissingPredicatesException extends RuntimeException {
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}