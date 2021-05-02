package edu.ada.micronaut.service;

import java.util.Map;

public interface FinancialService {

    Map<String, String> getFinancialData(String stock_index);

}
