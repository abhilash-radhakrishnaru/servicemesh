package com;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@CrossOrigin("*")
public class SellerDetails {
    private static final String PRICER_SERVICE = System.getenv("PRICER_SERVICE");
    private static final String PRICER_PORT = System.getenv("PRICER_PORT");
    private static final String STOCK_SERVICE = System.getenv("STOCK_SERVICE");
    private static final String STOCK_PORT = System.getenv("STOCK_PORT");

    @GetMapping(value="/seller/washingmachine") //call using http://localhost:9081/seller/washingmachine
    public JSONObject getWashingMachineSeller(){
        String pricerURL = "http://"+PRICER_SERVICE+":"+PRICER_PORT+"/pricer/washingmachine";
        String stockURL = "http://"+STOCK_SERVICE+":"+STOCK_PORT+"/stock/washingmachine";

        System.out.println("##################################################################");
        System.out.println(pricerURL);
        System.out.println(stockURL);
        System.out.println("##################################################################");

        JSONObject respJSON = new JSONObject();
        respJSON.put("Seller","MKRetailers");
        respJSON.put("Stock",getResponse(stockURL));
        respJSON.put("Price",getResponse(pricerURL));
        return respJSON;
    }

    @GetMapping(value="/seller/mobilephone") //call using http://localhost:9081/seller/phone
    public JSONObject getMobilePhoneSeller(){
        String pricerURL = "http://"+PRICER_SERVICE+":"+PRICER_PORT+"/pricer/mobilephone";
        String stockURL = "http://"+STOCK_SERVICE+":"+STOCK_PORT+"/stock/mobilephone";

        JSONObject respJSON = new JSONObject();
        respJSON.put("Seller","CloudTail");
        respJSON.put("Stock",getResponse(stockURL));
        respJSON.put("Price",getResponse(pricerURL));
        return respJSON;
    }

    @GetMapping(value="/seller/laptop") //call using http://localhost:9081/seller/laptop
    public JSONObject getLaptopSeller(){
        String pricerURL = "http://"+PRICER_SERVICE+":"+PRICER_PORT+"/pricer/laptop";
        String stockURL = "http://"+STOCK_SERVICE+":"+STOCK_PORT+"/stock/laptop";

        JSONObject respJSON = new JSONObject();
        respJSON.put("Seller","WSRetail");
        respJSON.put("Stock",getResponse(stockURL));
        respJSON.put("Price",getResponse(pricerURL));
        return respJSON;
    }


    private String getResponse(String rstURL){
        StringBuilder ret = new StringBuilder();

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

            String output;

            while ((output = br.readLine()) != null) {
                ret.append( output );
            }

            conn.disconnect();
        }catch(Exception e){
            e.printStackTrace();
        }

        return ret.toString();
    }
}