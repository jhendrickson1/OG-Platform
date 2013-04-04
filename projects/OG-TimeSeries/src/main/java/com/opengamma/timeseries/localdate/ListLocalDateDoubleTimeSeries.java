/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.timeseries.localdate;

import java.util.List;

import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;

import com.opengamma.timeseries.DateTimeConverter;
import com.opengamma.timeseries.DoubleTimeSeries;
import com.opengamma.timeseries.fast.DateTimeNumericEncoding;
import com.opengamma.timeseries.fast.integer.FastIntDoubleTimeSeries;
import com.opengamma.timeseries.fast.integer.FastListIntDoubleTimeSeries;
import com.opengamma.timeseries.fast.integer.FastMutableIntDoubleTimeSeries;

/**
 * List-based implementation of {@code MutableLocalDateDoubleTimeSeries}.
 */
public class ListLocalDateDoubleTimeSeries extends AbstractMutableLocalDateDoubleTimeSeries {

  /** Serialization version. */
  private static final long serialVersionUID = -9111933113421728410L;

  private static final FastIntDoubleTimeSeries TIMESERIES_TEMPLATE = new FastListIntDoubleTimeSeries(DateTimeNumericEncoding.DATE_EPOCH_DAYS);
  /** Default converter. */
  private static final DateTimeConverter<LocalDate> CONVERTER = new LocalDateEpochDaysConverter();

  public ListLocalDateDoubleTimeSeries(final DateTimeConverter<LocalDate> converter, final FastMutableIntDoubleTimeSeries fastTS) {
    super(converter, fastTS);
  }

  public ListLocalDateDoubleTimeSeries() {
    super(new LocalDateEpochDaysConverter(), new FastListIntDoubleTimeSeries(DateTimeNumericEncoding.DATE_EPOCH_DAYS));
  }

  public ListLocalDateDoubleTimeSeries(final LocalDate[] dates, final double[] values) {
    super(CONVERTER, new FastListIntDoubleTimeSeries(DateTimeNumericEncoding.DATE_EPOCH_DAYS, CONVERTER.convertToInt(dates), values));
  }

  public ListLocalDateDoubleTimeSeries(final ZoneId timeZone, final LocalDate[] dates, final double[] values) {
    super(new LocalDateEpochDaysConverter(timeZone), new FastListIntDoubleTimeSeries(DateTimeNumericEncoding.DATE_EPOCH_DAYS, new LocalDateEpochDaysConverter(timeZone).convertToInt(dates),
        values));
  }

  public ListLocalDateDoubleTimeSeries(final List<LocalDate> dates, final List<Double> values) {
    super(CONVERTER, new FastListIntDoubleTimeSeries(DateTimeNumericEncoding.DATE_EPOCH_DAYS, CONVERTER.convertToInt(dates), values));
  }

  public ListLocalDateDoubleTimeSeries(final ZoneId timeZone, final List<LocalDate> dates, final List<Double> values) {
    super(new LocalDateEpochDaysConverter(timeZone), new FastListIntDoubleTimeSeries(DateTimeNumericEncoding.DATE_EPOCH_DAYS, new LocalDateEpochDaysConverter(timeZone).convertToInt(dates),
        values));
  }

  public ListLocalDateDoubleTimeSeries(final DoubleTimeSeries<LocalDate> dts) {
    super(CONVERTER, (FastMutableIntDoubleTimeSeries) CONVERTER.convertToInt(TIMESERIES_TEMPLATE, dts));
  }

  public ListLocalDateDoubleTimeSeries(final ZoneId timeZone, final LocalDateDoubleTimeSeries dts) {
    super(new LocalDateEpochDaysConverter(timeZone), (FastMutableIntDoubleTimeSeries) new LocalDateEpochDaysConverter(timeZone).convertToInt(TIMESERIES_TEMPLATE, dts));
  }

  public ListLocalDateDoubleTimeSeries(final FastMutableIntDoubleTimeSeries pmidts) {
    super(CONVERTER, pmidts);
  }

  public ListLocalDateDoubleTimeSeries(final ZoneId timeZone, final FastMutableIntDoubleTimeSeries pmidts) {
    super(new LocalDateEpochDaysConverter(timeZone), pmidts);
  }

  @Override
  public ListLocalDateDoubleTimeSeries newInstanceFast(final LocalDate[] dateTimes, final double[] values) {
    return new ListLocalDateDoubleTimeSeries(dateTimes, values);
  }

  

}
