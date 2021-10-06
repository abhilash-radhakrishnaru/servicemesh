package com;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.json.*;

import java.net.http.HttpClient;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stock")
public class StockDetails {

    @GetMapping("/washingmachine") //call using http://localhost:9081/stock/washingmachine
    public String getWashingMachineStock(@RequestHeader MultiValueMap<String, String> headers)
    {
            printHeader(headers);
            return "1160";
    }

    @GetMapping("/mobilephone") //call using http://localhost:9081/stock/mobilephone
    public String getMobilePhoneStock(@RequestHeader MultiValueMap<String, String> headers){
        printHeader(headers);
        return "5120";
    }

    @GetMapping("/laptop") //call using http://localhost:9081/stock/laptop
    public String getLaptopStock(@RequestHeader MultiValueMap<String, String> headers)
    {
        printHeader(headers);
        return "2110";
    }

    private void printHeader(MultiValueMap<String, String> headers){
        //printing header for troubleshooting
        headers.forEach((key, value) -> {
            System.out.println(String.format(
                    "Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
        });
    }

}
