package com;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/seller")
public class SellerDetails {

    /*@Autowired
    private RestTemplate restTemplate;

    private final static String[] headers_to_propagate = { //this is for distributed tracing
            "x-request-id", //used for trace & log sampling decisions in Istio. Constant for entire request
            "x-ot-span-context", //// Lightstep tracing header. Propagate this if you use lightstep tracing
            // Datadog tracing header. Propagate these headers if you use Datadog tracing
            "x-datadog-trace-id",
            "x-datadog-parent-id",
            "x-datadog-sampling-priority",
            // W3C Trace Context. Compatible with OpenCensusAgent and Stackdriver Istio configurations
            "traceparent",
            "tracestate",
            // Cloud trace context. Compatible with OpenCensusAgent and Stackdriver Istio configurations
            "x-cloud-trace-context",
            // Grpc binary trace context. Compatible with OpenCensusAgent and Stackdriver Istio configurations.
            "grpc-trace-bin",
            // b3 trace headers. Compatible with Zipkin, OpenCensusAgent, and Stackdriver Istio configurations
            "x-b3-traceid", //constant for entire request
            "x-b3-spanid", //changes for each service in the request
            "x-b3-parentspanid", //changes for each service in a request
            "x-b3-sampled", //constant for entire request
            "x-b3-flags", //optional
            // Application-specific headers to forward if any
            "end-user",
            "user-agent",
    };*/

    private static final String PRICER_SERVICE = System.getenv("PRICER_SERVICE");
    private static final String PRICER_PORT = System.getenv("PRICER_PORT");
    private static final String STOCK_SERVICE = System.getenv("STOCK_SERVICE");
    private static final String STOCK_PORT = System.getenv("STOCK_PORT");

    @GetMapping("/washingmachine") //call using http://localhost:9081/seller/washingmachine
    public JSONObject getWashingMachineSeller(@RequestHeader MultiValueMap<String, String> headers){
        String pricerURL = "http://"+PRICER_SERVICE+":"+PRICER_PORT+"/pricer/washingmachine";
        String stockURL = "http://"+STOCK_SERVICE+":"+STOCK_PORT+"/stock/washingmachine";

        System.out.println(pricerURL);
        System.out.println(stockURL);

        JSONObject respJSON = new JSONObject();
        respJSON.put("Seller","MKRetailers");
        respJSON.put("Stock",getResponse(stockURL, headers));
        respJSON.put("Price",getResponse(pricerURL, headers));
        return respJSON;
    }

    @GetMapping("/mobilephone") //call using http://localhost:9081/seller/phone
    public JSONObject getMobilePhoneSeller(@RequestHeader MultiValueMap<String, String> headers){
        String pricerURL = "http://"+PRICER_SERVICE+":"+PRICER_PORT+"/pricer/mobilephone";
        String stockURL = "http://"+STOCK_SERVICE+":"+STOCK_PORT+"/stock/mobilephone";

        JSONObject respJSON = new JSONObject();
        respJSON.put("Seller","CloudTail");
        respJSON.put("Stock",getResponse(stockURL, headers));
        respJSON.put("Price",getResponse(pricerURL, headers));
        return respJSON;
    }

    @GetMapping("/laptop") //call using http://localhost:9081/seller/laptop
    public JSONObject getLaptopSeller(@RequestHeader MultiValueMap<String, String> headers){
        String pricerURL = "http://"+PRICER_SERVICE+":"+PRICER_PORT+"/pricer/laptop";
        String stockURL = "http://"+STOCK_SERVICE+":"+STOCK_PORT+"/stock/laptop";

        JSONObject respJSON = new JSONObject();
        respJSON.put("Seller","WSRetail");
        respJSON.put("Stock",getResponse(stockURL, headers));
        respJSON.put("Price",getResponse(pricerURL, headers));
        return respJSON;
    }


    private String getResponse(String rstURL, MultiValueMap<String, String> headers){
        StringBuilder ret = new StringBuilder();

        try {
            //print the headers for troubleshooting
            headers.forEach((key, value) -> {
                System.out.println(String.format(
                        "Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
            });

            URL url = new URL(rstURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //All these b3 headers need to be forwarded for tracing
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("x-request-id", headers.get("x-request-id").get(0));
            conn.setRequestProperty("x-b3-traceid", headers.get("x-b3-traceid").get(0));
            conn.setRequestProperty("x-b3-parentspanid", headers.get("x-b3-spanid").get(0));
            conn.setRequestProperty("x-b3-spanid", headers.get("x-b3-spanid").get(0));
            conn.setRequestProperty("MyHead", "Hello Pricer/Stock");

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
