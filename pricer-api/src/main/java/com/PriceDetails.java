package com;

import org.springframework.web.bind.annotation.*;
import org.springframework.boot.json.*;

import java.net.http.HttpClient;

@RestController
public class PriceDetails {

    @GetMapping(value="/pricer/washingmachine") //call using http://localhost:9081/pricer/washingmachine
    public String getWashingMachinePrice(){
        return "39485.23";
    }

    @GetMapping(value="/pricer/mobilephone") //call using http://localhost:9081/pricer/mobilephone
    public String getMobilePhonePrice(){
        return "16312.98";
    }

    @GetMapping(value="/pricer/laptop") //call using http://localhost:9081/pricer/laptop
    public String getLaptopPrice(){
        return "62341.24";
    }

}
