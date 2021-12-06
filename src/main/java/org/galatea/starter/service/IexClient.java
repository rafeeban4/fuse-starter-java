package org.galatea.starter.service;

import java.util.List;
import org.galatea.starter.domain.IexHistoricalPrice;
import org.galatea.starter.domain.IexLastTradedPrice;
import org.galatea.starter.domain.IexSymbol;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * A Feign Declarative REST Client to access endpoints from the Free and Open IEX API to get market
 * data. See https://iextrading.com/developer/docs/
 */
@FeignClient(name = "IEX", url = "${spring.rest.iexBasePath}")
public interface IexClient {

  /**
   * Get a list of all stocks supported by IEX. See https://iextrading.com/developer/docs/#symbols.
   * As of July 2019 this returns almost 9,000 symbols, so maybe don't call it in a loop.
   *
   * @param apiKey token for api.
   * @return a list of all of the stock symbols supported by IEX.
   */
  @GetMapping("/ref-data/symbols")
  List<IexSymbol> getAllSymbols(@RequestParam("token") String apiKey);

  /**
   * Get the last traded price for each stock symbol passed in. See https://iextrading.com/developer/docs/#last.
   *
   * @param apiKey token for api.
   * @param symbols stock symbols to get last traded price for.
   * @return a list of the last traded price for each of the symbols passed in.
   */
  @GetMapping("/tops/last")
  List<IexLastTradedPrice> getLastTradedPriceForSymbols(
      @RequestParam("token") String apiKey,
      @RequestParam("symbols") String[] symbols);

  /**
   * Get the time series data for stock symbol passed in. See https://iextrading.com/developer/docs/#time-series.
   *
   * @param apiKey token for api.
   * @param range string representation of date range of data.
   * @param symbol stock symbol to get data for.
   * @return a list of the last traded price for each of the symbols passed in.
   */
  @GetMapping("/stock/{symbol}/chart/{range}")
  List<IexHistoricalPrice> getHistoricalPriceForSymbol(
      @RequestParam("token") String apiKey,
      @PathVariable("range") String range,
      @PathVariable ("symbol") String symbol);
}
