package com.MyMicroservices.correncyconversion.controller;

import com.MyMicroservices.correncyconversion.configuration.RestConfig;
import com.MyMicroservices.correncyconversion.proxy.CurrencyExchangeProxy;
import model.CurrencyConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

   @Autowired
  private CurrencyExchangeProxy exchangeProxy;
   @Autowired
   private RestTemplate restTemplate;
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion getAllValues(@PathVariable String from,
                                           @PathVariable String to,
                                           @PathVariable BigDecimal quantity){
        HashMap<String,String> conversionHashMap = new HashMap<>();
        conversionHashMap.put("from",from);
        conversionHashMap.put("to",to);
        ResponseEntity<CurrencyConversion> conversionResponseEntity = restTemplate.getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class,
                conversionHashMap
        );
        CurrencyConversion currencyConversion = conversionResponseEntity.getBody();

            return new CurrencyConversion(currencyConversion.getId(), from,to,quantity,
                    currencyConversion.getExchangeMultiple(),
                    quantity.multiply(currencyConversion.getExchangeMultiple()),
                    currencyConversion.getEnvironment());

    }
    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion getAllValuesByFeign(@PathVariable String from,
                                           @PathVariable String to,
                                           @PathVariable BigDecimal quantity){

        CurrencyConversion currencyConversion = exchangeProxy.getAllCurrencyExchange(from,to);

        return new CurrencyConversion(currencyConversion.getId(), from,to,quantity,
                currencyConversion.getExchangeMultiple(),
                quantity.multiply(currencyConversion.getExchangeMultiple()),
                currencyConversion.getEnvironment()+"feign");

    }



}
