package com.example.exeptions;

public class BlogException extends RuntimeException {

    public BlogException(String message, Throwable t) {
        super(message, t);
    }

}
