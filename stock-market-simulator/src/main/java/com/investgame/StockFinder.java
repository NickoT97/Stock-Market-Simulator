package com.investgame;

import java.util.Scanner;


public class StockFinder {
   Scanner scan = new Scanner(System.in);

   //private String apiKey = System.getenv("FMP_API_KEY");
   private String ticker;
   String url;


   public StockFinder(String ticker){
       System.out.println("Please input a ticker symbol for a stock (ex. AAPL): ");
       this.ticker = scan.nextLine().toUpperCase();
       //this.url = "https://financialmodelingprep.com/stable/governance-executive-compensation?symbol=" + this.ticker + "&apikey=" + apiKey;


    

   }
}
