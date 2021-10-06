package com;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/details")
public class ProductDetails {

    /*@Autowired
    private RestTemplate restTemplate;*/

    /*private final static String[] headers_to_propagate = { //this is for distributed tracing
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

    private static final String SELLER_SERVICE = System.getenv("SELLER_SERVICE");
    private static final String SELLER_PORT = System.getenv("SELLER_PORT");

    @GetMapping("/washingmachine") //call using http://localhost:9081/details/washingmachine
    public JSONObject getWashingMachine(@RequestHeader MultiValueMap<String, String> headers){
        String sellerURL = "http://"+SELLER_SERVICE+":"+SELLER_PORT+"/seller/washingmachine";

        System.out.println(sellerURL);

        JSONObject respJSON = getResponse(sellerURL, headers);
        respJSON.put("Product","Washing Machine");

        return respJSON;
    }

    @GetMapping("/mobilephone") //call using http://localhost:9081/details/mobilephone
    public JSONObject getMobilePhone(@RequestHeader MultiValueMap<String, String> headers){
        String sellerURL = "http://"+SELLER_SERVICE+":"+SELLER_PORT+"/seller/mobilephone";

        JSONObject respJSON = getResponse( sellerURL, headers );
        respJSON.put("Product","Mobile Phone");

        return respJSON;
    }

    @GetMapping("/laptop") //call using http://localhost:9081/details/laptop
    public JSONObject getLaptop(@RequestHeader MultiValueMap<String, String> headers){
        String sellerURL = "http://"+SELLER_SERVICE+":"+SELLER_PORT+"/seller/laptop";

        JSONObject respJSON = getResponse(sellerURL, headers);
        respJSON.put("Product","Laptop");

        return respJSON;
    }

    private JSONObject getResponse(String rstURL, MultiValueMap<String, String> headers){

        JSONObject ret = new JSONObject();
        //can use RestTemplate as well
        try {
            //print the headers for troubleshooting
            headers.forEach((key, value) -> {
                System.out.println(String.format(
                        "Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
            });

            URL url = new URL(rstURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            //All these b3 headers need to be forwarded for tracing
            conn.setRequestProperty("x-request-id", headers.get("x-request-id").get(0));
            conn.setRequestProperty("x-b3-traceid", headers.get("x-b3-traceid").get(0));
            conn.setRequestProperty("x-b3-parentspanid", headers.get("x-b3-spanid").get(0));
            conn.setRequestProperty("x-b3-spanid", headers.get("x-b3-spanid").get(0));
            conn.setRequestProperty("MyHeader", "Heallo Seller");

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
