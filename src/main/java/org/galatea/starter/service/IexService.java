package org.galatea.starter.service;

import java.util.Collections;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.galatea.starter.domain.IexHistoricalPrice;
import org.galatea.starter.domain.IexLastTradedPrice;
import org.galatea.starter.domain.IexSymbol;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * A layer for transformation, aggregation, and business required when retrieving data from IEX.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class IexService {

  @NonNull
  private IexClient iexClient;
  String apiKey = "pk_6dbb04551433434f9b727aafceda2947";

  /**this generally isnt a good practice to have an apiKey here
   * but since this is a mock and the apikey is exposed anyway, I'm leaving this here
   * alternate solution would be to make a separate file in gitignore
   * that contains the apikey and then import it via FileReader
  */

  /**
   * Exposes an endpoint to get all of the symbols available on IEX.
   *
   * @return a list of all IexStockSymbols.
   */
  public List<IexSymbol> getAllSymbols() {
    return iexClient.getAllSymbols(apiKey);
  }

  /**
   * Get the last traded price for each of the symbols passed in.
   *
   * @param symbols list of symbols to get last traded price for.
   * @return a List of IexLastTradedPrice objects for the given symbols.
   */
  public List<IexLastTradedPrice> getLastTradedPriceForSymbols(final List<String> symbols) {
    if (CollectionUtils.isEmpty(symbols)) {
      return Collections.emptyList();
    } else {
      return iexClient.getLastTradedPriceForSymbols(apiKey,symbols.toArray(new String[0]));
    }
  }

  /**
   * Get the historical price for the symbol passed in.
   *
   * @param range string representation of date range of data.
   * @param symbol symbol to get data for.
   * @return a List of IexHistoricalPrice objects for the given symbol.
   */
  public List<IexHistoricalPrice> getHistoricalPriceForSymbol(final String range,
      final String symbol) {
    return iexClient.getHistoricalPriceForSymbol(apiKey,range,symbol);
  }


}
