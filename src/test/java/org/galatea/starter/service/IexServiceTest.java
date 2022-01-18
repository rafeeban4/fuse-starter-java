package org.galatea.starter.service;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.galatea.starter.ASpringTest;
import org.galatea.starter.domain.IexHistoricalPrice;
import org.galatea.starter.domain.IexLastTradedPrice;
import org.galatea.starter.domain.IexSymbol;
import org.galatea.starter.domain.rpsy.IiexHistoricalPriceRpsy;
import org.galatea.starter.testutils.TestDataGenerator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

public class IexServiceTest extends ASpringTest {

  @MockBean
  private IexClient client;

  @MockBean
  private IiexHistoricalPriceRpsy cache;

  private IexService service;
  private Date dateRepresentation;

  @Before
  public void setup() {
    service = new IexService(client,cache);
    //copied https://stackoverflow.com/questions/5677470/java-why-is-the-date-constructor-deprecated-and-what-do-i-use-instead
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, 1988);
    cal.set(Calendar.MONTH, Calendar.JANUARY);
    cal.set(Calendar.DAY_OF_MONTH, 1);
    dateRepresentation = cal.getTime();
  }
  @Test
  public void testGetAllSymbols() {
    List<IexSymbol> expResult = new ArrayList<IexSymbol>();
    IexSymbol mock =
        TestDataGenerator.defaultIexSymbolData().build();
    expResult.add(mock);
    when(client.getAllSymbols(null)).thenReturn(
         Stream.of(mock).collect(
                Collectors.toList()));

    assertEquals(expResult, service.getAllSymbols());
  }
  @Test
  public void testGetLastTradedPriceForSymbols() {
    List<String> params =  new ArrayList<>();
    params.add("FB");
    String[] clientMock = {"FB"};
    List<IexLastTradedPrice> expResult = new ArrayList<IexLastTradedPrice>();
    IexLastTradedPrice mock =
        TestDataGenerator.defaultIexLastTradedPriceData().build();
    expResult.add(mock);
    when(client.getLastTradedPriceForSymbols(null,clientMock)).thenReturn(
        Stream.of(mock).collect(
            Collectors.toList()));

    assertEquals(expResult, service.getLastTradedPriceForSymbols(params));
  }

  @Test
  public void testGetHistoricalPriceForSymbol() {
    List<IexHistoricalPrice> expResult = new ArrayList<IexHistoricalPrice>();
    IexHistoricalPrice mock =
        TestDataGenerator.defaultIexHistoricalPriceData().build();
    expResult.add(mock);
    when(client.getHistoricalPriceForSymbol(null,"5d","FB")).thenReturn(
        Stream.of(mock).collect(
            Collectors.toList()));

    assertEquals(expResult, service.getHistoricalPriceForSymbol("5d","FB"));
  }

}
