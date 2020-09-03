package com.yingglish.bankproject;

public class OverdraftException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    //赤字
    private double deficit;

    public double getDeficit() {
        return deficit;
    }

    public OverdraftException(String message, double deficit) {
        super(message);
        this.deficit = deficit;
    }
}
