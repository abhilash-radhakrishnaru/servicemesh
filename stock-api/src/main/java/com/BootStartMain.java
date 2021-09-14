package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class BootStartMain {
    public static void main(String[] args)
    {
        SpringApplication.run(BootStartMain.class, args);
    }

}