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


  /**
   * Exposes an endpoint to get all of the symbols available on IEX.
   *
   * @param apiKey api token.
   * @return a list of all IexStockSymbols.
   */
  public List<IexSymbol> getAllSymbols(final String apiKey) {
    return iexClient.getAllSymbols(apiKey);
  }

  /**
   * Get the last traded price for each of the symbols passed in.
   *
   * @param apiKey api token.
   * @param symbols list of symbols to get last traded price for.
   * @return a List of IexLastTradedPrice objects for the given symbols.
   */
  public List<IexLastTradedPrice> getLastTradedPriceForSymbols(final String apiKey,
      final List<String> symbols) {
    if (CollectionUtils.isEmpty(symbols)) {
      return Collections.emptyList();
    } else {
      return iexClient.getLastTradedPriceForSymbols(apiKey,symbols.toArray(new String[0]));
    }
  }

  /**
   * Get the historical price for the symbol passed in.
   *
   * @param apiKey api token.
   * @param timeSeriesId type of data to get for symbol.
   * @param symbol symbol to get data for.
   * @return a List of IexHistoricalPrice objects for the given symbol.
   */
  public List<IexHistoricalPrice> getHistoricalPriceForSymbol(final String apiKey,
      final String timeSeriesId, final String symbol) {
    return iexClient.getHistoricalPriceForSymbol(apiKey,timeSeriesId,symbol);
  }


}
