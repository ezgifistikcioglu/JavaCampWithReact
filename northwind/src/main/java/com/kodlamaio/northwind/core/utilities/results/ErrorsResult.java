package com.kodlamaio.northwind.core.utilities.results;

public class ErrorsResult extends Result {
    public ErrorsResult() {
        super(false);
    }

    public ErrorsResult(String message) {
        super(false, message);
    }
}
