package com.ecom.ecomui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UIController {

    @RequestMapping(value="/home") //http://localhost:9080/home
    public String firstPage(){
        return "landing";
        //this will return landing.html since the class is annotated with @Controller(not @RestController)
    }
}
