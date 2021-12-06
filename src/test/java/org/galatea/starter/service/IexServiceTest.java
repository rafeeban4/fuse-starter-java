package org.galatea.starter.service;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.galatea.starter.ASpringTest;
import org.galatea.starter.domain.IexHistoricalPrice;
import org.galatea.starter.domain.IexLastTradedPrice;
import org.galatea.starter.domain.IexSymbol;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

public class IexServiceTest extends ASpringTest {

  @MockBean
  private IexClient IexClient;

  private IexService service;

  @Before
  public void setup() {
    service = new IexService(IexClient);
  }
  @Test
  public void testGetAllSymbols() {
    List<IexSymbol> expResult = new ArrayList<IexSymbol>();

    IexService spyService = spy(service);
    List<IexSymbol> result =  spyService.getAllSymbols();

    assertEquals(expResult, service.getAllSymbols());
  }
  @Test
  public void testGetLastTradedPriceForSymbolsNoSymbols() {
    List<String> params =  new ArrayList<>();
    List<String> expResult = Collections.emptyList();

    IexService spyService = spy(service);
    List<IexLastTradedPrice> result =  spyService.getLastTradedPriceForSymbols(params);

    assertEquals(expResult, result);
  }

  @Test
  public void testGetLastTradedPriceForSymbols() {
    List<String> params =  new ArrayList<>();
    params.add("AA");
    List<String> expResult = Collections.emptyList();

    IexService spyService = spy(service);
    List<IexLastTradedPrice> result =  spyService.getLastTradedPriceForSymbols(params);

    assertEquals(expResult, result);
  }

  @Test
  public void testGetHistoricalPriceForSymbol() {
    List<IexHistoricalPrice> expResult = new ArrayList<IexHistoricalPrice>();
    IexService spyService = spy(service);
    List<IexHistoricalPrice> result =  spyService.getHistoricalPriceForSymbol("5d","FB");

    assertEquals(expResult, result);
  }

}
