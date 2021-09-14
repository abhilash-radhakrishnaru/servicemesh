package com;

import org.springframework.web.bind.annotation.*;
import org.springframework.boot.json.*;

import java.net.http.HttpClient;

@RestController
public class PriceDetails {

    @GetMapping(value="/wprice") //call using http://localhost:9081/wprice
    public String getWashingMachinePrice(){
        return "39485.23";
    }

    @GetMapping(value="/mprice") //call using http://localhost:9081/mprice
    public String getMobilePhonePrice(){
        return "16312.98";
    }

    @GetMapping(value="/lprice") //call using http://localhost:9081/lprice
    public String getLaptopPrice(){
        return "62341.24";
    }

}
