package com.dinelink.exceptions;

public class SubCategoryNotFound extends RuntimeException {
    public SubCategoryNotFound(String message) {
        super(message);
    }
}
