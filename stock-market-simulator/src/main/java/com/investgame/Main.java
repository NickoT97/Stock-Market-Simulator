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

/* 
Choices for while true loop:

1. Portfolio balance
2. Portfolio holdings
3. Sell a stock
3. Allowable stocks to invest in
4. Stock info (includes purchase of a stock)
5. Quit the program & save data

*/

        System.out.println("\nHello " + name + "! Welcome to the Stock Market Simulator.");
        System.out.println("\nThere are a limited amount of stocks you can shoose from: ");
        System.out.println("\nAAPL, TSLA, AMZN, MSFT, NVDA, GOOGL, META, NFLX, JPM, V, BAC, PYPL, " +
        "\nDIS, T, PFE, COST, INTC, KO, TGT, NKE, SPY, BA, BABA, XOM, " +
        "\nWMT, GE, CSCO, VZ, JNJ, CVX, PLTR, SQ, SHOP, SBUX, SOFI, HOOD, " +
        "\nRBLX, SNAP, AMD, UBER, FDX, ABBV, ETSY, MRNA, LMT, GM, F, LCID, " +
        "\nVWO, SPYG, NOK, ROKU, VIAC, ATVI, BIDU, DOCU, ZM, PINS, TLRY, WBA, " +
        "\nMGM, NIO, C, GS, WFC, ADBE, PEP, UNH, CARR, HCA, TWTR, BILI, SIRI, FUBO, RKT");


        //stock input
        System.out.println("Please input a stock: ");

        String ticker = scan.next().toUpperCase();
        
        //create an instance of StockFinder
        StockFinder stockFinder = new StockFinder();
        
        //call the stockInfo method
        stockFinder.stockInfo(ticker, portfolio);
   }
}
