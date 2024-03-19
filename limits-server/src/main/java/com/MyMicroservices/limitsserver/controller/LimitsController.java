package com.MyMicroservices.limitsserver.controller;

import com.MyMicroservices.limitsserver.config.LimitConfig;
import com.MyMicroservices.limitsserver.model.Limit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {
    @Autowired
    LimitConfig limitConfig;
    @GetMapping("/limits")
    public Limit getAllLimits(){
        return new Limit(limitConfig.getMinimum(), limitConfig.getMaximum());

    }
}
