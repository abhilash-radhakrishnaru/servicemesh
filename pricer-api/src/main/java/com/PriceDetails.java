package com;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.json.*;

import java.net.http.HttpClient;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pricer")
public class PriceDetails {

    @GetMapping("/washingmachine") //call using http://localhost:9081/pricer/washingmachine
    public String getWashingMachinePrice(@RequestHeader MultiValueMap<String, String> headers){

        printHeader(headers);
        headers.forEach((key, value) -> {
            System.out.println(String.format(
                    "Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
        });
        return "39485.23";
    }

    @GetMapping("/mobilephone") //call using http://localhost:9081/pricer/mobilephone
    public String getMobilePhonePrice(@RequestHeader MultiValueMap<String, String> headers){

        printHeader(headers);
        return "16312.98";
    }

    @GetMapping("/laptop") //call using http://localhost:9081/pricer/laptop
    public String getLaptopPrice(@RequestHeader MultiValueMap<String, String> headers){

        printHeader(headers);
        return "62341.24";
    }

    private void printHeader(MultiValueMap<String, String> headers){
        //printing header for troubleshooting
        headers.forEach((key, value) -> {
            System.out.println(String.format(
                    "Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
        });
    }

}
