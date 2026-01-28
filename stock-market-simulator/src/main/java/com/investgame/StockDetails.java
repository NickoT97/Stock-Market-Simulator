package com.investgame;

//details of the JSON file and getter methods

public class StockDetails {
    private String symbol;
    private String name;
    private Number price;
    private Number changePercentage;
    private Number change;
    private Number volume;
    private Number dayLow;
    private Number dayHigh;
    private Number yearHigh;
    private Number yearLow;
    private Number marketCap;
    private Number priceAvg50;
    private Number priceAvg200;
    private String exchange;
    private Number open;
    private Number previousClose;
    private Number timestamp;

    public String getSymbol(){
        return symbol;
    }

    public String getName(){
        return name;
    }

    public Number getPrice(){
        return price;
    }
    
    public Number getChangePercentage(){
        return changePercentage;
    }

    public Number getChange(){
        return change;
    }

    public Number getVolume(){
        return volume;
    }

    public Number getDayLow(){
        return dayLow;
    }

    public Number getDayHigh(){
        return dayHigh;
    }

    public Number getYearHigh(){
        return yearHigh;
    }

    public Number getYearLow(){
        return yearLow;
    }

    public Number getMarketCap(){
        return marketCap;
    }

    public Number getPriceAvg50(){
        return priceAvg50;
    }

    public Number getPriceAvg200(){
        return priceAvg200;
    }

    public String getExchange(){
        return exchange;
    }

    public Number getOpen(){
        return open;
    }

    public Number getPreviousClose(){
        return previousClose;
    }

    public Number getTimestamp(){
        return timestamp;
    }
}