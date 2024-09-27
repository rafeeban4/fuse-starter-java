package org.galatea.starter.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;

@Data
@Builder
@Entity
public class IexHistoricalPrice {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long identifier;

  private BigDecimal close;
  private BigDecimal high;
  private BigDecimal low;
  private BigDecimal open;
  private String symbol;
  private long volume;
  private Date date;
  private String range;

  /**
   * constructor with all fields.
   */
  public IexHistoricalPrice(final long identifier, final BigDecimal close, final BigDecimal high,
      final BigDecimal low, final BigDecimal open, final String symbol, final long volume,
      final Date date,final String range) {
    this.identifier = identifier;
    this.close = close;
    this.high = high;
    this.low = low;
    this.open = open;
    this.symbol = symbol;
    this.volume = volume;
    this.date = date;
    this.range = range;
  }

  /**
   * default constructor.
   */
  public IexHistoricalPrice(){}

  /**
   * constructor without id.
   */
  public IexHistoricalPrice(final BigDecimal close, final BigDecimal high, final BigDecimal low,
      final BigDecimal open, final String symbol, final long volume, final Date date,
      final String range) {
    this.close = close;
    this.high = high;
    this.low = low;
    this.open = open;
    this.symbol = symbol;
    this.volume = volume;
    this.date = date;
    this.range = range;
  }

}
