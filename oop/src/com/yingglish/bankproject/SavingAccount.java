package com.yingglish.bankproject;

public class SavingAccount extends Account{
    //利率
    private double interestRate;

    public SavingAccount(double init_balance, double interestRate) {
        super(init_balance);
        this.interestRate = interestRate;
    }
}
