package com;

import org.springframework.web.bind.annotation.*;
import org.springframework.boot.json.*;

import java.net.http.HttpClient;

@RestController
public class StockDetails {

    @GetMapping(value="/stock/washingmachine") //call using http://localhost:9081/stock/washingmachine
    public String getWashingMachineStock(){
        return "1160";
    }

    @GetMapping(value="/stock/mobilephone") //call using http://localhost:9081/stock/mobilephone
    public String getMobilePhoneStock(){
        return "5120";
    }

    @GetMapping(value="/stock/laptop") //call using http://localhost:9081/stock/laptop
    public String getLaptopStock()
    {
        return "2110";
    }

}
