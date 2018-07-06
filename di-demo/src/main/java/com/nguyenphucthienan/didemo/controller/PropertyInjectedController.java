package com.nguyenphucthienan.didemo.controller;

import com.nguyenphucthienan.didemo.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PropertyInjectedController {
    @Autowired
    public GreetingService greetingService;

    public String sayHello() {
        return greetingService.sayGreeting();
    }
}
