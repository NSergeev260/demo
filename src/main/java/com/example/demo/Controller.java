package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;

@RestController
public class Controller {

    @Value("${hello}")
    private String hello;
    private String post = "Nightmare";

    @GetMapping("/hello")
    public String hello() {
        System.out.println(this.hello);
        return hello;
    }

    @PostMapping("/hello")
    public String post() {
        System.out.println(this.post);
        return post;
    }

    @GetMapping("/")
    public String getData() {
        String hostname = "Unknown";
        try {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            hostname = addr.getHostName();
        } catch (Exception e) {
            System.out.println("Hostname can not be resolved");
        }
        return hostname;
    }
}