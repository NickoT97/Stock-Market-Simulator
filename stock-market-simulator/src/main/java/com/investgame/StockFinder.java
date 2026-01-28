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

    String url;

    public void getQuote(String ticker) throws Exception {

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

        //print out the symbol from the first stock inputted in the array of StockDetails[]
        System.out.println(stockDetails[0].getSymbol());

    }
}
