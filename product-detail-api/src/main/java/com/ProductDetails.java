package com;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.json.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;

@RestController
public class ProductDetails {

    private static final String SELLER_SERVICE = System.getenv("SELLER_SERVICE");
    private static final String SELLER_PORT = System.getenv("SELLER_PORT");

    @GetMapping(value="/washingmachine") //call using http://localhost:9081/washingmachine
    public JSONObject getWashingMachine(){
        String sellerURL = "http://"+SELLER_SERVICE+":"+SELLER_PORT+"/wseller";

        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println(sellerURL);
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

        JSONObject respJSON = getResponse(sellerURL);
        respJSON.put("Product","Washing Machine");

        return respJSON;
    }

    @GetMapping(value="/mobilephone") //call using http://localhost:9081/mobilephone
    public JSONObject getMobilePhone(){
        String sellerURL = "http://"+SELLER_SERVICE+":"+SELLER_PORT+"/mseller";

        JSONObject respJSON = getResponse( sellerURL );
        respJSON.put("Product","Mobile Phone");

        return respJSON;
    }

    @GetMapping(value="/laptop") //call using http://localhost:9081/laptop
    public JSONObject getLaptop(){
        String sellerURL = "http://"+SELLER_SERVICE+":"+SELLER_PORT+"/lseller";

        JSONObject respJSON = getResponse(sellerURL);
        respJSON.put("Product","Laptop");

        return respJSON;
    }

    private JSONObject getResponse(String rstURL){

        JSONObject ret = null;

        try {
            URL url = new URL(rstURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            StringBuilder output = new StringBuilder();
            String outStr;

            while ((outStr = br.readLine()) != null) {
                output.append( outStr );
            }
            JSONParser parser = new JSONParser();

            ret = (JSONObject)parser.parse( output.toString() );

            conn.disconnect();
        }catch(Exception e){
            e.printStackTrace();
        }

        return ret;
    }
}
