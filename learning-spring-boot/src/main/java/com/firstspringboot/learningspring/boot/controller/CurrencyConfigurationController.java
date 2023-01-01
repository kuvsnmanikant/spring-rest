package com.firstspringboot.learningspring.boot.controller;
// this is used to fetch the config values from application.properties
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.firstspringboot.learningspring.boot.configurations.CurrencyServiceConfiguration;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class CurrencyConfigurationController {

    @Autowired
    private CurrencyServiceConfiguration currencyserviceconfiguration;

    @GetMapping("/configured-controller")
    public CurrencyServiceConfiguration retrieveAllCourses() {   // here we are sending bean as json to the clinet using @responsebody
        return currencyserviceconfiguration;
    }
}
