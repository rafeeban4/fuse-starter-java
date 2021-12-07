package org.galatea.starter.testutils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.galatea.starter.domain.IexHistoricalPrice;
import org.galatea.starter.domain.IexLastTradedPrice;
import org.galatea.starter.domain.IexSymbol;
import org.galatea.starter.domain.SettlementMission;
import org.galatea.starter.domain.TradeAgreement;
import org.galatea.starter.entrypoint.messagecontracts.ProtobufMessages;

/**
 * Utility class for generating default domain objects for tests.
 *
 * <p>Objects are returned in their builder forms so that objects without setter methods can still
 * be easily modified during test setup.
 */
// Mainly intended to be used when the specific data in the object doesn't matter, or when a test
// only cares about one or two fields (in which case those values should be explicitly set inside
// the test). The specific values assigned in this class should not be relied upon.
@Slf4j
public class TestDataGenerator {

  // Private constructor to appease Sonar
  private TestDataGenerator() {}

  /**
   * Generate a TradeAgreement builder populated with some default test values.
   */
  public static TradeAgreement.TradeAgreementBuilder defaultTradeAgreementData() {
    return TradeAgreement.builder()
        .instrument("IBM")
        .internalParty("INT-1")
        .externalParty("EXT-1")
        .buySell("B")
        .qty(100d);
  }

  /**
   * Generate a TradeAgreementProtoMessage builder populated with some default test values.
   */
  public static ProtobufMessages.TradeAgreementProtoMessage.Builder
  defaultTradeAgreementProtoMessageData() {
    return ProtobufMessages.TradeAgreementProtoMessage.newBuilder()
        .setInstrument("IBM")
        .setInternalParty("INT-1")
        .setExternalParty("EXT-1")
        .setBuySell("B")
        .setQty(100);
  }

  /**
   * Generate a SettlementMission builder populated with some default test values.
   */
  public static SettlementMission.SettlementMissionBuilder defaultSettlementMissionData() {
    return SettlementMission.builder()
        .id(100L)
        .depot("DTC")
        .externalParty("EXT-1")
        .instrument("IBM")
        .direction("REC")
        .qty(100d)
        .version(0L);
  }

  /**
   * Generate a IexSymbol Price populated with some default test values.
   */
  public static IexSymbol.IexSymbolBuilder defaultIexSymbolData(){

    //copied https://stackoverflow.com/questions/5677470/java-why-is-the-date-constructor-deprecated-and-what-do-i-use-instead
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, 1988);
    calendar.set(Calendar.MONTH, Calendar.JANUARY);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    Date date = calendar.getTime();

    return IexSymbol.builder()
        .symbol("A")
        .name("Agilent Technologies Inc.")
        .date(date)
        .isEnabled(true)
        .type("cs")
        .iexId("2");

  }
  /**
   * Generate a IexLastTradedPrice  populated with some default test values.
   */
  public static IexLastTradedPrice.IexLastTradedPriceBuilder defaultIexLastTradedPriceData(){

    return IexLastTradedPrice.builder()
        .symbol("FB")
        .price(new BigDecimal(186.34))
        .size(100)
        .time(1565273330617l);

  }
  /**
   * Generate a IexHistorical Price populated with some default test values.
   */
  public static IexHistoricalPrice.IexHistoricalPriceBuilder defaultIexHistoricalPriceData(){

    //copied https://stackoverflow.com/questions/5677470/java-why-is-the-date-constructor-deprecated-and-what-do-i-use-instead
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, 1988);
    calendar.set(Calendar.MONTH, Calendar.JANUARY);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    Date date = calendar.getTime();

    return IexHistoricalPrice.builder()
        .symbol("A")
        .close(new BigDecimal(186.34))
        .high(new BigDecimal(186.34))
        .low(new BigDecimal(186.34))
        .open(new BigDecimal(186.34))
        .volume(1638316800000l)
        .date(date);


  }
}
