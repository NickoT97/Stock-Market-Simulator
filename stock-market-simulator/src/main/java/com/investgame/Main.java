package com.investgame;

import java.util.Scanner;

public class Main {
    
    //make apiKey static so all classes can access it
    public static String apiKey;

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        //INTRODUCTION
        System.out.println("Please enter your name: ");
        final String name = scan.nextLine();


        //enter API key
        System.out.println("\nPlease input your API key from FMP: ");
        apiKey = scan.nextLine();


        //create an instance of Portfolio
        Portfolio portfolio = new Portfolio();

        System.out.println("\nPlease enter starting cash balance: ");
        double num = scan.nextDouble();
        scan.nextLine();
        portfolio.introCash(num); //add cash

        //create an instance of StockFinder
        StockFinder stockFinder = new StockFinder();

        System.out.println("\nHello " + name + "! Welcome to the Stock Market Simulator.");

/* 
Choices for while true loop:

1. Portfolio balance
2. Portfolio holdings
3. Sell a stock
4. Allowable stocks to invest in
5. Stock info (includes purchase of a stock)
6. Quit the program and save data

*/

        while (true){ //the simulation starts on an infinite loop
            
            System.out.println("What would you like to do? Please type out one of the following numbers (ex. 2): ");
            System.out.println("1. Portfolio balance\n2. Portfolio holdings\n3. Sell a stock\n4. Allowable stocks to invest in\n5. Stock info (including purchase of a stock\n6. Quit program");
            int response = scan.nextInt();
            scan.nextLine();

            switch (response) {
                case 1: //portfolio balance
                    System.out.println("Portfolio balance: " + portfolio.getBalance());
                    break;
                case 2: //portfolio holdings
                    for (int i = 0; i < portfolio.Holdings.size(); i++){
                        System.out.println(portfolio.Holdings.get(i).getName() + " - " + portfolio.Holdings.get(i).getSymbol());
                    }
                    break;
                case 3: //sell a stock
                    stockFinder.sellStock(portfolio);
                    break;
                case 4: //allowable stocks to invest in
                    System.out.println("\nThere are a limited amount of stocks you can choose from: ");
                    System.out.println("\nAAPL, TSLA, AMZN, MSFT, NVDA, GOOGL, META, NFLX, JPM, V, BAC, PYPL, " +
                    "\nDIS, T, PFE, COST, INTC, KO, TGT, NKE, SPY, BA, BABA, XOM, " +
                    "\nWMT, GE, CSCO, VZ, JNJ, CVX, PLTR, SQ, SHOP, SBUX, SOFI, HOOD, " +
                    "\nRBLX, SNAP, AMD, UBER, FDX, ABBV, ETSY, MRNA, LMT, GM, F, LCID, " +
                    "\nVWO, SPYG, NOK, ROKU, VIAC, ATVI, BIDU, DOCU, ZM, PINS, TLRY, WBA, " +
                    "\nMGM, NIO, C, GS, WFC, ADBE, PEP, UNH, CARR, HCA, TWTR, BILI, SIRI, FUBO, RKT");
                    break;
                case 5: //stock info and purchasing a stock
                    //stock input
                    System.out.println("Please input a stock: ");
                    String ticker = scan.next().toUpperCase();
            
                    //call the stockInfo method
                    stockFinder.stockInfo(ticker, portfolio);
                    break;
                case 6: //quit the program
                    System.out.println("Thanks for playing. Hope to see you again soon.");
                    System.out.println("Quitting Stock Market Simulator...");
                    System.exit(0); //exit program
                    break;
                default: System.out.println("Invalid input. Please try again.");
                    break; 
            }
            
            
            
            
    
            }
   }
}
