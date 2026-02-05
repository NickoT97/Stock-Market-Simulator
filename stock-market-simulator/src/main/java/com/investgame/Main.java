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

        System.out.println("\nHello " + name + "! Welcome to the Stock Market Simulator.");
        

        //stock input
        System.out.println("Please input a stock: ");

        String ticker = scan.nextLine().toUpperCase();
        
        //create an instance of StockFinder
        StockFinder stockFinder = new StockFinder();
        
        //call the stockInfo method
        stockFinder.stockInfo(ticker, portfolio);
   }
}
