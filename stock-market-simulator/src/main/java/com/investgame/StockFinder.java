package com.investgame;

import java.util.Scanner;

import com.google.gson.Gson;

import okhttp3.Request;
import okhttp3.Response;

public class StockFinder {

    //using shared OkHttp client from OkHttpUtil
    Scanner scan = new Scanner(System.in);
    Gson gson = new Gson();

    //get required ticker and apiKey for the url
    private String ticker;

    //stock used for parameter to buy and sell, as a StockDetails object
    private StockDetails stock;
    String url;



    //GENERATE STOCK INFO (price, name, etc.)
    public void stockInfo(String ticker) throws Exception {

        StockDetails[] stockDetails = getQuote(ticker);

        //getting information on the stock
        for ( ; ; ){
            //ask user if they want to know the info of the stock they wanted
            System.out.println("\n\nWhat information would you like to know about " + ticker + "? (enter number - ex. 11)");
            System.out.println("1. symbol\n2. name\n3. price\n4. percentage change\n5. change\n6. volume\n7. day low\n8. day high\n" +
            "9. year high\n10. year low\n11. market cap\n12. price (avg. 50 days)\n13. price (avg. 200 days)\n14. exchange"+
            "\n15. open price\n16. previous closing price\n17. timestamp\n");

            int num = scan.nextInt();
            
            switch (num) {
                case 1: System.out.println(stockDetails[0].getSymbol());
                    break;
                case 2: System.out.println(stockDetails[0].getName());
                    break;
                case 3: System.out.println(stockDetails[0].getPrice());
                    break;
                case 4: System.out.println(stockDetails[0].getChangePercentage());
                    break;
                case 5: System.out.println(stockDetails[0].getChange());
                    break;
                case 6: System.out.println(stockDetails[0].getVolume());
                    break;
                case 7: System.out.println(stockDetails[0].getDayLow());
                    break;
                case 8: System.out.println(stockDetails[0].getDayHigh());
                    break;
                case 9: System.out.println(stockDetails[0].getYearHigh());
                    break;
                case 10: System.out.println(stockDetails[0].getYearLow());
                    break;
                case 11: System.out.println(stockDetails[0].getMarketCap());
                    break;
                case 12: System.out.println(stockDetails[0].getPriceAvg50());
                    break;
                case 13: System.out.println(stockDetails[0].getPriceAvg200());
                    break;
                case 14: System.out.println(stockDetails[0].getExchange());
                    break;
                case 15: System.out.println(stockDetails[0].getOpen());
                    break;
                case 16: System.out.println(stockDetails[0].getPreviousClose());
                    break;
                case 17: System.out.println(stockDetails[0].getTimestamp());
                    break;
                default: System.out.println("Invalid. Enter again: ");
                    num = scan.nextInt();
                    break; 
            }

            //ask user if they want more info, or they want to break out of the loop
            scan.nextLine(); 
            
            while (true) {
                System.out.println("\n\nWould you like more information for " + ticker + "? (YES/NO)");
                String userInput = scan.nextLine().toUpperCase();

                if (userInput.equals("YES")) {
                    break; //break inner loop, continue outer loop
                } else if (userInput.equals("NO")) {
                    buyStock(stockDetails); //buy the stock
                    return; //exit the entire method
                } else {
                    System.out.println("Invalid input. Type either YES or NO.");
                }
            }
        }

    }



    //OBTAIN INFORMATION FROM FMP
    public StockDetails[] getQuote(String ticker) throws Exception {

        //makes the input all caps
        this.ticker = ticker;

        //url to be used on the web using ticker and apiKey
        this.url = "https://financialmodelingprep.com/stable/quote?symbol=" + this.ticker + "&apikey=" + Main.apiKey;

        //build a new request for each stock quote - sets URL target
        Request request = new Request.Builder().url(url).build();

        String stockResponse; //store the API response as a String variable

        //send out the request using okhttp - requires an exception if invalid
        try (Response response = OkHttpUtil.client.newCall(request).execute()) {
            System.out.println(response);
            
            //extract the body from the API response and convert the body into a String
            stockResponse = response.body().string();
        }

        //use Gson to extract data from JSON
        StockDetails[] stockDetails = gson.fromJson(stockResponse, StockDetails[].class);

        return stockDetails;
    }



    //PURCHASE A STOCK
    public void buyStock(StockDetails[] stock){
        

        //FIX THIS - USE INSTANCE OF PORTFOLIO IN MAIN (pass on objects)
        Portfolio portfolio = new Portfolio();

        while (true) {
            System.out.println("\nWould you like to buy " + ticker + "? (YES/NO)");
            String userInput = scan.nextLine().toUpperCase();

            if (userInput.equals("YES")) { //wants to buys stock

                System.out.println("\nCurrent share price of " + ticker + ": " + stock[0].getPrice()); //current share price
                System.out.println("Current cash balance: " + portfolio.getBalance()); //current cash balance
                System.out.println("How many shares would you like to purchase?"); 
                int shares = scan.nextInt(); //number of shares the user wants
                double sharePrice = stock[0].getPrice();
                double totalOrder = shares * sharePrice; //total value of purchase 

                if (totalOrder > portfolio.getBalance()) { //user is not able to buy stock
                    System.out.println("Insufficient funds. Returning to main menu.");
                    return;
                }

                else if (totalOrder <= portfolio.getBalance()) { //user is able to buy stock
                    System.out.println("Sufficient funds available. Generating order for " + ticker);
                    Portfolio.Holdings.add(stock); //add stock to portfolio
                    System.out.println(ticker + " has been purchased.");

                    //decrease cash balance
                    portfolio.decreaseCash(totalOrder); //decreases cash in portfolio and restates new cash balance
                    return;
                }

                break; //break inner loop, continue outer loop
            } else if (userInput.equals("NO")) {
                System.out.println(ticker + " will not be purchased.");
                return; //exit the entire method
            } else {
                System.out.println("Invalid input. Type either YES or NO.");
            }
        }
        
        

    }

    //SELL A STOCK FROM PORTFOLIO
    public void sellStock(StockDetails[] stock){


    }

    

}
