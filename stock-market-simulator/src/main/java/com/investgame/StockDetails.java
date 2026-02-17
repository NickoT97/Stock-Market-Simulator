package com.investgame;

//details of the JSON file and getter methods

public class StockDetails {
    private String symbol;
    private String name;
    private double price; //purchase price
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
    private double currentPrice = 0; //updated share price
    private double totalStockCost = 0; //total value of stock - used to get avg share price

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

    public int getNumSharesOwned(){
        return numSharesOwned;
    }

    public double getCurrentPrice(){
        return currentPrice;
    }

    public double getTotalStockCost(){
        return totalStockCost;
    }

    public double getAvgSharePrice(){
        if (numSharesOwned == 0){
            return 0;
        }

        return totalStockCost / numSharesOwned; //total cost of stock (share price * shares) divided by total shares owned

    }

    public void addPurchase(int shareCount, double pricePerShare){ //used for buying a stock
        numSharesOwned += shareCount; //total shares owned
        totalStockCost += (shareCount * pricePerShare); //add the total cost of the stock
        System.out.println("Bought " + shareCount + " shares. Updated amount of shares for " + symbol + ": " + numSharesOwned); //new amount of shares
        System.out.println("New average share price: $" + getAvgSharePrice()); //new average share price
    }

    public void removeSale(int shareCount){ //used for selling a stock
        if (shareCount < numSharesOwned){
            double avgPrice = getAvgSharePrice(); //avg price of stock
            numSharesOwned -= shareCount; //reduce total shares owned
            totalStockCost -= (shareCount * avgPrice); //update total cost of the stock owned
            System.out.println("Sold " + shareCount + " shares. Updated amount of shares for " + symbol + ": " + numSharesOwned); //new amount of shares
        }
    }
    public void updateCurrentPrice(double newPrice){ //set current price when: 1. buy a stock and/or 2. updating holdings
        currentPrice = newPrice;
    }

}