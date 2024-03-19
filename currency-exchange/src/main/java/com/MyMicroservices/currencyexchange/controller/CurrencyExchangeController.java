package com.MyMicroservices.currencyexchange.controller;

import com.MyMicroservices.currencyexchange.model.CurrencyExchange;
import com.MyMicroservices.currencyexchange.repository.CurrencyExchangeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {
   @Autowired
   private Environment environment;
   @Autowired
   private CurrencyExchangeRepo currencyExchangeRepo;
   private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getAllCurrencyExchange(@PathVariable String from,@PathVariable String to)
    {
        String port= environment.getProperty("local.server.port");
        //CurrencyExchange currencyExchange=new CurrencyExchange(1000L,from,to, BigDecimal.valueOf(80),port);
        CurrencyExchange currencyExchange = currencyExchangeRepo.findByFromAndTo(from,to);
        logger.info("retrieve exchange value {} to {}",from,to);
        currencyExchange.setEnvironment(port);
        System.out.println(currencyExchange);
        if(currencyExchange==null)
            throw new RuntimeException("unable to find");
        return currencyExchange;
    }
}
