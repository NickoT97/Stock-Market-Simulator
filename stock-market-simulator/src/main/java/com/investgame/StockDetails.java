package com.investgame;

//details of the JSON file and getter methods

public class StockDetails {
    private String symbol;
    private String name;
    private double price;
    private double changePercentage;
    private double change;
    private double volume;
    private double dayLow;
    private double dayHigh;
    private double yearHigh;
    private double yearLow;
    private double marketCap;
    private double priceAvg50;
    private double priceAvg200;
    private String exchange;
    private double open;
    private double previousClose;
    private double timestamp;
    private int numSharesOwned = 0;

    public String getSymbol(){
        return symbol;
    }
    
    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }
    
    public double getChangePercentage(){
        return changePercentage;
    }

    public double getChange(){
        return change;
    }

    public double getVolume(){
        return volume;
    }

    public double getDayLow(){
        return dayLow;
    }

    public double getDayHigh(){
        return dayHigh;
    }

    public double getYearHigh(){
        return yearHigh;
    }

    public double getYearLow(){
        return yearLow;
    }

    public double getMarketCap(){
        return marketCap;
    }

    public double getPriceAvg50(){
        return priceAvg50;
    }

    public double getPriceAvg200(){
        return priceAvg200;
    }

    public String getExchange(){
        return exchange;
    }

    public double getOpen(){
        return open;
    }

    public double getPreviousClose(){
        return previousClose;
    }

    public double getTimestamp(){
        return timestamp;
    }

    public int numSharesOwned(){
        return numSharesOwned;
    }

    public void increaseShares(String symbol, int shareCount){ //used for buying a stock
        numSharesOwned += shareCount;
        System.out.println("Updated amount of shares for " + symbol + ": " + numSharesOwned); //new amount of shares
    }

    public void decreaseShares(String symbol, int shareCount){ //used for selling a stock
        numSharesOwned -= shareCount;
        System.out.println("Updated amount of shares for " + symbol + ": " + numSharesOwned); //new amount of shares
    }

}