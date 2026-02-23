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

        //create an instance of Portfolio and Data
        Portfolio portfolio = new Portfolio();
        Data data = new Data();

        //CHECK IF THERE IS SAVED DATA AVAILABLE
        if (data.hasSavedData()) {
            System.out.println("\nSaved data found! Loading previous game..."); //state saved data is found
            data.loadData(portfolio); //load the data
            System.out.println("Game loaded successfully. Cash balance: " + portfolio.getBalance());
        } else {
            System.out.println("\nNo saved data found. Starting a new game..."); //state no saved data is found
            System.out.println("\nPlease enter starting cash balance: ");
            double num = scan.nextDouble();
            scan.nextLine();
            portfolio.introCash(num); //add cash
        }

        //enter API key
        System.out.println("\nPlease input your API key from FMP: ");
        apiKey = scan.nextLine();

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
            
            System.out.println("\nWhat would you like to do? Please type out one of the following numbers (ex. 2): ");
            System.out.println("1. Portfolio cash balance\n2. Portfolio holdings\n3. Sell a stock\n4. Allowable stocks to invest in\n5. Stock info (including purchase of a stock)\n6. Quit program and save data\n7. Delete saved data and quit program");
            int response = scan.nextInt();
            scan.nextLine();

            switch (response) {
                case 1: //portfolio cash balance
                    System.out.println("Portfolio cash balance: " + portfolio.getBalance());

                    while (true){
                        System.out.println("\nWould you like to make a deposit/withdrawal? (YES/NO)");
                        String userAnswer1 = scan.nextLine().toUpperCase();
                        
                        if (userAnswer1.equals("YES")){
                            
                            while (true){
                                System.out.println("\nWould you like to make a deposit or a withdrawal?");
                                String userAnswer2 = scan.nextLine().toUpperCase();

                                if (userAnswer2.equals("DEPOSIT")){ //user wants to make a deposit
                                
                                    System.out.println("\nHow much would you like to deposit?");

                                    double userAmount = scan.nextDouble(); //get deposit amount
                                    scan.nextLine(); //consume newline
                                    
                                    portfolio.increaseCash(userAmount); //deposit money into balance
                                    break;
                                }

                                else if (userAnswer2.equals("WITHDRAWAL")){ //user wants to make a withdrawal
                                
                                    System.out.println("\nHow much would you like to withdraw?");

                                    double userAmount = scan.nextDouble(); //get withdrawal amount
                                    scan.nextLine(); //consume newline
                                    
                                    portfolio.decreaseCash(userAmount); //withdraw money from balance
                                    break;
                                }

                                else { //neither withdrawal or deposit entered
                                    System.out.println("\nInvalid input. Try again...");
                                }
                            }
                            break; //exit outer loop after transaction
                        }

                        else if(userAnswer1.equals("NO")){
                            System.out.println("\nReturning to main menu.");
                            break;
                        }

                        else {
                            System.out.println("\nInvalid input. Try again...");
                        }
                    }

                    break;
                case 2: //portfolio holdings
                    stockFinder.updateHoldingsData(portfolio); 
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
                    System.out.println("\nPlease input a stock: ");
                    String ticker = scan.nextLine().toUpperCase();
            
                    //call the stockInfo method
                    stockFinder.stockInfo(ticker, portfolio);
                    break;
                case 6: //quit the program and save data
                    data.saveData(portfolio); //save data
                    System.out.println("\nGame data saved successfully!");
                    System.out.println("Thanks for playing. Hope to see you again soon.");
                    System.out.println("Quitting Stock Market Simulator...");
                    System.exit(0); //exit program
                    break;
                case 7: //delete saved data and quit the program 
                    data.deleteSavedData(); //delete data
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
