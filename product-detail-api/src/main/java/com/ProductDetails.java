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
@CrossOrigin("*")
public class ProductDetails {

    private static final String SELLER_SERVICE = System.getenv("SELLER_SERVICE");
    private static final String SELLER_PORT = System.getenv("SELLER_PORT");

    @GetMapping(value="/details/washingmachine") //call using http://localhost:9081/details/washingmachine
    public JSONObject getWashingMachine(){
        String sellerURL = "http://"+SELLER_SERVICE+":"+SELLER_PORT+"/seller/washingmachine";

        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println(sellerURL);
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

        JSONObject respJSON = getResponse(sellerURL);
        respJSON.put("Product","Washing Machine");

        return respJSON;
    }

    @GetMapping(value="/details/mobilephone") //call using http://localhost:9081/details/mobilephone
    public JSONObject getMobilePhone(){
        String sellerURL = "http://"+SELLER_SERVICE+":"+SELLER_PORT+"/seller/mobilephone";

        JSONObject respJSON = getResponse( sellerURL );
        respJSON.put("Product","Mobile Phone");

        return respJSON;
    }

    @GetMapping(value="/details/laptop") //call using http://localhost:9081/details/laptop
    public JSONObject getLaptop(){
        String sellerURL = "http://"+SELLER_SERVICE+":"+SELLER_PORT+"/seller/laptop";

        JSONObject respJSON = getResponse(sellerURL);
        respJSON.put("Product","Laptop");

        return respJSON;
    }

    private JSONObject getResponse(String rstURL){

        JSONObject ret = new JSONObject();

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
