package com.MyMicroservices.currencyexchange.repository;

import com.MyMicroservices.currencyexchange.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange,Long> {
    public CurrencyExchange findByFromAndTo(String from,String to);
}
