package com;

import org.springframework.web.bind.annotation.*;
import org.springframework.boot.json.*;

@RestController
public class TestController {

    @RequestMapping(value="/test")
    public String testMsg(){
        return "HELLO FROM TEST";
    }


    @GetMapping(value = "/getTest") //call using http://localhost:8080/getTest?param1=WORLD
    public String testGET(String param1) {

        return "<h1>Hello<br>GET<br>"+param1+" <h1> ";
    }

    @GetMapping(value = "/getTest/{myid}/test") //call using http://localhost:8080/getTest/21?param1=WORLD
    public String testGETPath(@PathVariable("myid") int id, String param1) {

        return "<h1>Hello<br>GET<br>"+param1+" <h1> <br>ID="+id;
    }

    @PostMapping(value = "/postTest")
    public String testPost(String param1) {

        return "<h1>Hello<br>POST<br>"+param1;
    }

}
