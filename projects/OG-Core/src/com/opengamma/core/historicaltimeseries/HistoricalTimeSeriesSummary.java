/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */

package com.opengamma.core.historicaltimeseries;

import javax.time.calendar.LocalDate;

import org.joda.beans.BeanDefinition;
import org.joda.beans.MetaBean;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBean;

import com.opengamma.util.PublicSPI;

import java.io.Serializable;
import java.util.Map;
import org.joda.beans.BeanBuilder;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

/**
 * Provides access to summary information for an HTS without needing to load data points unnecessarily.
 * Earliest and latest point dates and data are kept here for quick access whether using a master or
 * a source.
 */
@PublicSPI
@BeanDefinition
public class HistoricalTimeSeriesSummary extends DirectBean implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * The earliest value of time-series.
   */
  @PropertyDefinition
  private double _earliestValue;
  
  /**
   * The latest value of time-series.
   */
  @PropertyDefinition
  private double _latestValue;
  
  /**
   * The earliest date of time-series.
   * This field is only returned if requested from the master.
   */
  @PropertyDefinition
  private LocalDate _earliestDate;
  
  /**
   * The latest date of time-series.
   * This field is only returned if requested from the master.
   */
  @PropertyDefinition
  private LocalDate _latestDate;

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code HistoricTimeSeriesSummary}.
   * @return the meta-bean, not null
   */
  public static HistoricalTimeSeriesSummary.Meta meta() {
    return HistoricalTimeSeriesSummary.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(HistoricalTimeSeriesSummary.Meta.INSTANCE);
  }

  @Override
  public HistoricalTimeSeriesSummary.Meta metaBean() {
    return HistoricalTimeSeriesSummary.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -1157287970:  // earliestValue
        return getEarliestValue();
      case 426815018:  // latestValue
        return getLatestValue();
      case 239226785:  // earliestDate
        return getEarliestDate();
      case -125315115:  // latestDate
        return getLatestDate();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -1157287970:  // earliestValue
        setEarliestValue((Double) newValue);
        return;
      case 426815018:  // latestValue
        setLatestValue((Double) newValue);
        return;
      case 239226785:  // earliestDate
        setEarliestDate((LocalDate) newValue);
        return;
      case -125315115:  // latestDate
        setLatestDate((LocalDate) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      HistoricalTimeSeriesSummary other = (HistoricalTimeSeriesSummary) obj;
      return JodaBeanUtils.equal(getEarliestValue(), other.getEarliestValue()) &&
          JodaBeanUtils.equal(getLatestValue(), other.getLatestValue()) &&
          JodaBeanUtils.equal(getEarliestDate(), other.getEarliestDate()) &&
          JodaBeanUtils.equal(getLatestDate(), other.getLatestDate());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getEarliestValue());
    hash += hash * 31 + JodaBeanUtils.hashCode(getLatestValue());
    hash += hash * 31 + JodaBeanUtils.hashCode(getEarliestDate());
    hash += hash * 31 + JodaBeanUtils.hashCode(getLatestDate());
    return hash;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the earliest value of time-series.
   * @return the value of the property
   */
  public double getEarliestValue() {
    return _earliestValue;
  }

  /**
   * Sets the earliest value of time-series.
   * @param earliestValue  the new value of the property
   */
  public void setEarliestValue(double earliestValue) {
    this._earliestValue = earliestValue;
  }

  /**
   * Gets the the {@code earliestValue} property.
   * @return the property, not null
   */
  public final Property<Double> earliestValue() {
    return metaBean().earliestValue().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the latest value of time-series.
   * @return the value of the property
   */
  public double getLatestValue() {
    return _latestValue;
  }

  /**
   * Sets the latest value of time-series.
   * @param latestValue  the new value of the property
   */
  public void setLatestValue(double latestValue) {
    this._latestValue = latestValue;
  }

  /**
   * Gets the the {@code latestValue} property.
   * @return the property, not null
   */
  public final Property<Double> latestValue() {
    return metaBean().latestValue().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the earliest date of time-series.
   * This field is only returned if requested from the master.
   * @return the value of the property
   */
  public LocalDate getEarliestDate() {
    return _earliestDate;
  }

  /**
   * Sets the earliest date of time-series.
   * This field is only returned if requested from the master.
   * @param earliestDate  the new value of the property
   */
  public void setEarliestDate(LocalDate earliestDate) {
    this._earliestDate = earliestDate;
  }

  /**
   * Gets the the {@code earliestDate} property.
   * This field is only returned if requested from the master.
   * @return the property, not null
   */
  public final Property<LocalDate> earliestDate() {
    return metaBean().earliestDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the latest date of time-series.
   * This field is only returned if requested from the master.
   * @return the value of the property
   */
  public LocalDate getLatestDate() {
    return _latestDate;
  }

  /**
   * Sets the latest date of time-series.
   * This field is only returned if requested from the master.
   * @param latestDate  the new value of the property
   */
  public void setLatestDate(LocalDate latestDate) {
    this._latestDate = latestDate;
  }

  /**
   * Gets the the {@code latestDate} property.
   * This field is only returned if requested from the master.
   * @return the property, not null
   */
  public final Property<LocalDate> latestDate() {
    return metaBean().latestDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code HistoricTimeSeriesSummary}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code earliestValue} property.
     */
    private final MetaProperty<Double> _earliestValue = DirectMetaProperty.ofReadWrite(
        this, "earliestValue", HistoricalTimeSeriesSummary.class, Double.TYPE);
    /**
     * The meta-property for the {@code latestValue} property.
     */
    private final MetaProperty<Double> _latestValue = DirectMetaProperty.ofReadWrite(
        this, "latestValue", HistoricalTimeSeriesSummary.class, Double.TYPE);
    /**
     * The meta-property for the {@code earliestDate} property.
     */
    private final MetaProperty<LocalDate> _earliestDate = DirectMetaProperty.ofReadWrite(
        this, "earliestDate", HistoricalTimeSeriesSummary.class, LocalDate.class);
    /**
     * The meta-property for the {@code latestDate} property.
     */
    private final MetaProperty<LocalDate> _latestDate = DirectMetaProperty.ofReadWrite(
        this, "latestDate", HistoricalTimeSeriesSummary.class, LocalDate.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<Object>> _map = new DirectMetaPropertyMap(
        this, null,
        "earliestValue",
        "latestValue",
        "earliestDate",
        "latestDate");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1157287970:  // earliestValue
          return _earliestValue;
        case 426815018:  // latestValue
          return _latestValue;
        case 239226785:  // earliestDate
          return _earliestDate;
        case -125315115:  // latestDate
          return _latestDate;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends HistoricalTimeSeriesSummary> builder() {
      return new DirectBeanBuilder<HistoricalTimeSeriesSummary>(new HistoricalTimeSeriesSummary());
    }

    @Override
    public Class<? extends HistoricalTimeSeriesSummary> beanType() {
      return HistoricalTimeSeriesSummary.class;
    }

    @Override
    public Map<String, MetaProperty<Object>> metaPropertyMap() {
      return _map;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code earliestValue} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> earliestValue() {
      return _earliestValue;
    }

    /**
     * The meta-property for the {@code latestValue} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> latestValue() {
      return _latestValue;
    }

    /**
     * The meta-property for the {@code earliestDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<LocalDate> earliestDate() {
      return _earliestDate;
    }

    /**
     * The meta-property for the {@code latestDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<LocalDate> latestDate() {
      return _latestDate;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
