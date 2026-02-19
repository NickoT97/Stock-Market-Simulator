package com.investgame;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson; 
import com.google.gson.reflect.TypeToken; 

public class Data {

    Gson gson = new Gson();

    public void saveData(Portfolio portfolio) { //save data for holdings and cash

        try (FileWriter stockWriter = new FileWriter("holdingsData.json")) { //write a new json file called holdingsData
            new Gson().toJson(portfolio.Holdings, stockWriter); //convert the data of Holdings into a JSON using FileWriter
        } catch (IOException e) {}

        try (FileWriter cashWriter = new FileWriter("cashData.json")) { //write a new json file called cashData
            new Gson().toJson(portfolio.getBalance(), cashWriter); //convert the data of Holdings into a JSON using FileWriter
        } catch (IOException e) {}

    } 

    public void loadData(Portfolio portfolio) { //load data for holdings and cash

        Type stockListType = new TypeToken<ArrayList<StockDetails>>(){}.getType(); //get the type of the ArrayList StockDetails

        try (FileReader stockReader = new FileReader("holdingsData.json")) { //read the json file called holdingsData
            
            ArrayList<StockDetails> loadedData = gson.fromJson(stockReader, stockListType); //create an ArrayList for the extracted data from the JSON

            if (loadedData != null){ //if the data is empty, it returns null
                portfolio.Holdings.addAll(loadedData); //add all the data from loadedData to Holdings
            }

        } catch (IOException e) {}

        try (FileReader cashReader = new FileReader("cashData.json")) { //read the json file called cashData
            
            Double loadedCash = gson.fromJson(cashReader, Double.class); 

            if (loadedCash != null){ //if the data is empty, it returns null
                portfolio.setBalance(loadedCash); //set the cash balance in portfolio
            }

        } catch (IOException e) {}

    } 

}