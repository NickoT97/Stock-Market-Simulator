package com.investgame;

import java.util.ArrayList;

public class Portfolio {
    
    //cash balance for the user
    private double cashBalance;

    public ArrayList<StockDetails> Holdings = new ArrayList<StockDetails>();

    //starting cash balance
    public void introCash(double num){
        cashBalance = num; //starting cash balance
        System.out.println("Cash balance: " + cashBalance); //cash balance
    }

    //get cash balance
    public double getBalance(){
        return cashBalance;
    }

    //set cash balance from data sabed
    public void setBalance(double cash){
        this.cashBalance = cash;
    }

    public void decreaseCash(double totalPurchase){
        cashBalance -= totalPurchase; //decreases cash in portfolio - used in buying a stock or withdrawal
        System.out.println("New cash balance: " + cashBalance); //new cash balance
    }

    public void increaseCash(double totalSale){
        cashBalance += totalSale; //increases cash in portfolio - used in selling a stock or deposit
        System.out.println("New cash balance: " + cashBalance); //new cash balance
    }

    

}
