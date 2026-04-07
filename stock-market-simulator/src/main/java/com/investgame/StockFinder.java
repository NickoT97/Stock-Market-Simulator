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
    public void stockInfo(String ticker, Portfolio portfolio) throws Exception {

        StockDetails[] stockDetails = getQuote(ticker); //put the quote into stockDetails

        if (stockDetails == null || stockDetails.length == 0) { //if there is an invalid stock input, return to main menu
            System.out.println("Invalid stock input. Returning to main menu.\n");
            return;
        }

        //getting information on the stock
        for ( ; ; ){
            //ask user if they want to know the info of the stock they wanted
            System.out.println("\n\nWhat information would you like to know about " + ticker + "? (enter number - ex. 11)");
            System.out.println("1. symbol\n2. name\n3. price\n4. percentage change\n5. change\n6. volume\n7. day low\n8. day high\n" +
            "9. year high\n10. year low\n11. market cap\n12. price (avg. 50 days)\n13. price (avg. 200 days)\n14. exchange"+
            "\n15. open price\n16. previous closing price\n17. timestamp\n");

            int num;

            if (scan.hasNextInt()) { //gives true/false depending if the input is an integer or not
                num = scan.nextInt(); //input gets assigned to num
                scan.nextLine(); //consume newline
            } else {
                String invalidInput = scan.next(); //capture the invalid input directly
                scan.nextLine(); //consume the rest of the line
                System.out.println("Invalid input: '" + invalidInput + "'. Please enter a number from 1 to 17.");
                continue;
            }
            
            switch (num) {
                case 1: System.out.println("Symbol: " + stockDetails[0].getSymbol());
                    break;
                case 2: System.out.println("Name: " + stockDetails[0].getName());
                    break;
                case 3: System.out.println("Price: $ " + stockDetails[0].getPrice());
                    break;
                case 4: System.out.println("Percentage Change: " + stockDetails[0].getChangePercentage() + "%");
                    break;
                case 5: System.out.println("Change: " + stockDetails[0].getChange());
                    break;
                case 6: System.out.println("Volume: " + stockDetails[0].getVolume());
                    break;
                case 7: System.out.println("Day Low: $" + stockDetails[0].getDayLow());
                    break;
                case 8: System.out.println("Day High: $" + stockDetails[0].getDayHigh());
                    break;
                case 9: System.out.println("Year High: $" + stockDetails[0].getYearHigh());
                    break;
                case 10: System.out.println("Year Low: $" + stockDetails[0].getYearLow());
                    break;
                case 11: System.out.println("Market Cap: $" + stockDetails[0].getMarketCap());
                    break;
                case 12: System.out.println("Avg Price (50 days): $" + stockDetails[0].getPriceAvg50());
                    break;
                case 13: System.out.println("Avg Price (200 days): $" + stockDetails[0].getPriceAvg200());
                    break;
                case 14: System.out.println("Exchange: " + stockDetails[0].getExchange());
                    break;
                case 15: System.out.println("Open Price: $" + stockDetails[0].getOpen());
                    break;
                case 16: System.out.println("Previous Close Price: $" + stockDetails[0].getPreviousClose());
                    break;
                case 17: System.out.println("Timestamp: " + stockDetails[0].getTimestamp());
                    break;
                default: System.out.println("Invalid input. Please try again.");
                    break; 
            }

            //ask user if they want more info, or they want to break out of the loop
            
            while (true) {
                System.out.println("\n\nWould you like more information for " + ticker + "? (YES/NO)");
                String userInput = scan.nextLine().toUpperCase();

                if (userInput.equals("YES")) {
                    break; //break inner loop, continue outer loop
                } else if (userInput.equals("NO")) {
                    buyStock(stockDetails[0], portfolio); //buy the stock
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

        //send out the request using okhttp
        try (Response response = OkHttpUtil.client.newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null) { //if the stock input is invalid, make it null
                return null;
            }
            
            System.out.println(response);
            
            //extract the body from the API response and convert the body into a String
            stockResponse = response.body().string();
        }

        //use Gson to extract data from JSON
        StockDetails[] stockDetails = gson.fromJson(stockResponse, StockDetails[].class);

        if (stockDetails == null || stockDetails.length == 0) { //if the return is null or empty due to an invalid stock input
            return null;
        }

        return stockDetails;
    }



    //PURCHASE A STOCK
    public void buyStock(StockDetails stock, Portfolio portfolio){

        while (true) {
            System.out.println("\nWould you like to buy " + ticker + "? (YES/NO)");
            String userInput = scan.next().toUpperCase();

            if (userInput.equals("YES")) { //wants to buys stock

                System.out.println("\nCurrent share price of " + ticker + ": " + stock.getPrice()); //current share price
                System.out.println("Current cash balance: " + portfolio.getBalance()); //current cash balance
                System.out.println("\nHow many shares would you like to purchase?"); 
                int shares; //number of shares the user wants

                if (scan.hasNextInt()) { //gives true/false depending if the input is an integer or not
                    shares = scan.nextInt(); //input gets assigned to shares
                    scan.nextLine(); //consume newline
                } else {
                    String invalidInput = scan.next(); //capture the invalid input directly
                    scan.nextLine(); //consume the rest of the line
                    System.out.println("Invalid input: '" + invalidInput + "'. Restarting process. Please enter a number >= 0.");
                    continue;
                }


                if (shares <= 0){
                    System.out.println("Invalid amount of shares. Restarting process.");
                    continue;
                }

                double sharePrice = stock.getPrice();
                double totalOrder = shares * sharePrice; //total value of purchase 

                if (totalOrder > portfolio.getBalance()) { //user is not able to buy stock
                    System.out.println("Insufficient funds. Restarting process.");
                }

                else if (totalOrder <= portfolio.getBalance()) { //user is able to buy stock
                    System.out.println("\nSufficient funds available. Generating buy order for " + ticker);

                    boolean alreadyOwnedStock = false;
                    StockDetails existingStock = null;

                    for (int i = 0; i < portfolio.Holdings.size(); i++){ //check if stock is already owned
                        StockDetails arrayStock = portfolio.Holdings.get(i);
                        if (ticker.equals(arrayStock.getSymbol())){
                            alreadyOwnedStock = true; //user owns the stock
                            existingStock = arrayStock;
                            break;
                        }
                    }

                    if (alreadyOwnedStock == false){ //user doesn't own stock, add to portfolio - first time buying    
                        stock.addPurchase(shares, sharePrice);
                        portfolio.Holdings.add(stock); //add to portfolio
                        System.out.println(ticker + " has been added to your portfolio.");
                    }
                    else { //user already owns stock
                        existingStock.addPurchase(shares, sharePrice);
                        System.out.println(shares + " shares has been added to " + ticker + ".");
                    }

                    //decrease cash balance
                    portfolio.decreaseCash(totalOrder); //decreases cash in portfolio and restates new cash balance

                    return;
                }
            } else if (userInput.equals("NO")) {
                System.out.println(ticker + " will not be purchased.");
                return; //exit the entire method
            } else {
                System.out.println("Invalid input. Type either YES or NO.");
            }
        }
        
        

    }

    //SELL A STOCK FROM PORTFOLIO
    public void sellStock(Portfolio portfolio) throws Exception{

        while (true) {
            System.out.println("\nWhich stock would you like to sell? ");
            String userInput = scan.next().toUpperCase();

            boolean hasStock = false; //check if user has the stock

            for (int i = 0; i < portfolio.Holdings.size(); i++){

                StockDetails stock = portfolio.Holdings.get(i); //refer to stock at position i in holdings arraylist

                if (userInput.equals(stock.getSymbol())){ //user has the stock in their portfolio
                    System.out.println("User has " + userInput + " in their portfolio.");
                    hasStock = true;

                    //update current price to the current market price
                    StockDetails[] updatedData = getQuote(userInput);
                    double currentPrice = updatedData[0].getPrice(); //get the current market price from the quote
                    stock.updateCurrentPrice(currentPrice); //update stocks current market price

                    System.out.println("\nShares owned of " + userInput + ": " + stock.getNumSharesOwned()); //show user share count
                    System.out.println("\nAverage purchase price of each share of " + userInput + ": $" + stock.getAvgSharePrice()); //show user avg purchase price
                    System.out.println("\nCurrent market price price of " + userInput + ": $" + stock.getCurrentPrice()); //show user the current market share price

                    System.out.println("\nHow many shares would you like to sell of " + userInput + "?"); //ask user for how many shares they'd like to sell
                    int sellShares; //get amount of shares to sell

                    if (scan.hasNextInt()) { //gives true/false depending if the input is an integer or not
                        sellShares = scan.nextInt(); //input gets assigned to sellShares
                        scan.nextLine(); //consume newline
                    } else {
                        String invalidInput = scan.next(); //capture the invalid input directly
                        scan.nextLine(); //consume the rest of the line
                        System.out.println("Invalid input: '" + invalidInput + "'. Please enter a number >= 0.");
                        continue;
                    }

                    if (sellShares <= 0){
                        System.out.println("Invalid amount of shares. Try again.");
                        continue;
                    }

                    double saleTotal = sellShares * stock.getCurrentPrice(); //total sell order

                    if (sellShares > stock.getNumSharesOwned()) { //user is not able to sell stock
                        System.out.println("Insufficient amount of shares available. Restarting process.");
                        break; //exit for loop and restart the while loop since hasStock is still true, not false
                    }

                    else if (sellShares <= stock.getNumSharesOwned()) { //user is able to sell stock
                        System.out.println("\nSufficient shares available. Generating sell order for " + userInput);

                        //sale transaction in portfolio
                        stock.removeSale(sellShares); 

                        //if shares of a company is 0, remove from holdings
                        if (stock.getNumSharesOwned() == 0){
                            portfolio.Holdings.remove(i); //remove stock from portfolio
                        }

                        System.out.println(sellShares + " shares of " + userInput + " has been sold."); //order confirmation

                        //increase cash balance
                        portfolio.increaseCash(saleTotal); //decreases cash in portfolio and restates new cash balance

                        return; //exit method after successful transaction
                    }
                }
            }
            
            if (!hasStock) { //check if stock wasn't found
                System.out.println("User does not have " + userInput + " in their portfolio.");
                System.out.println("Returning to main menu.");
                return;
            }

        }

    }

    public void updateHoldingsData(Portfolio portfolio) throws Exception { //update current price in portfolio

        if (portfolio.Holdings.size() == 0){ //no stocks owned
            System.out.println("You have no stocks owned. Returning to main menu."); 
            return;
        }
        
        else{ //at least one stock is owned
            
            double totalProfitorLoss = 0;
            
            for (int i = 0; i < portfolio.Holdings.size(); i++){ //iterate through portfolio
                StockDetails stock = portfolio.Holdings.get(i); //stock in portfolio
                String stockName = stock.getSymbol(); //stock symbol in portfolio

                StockDetails[] newStockDetails = getQuote(stockName); //updated quote for stock at index i

                double newPrice = newStockDetails[0].getPrice(); //new price of current stock

                stock.updateCurrentPrice(newPrice); //set the new price for the one in holdings

                System.out.println("\n" + stock.getName() + " - " + stock.getSymbol()); //state name and symbol
                System.out.printf("Current price: $%.2f%n", stock.getCurrentPrice()); //state current price
                System.out.printf("Average purchase price: $%.2f%n", stock.getAvgSharePrice()); //state avg purchase price

                double intProfitOrLoss = (stock.getCurrentPrice() - stock.getAvgSharePrice()) * stock.getNumSharesOwned();

                System.out.printf("Unrealized net gain/loss: $%.2f%n", intProfitOrLoss); //state unrealized net gain/loss

                System.out.println("\n----------------------------------------------"); //separate stocks

                totalProfitorLoss += intProfitOrLoss;

            }

            System.out.printf("\nTotal Portfolio net gain/loss: $%.2f%n", totalProfitorLoss); //state total portfolio profits/losses

            System.out.println("\n----------------------------------------------"); //separate stocks

        }
    }
    

}
