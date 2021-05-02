package edu.ada.micronaut.service.impl;

import edu.ada.micronaut.service.FinancialService;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.inject.Singleton;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Singleton
public class YahooFinanceServiceImpl implements FinancialService {

    protected static final Logger logger = Logger.getLogger(YahooFinanceServiceImpl.class.getName());


    @Override
    public Map<String, String>
    getFinancialData(String stock_index) {

        Map<String, String> map = new HashMap<>();
        Stock stock = null;
        BigDecimal price = BigDecimal.ZERO;
        String[] stocks = stock_index.split(",");
        List<Object> result = null;

        try {


            FileHandler fileHandler = new FileHandler("app.log", true);
            logger.addHandler(fileHandler);


            for (int i = 0; i < stocks.length; i++) {
                stock = YahooFinance.get(stocks[i]);

                price = stock.getQuote(true).getPrice();
                map.put(stocks[i], price.toString());
                
                logger.info("Stock: " + stock + " Price: " + price);
            }
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }

        return map;
    }

}

