package com;

import org.springframework.web.bind.annotation.*;
import org.springframework.boot.json.*;

import java.net.http.HttpClient;

@RestController
public class StockDetails {

    @GetMapping(value="/wstock") //call using http://localhost:9081/wstock
    public String getWashingMachineStock(){
        return "1160";
    }

    @GetMapping(value="/mstock") //call using http://localhost:9081/mstock
    public String getMobilePhoneStock(){
        return "5120";
    }

    @GetMapping(value="/lstock") //call using http://localhost:9081/lstock
    public String getLaptopStock()
    {
        return "2110";
    }

}
