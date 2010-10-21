/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.timeseries;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.time.calendar.LocalDate;

import org.joda.beans.BeanDefinition;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.BasicMetaBean;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectMetaProperty;

import com.opengamma.id.Identifier;
import com.opengamma.id.UniqueIdentifier;
import com.opengamma.util.db.PagingRequest;

/**
 * Request for searching for TimeSeries.
 * 
 * @param <T> LocalDate/java.sql.Date
 */
@BeanDefinition
public class TimeSeriesSearchRequest<T> extends DirectBean {

  /**
   * The request for paging.
   * By default all matching items will be returned.
   */
  @PropertyDefinition
  private PagingRequest _pagingRequest = PagingRequest.ALL;
  /**
   * The time series identifier for loading specific data points range.
   */
  @PropertyDefinition
  private UniqueIdentifier _timeSeriesId;
  /**
   * The identifier value, matching against the <b>value</b> of the identifiers,
   * null to not match by identifier value.
   * This matches against the {@link Identifier#getValue() value} of the identifier
   * and does not match against the key. Wildcards are allowed.
   * This method is suitable for human searching, whereas the {@code identifiers}
   * search is useful for exact machine searching.
   */
  @PropertyDefinition
  private String _identifierValue;
  /**
   * The identifiers to match, null to not match on identifiers.
   * This will return time series where at least one complete identifier in the series matches
   * at least one complete identifier in this bundle. Note that an empty bundle will not match
   * anything, whereas a null bundle places no restrictions on the result.
   * This method is suitable for exact machine searching, whereas the {@code identifierValue}
   * search is useful for human searching.
   */
  @PropertyDefinition
  private final List<Identifier> _identifiers = new ArrayList<Identifier>();
  /**
   * Current date (if appicalable for identifiers)
   */
  @PropertyDefinition
  private LocalDate _currentDate;
  /**
   * The data source, null to search all data sources.
   */
  @PropertyDefinition
  private String _dataSource;
  /**
   * The data provider, null to search all data providers.
   */
  @PropertyDefinition
  private String _dataProvider; 
  /**
   * The data field to search, null to search all data fields.
   */
  @PropertyDefinition
  private String _dataField;
  /**
   * The observation time, null to search all observation times.
   */
  @PropertyDefinition
  private String _observationTime;
  /**
   * The start date, null to search from start date in data store.
   */
  @PropertyDefinition
  private T _start; 
  /**
   * The end date, null to search until the end date in data store.
   */
  @PropertyDefinition
  private T _end;
  /**
   * Set to true to load data points, otherwise return just meta data.
   */
  @PropertyDefinition
  private boolean _loadTimeSeries;
  /**
   * Set to true to load the start and end date for time series.
   */
  @PropertyDefinition
  private boolean _loadDates;

  /**
   * Creates an instance.
   */
  public TimeSeriesSearchRequest() {
  }
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code TimeSeriesSearchRequest<T>}.
   * @param <R>  the bean's generic type
   * @return the meta-bean, not null
   */
  @SuppressWarnings("unchecked")
  public static <R> TimeSeriesSearchRequest.Meta<R> meta() {
    return TimeSeriesSearchRequest.Meta.INSTANCE;
  }

  @SuppressWarnings("unchecked")
  @Override
  public TimeSeriesSearchRequest.Meta<T> metaBean() {
    return TimeSeriesSearchRequest.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName) {
    switch (propertyName.hashCode()) {
      case -2092032669:  // pagingRequest
        return getPagingRequest();
      case 1709694943:  // timeSeriesId
        return getTimeSeriesId();
      case 2085582408:  // identifierValue
        return getIdentifierValue();
      case 1368189162:  // identifiers
        return getIdentifiers();
      case 600751303:  // currentDate
        return getCurrentDate();
      case 1272470629:  // dataSource
        return getDataSource();
      case 339742651:  // dataProvider
        return getDataProvider();
      case -386794640:  // dataField
        return getDataField();
      case 951232793:  // observationTime
        return getObservationTime();
      case 109757538:  // start
        return getStart();
      case 100571:  // end
        return getEnd();
      case 1833789738:  // loadTimeSeries
        return isLoadTimeSeries();
      case 1364095295:  // loadDates
        return isLoadDates();
    }
    return super.propertyGet(propertyName);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void propertySet(String propertyName, Object newValue) {
    switch (propertyName.hashCode()) {
      case -2092032669:  // pagingRequest
        setPagingRequest((PagingRequest) newValue);
        return;
      case 1709694943:  // timeSeriesId
        setTimeSeriesId((UniqueIdentifier) newValue);
        return;
      case 2085582408:  // identifierValue
        setIdentifierValue((String) newValue);
        return;
      case 1368189162:  // identifiers
        setIdentifiers((List<Identifier>) newValue);
        return;
      case 600751303:  // currentDate
        setCurrentDate((LocalDate) newValue);
        return;
      case 1272470629:  // dataSource
        setDataSource((String) newValue);
        return;
      case 339742651:  // dataProvider
        setDataProvider((String) newValue);
        return;
      case -386794640:  // dataField
        setDataField((String) newValue);
        return;
      case 951232793:  // observationTime
        setObservationTime((String) newValue);
        return;
      case 109757538:  // start
        setStart((T) newValue);
        return;
      case 100571:  // end
        setEnd((T) newValue);
        return;
      case 1833789738:  // loadTimeSeries
        setLoadTimeSeries((Boolean) newValue);
        return;
      case 1364095295:  // loadDates
        setLoadDates((Boolean) newValue);
        return;
    }
    super.propertySet(propertyName, newValue);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the request for paging.
   * By default all matching items will be returned.
   * @return the value of the property
   */
  public PagingRequest getPagingRequest() {
    return _pagingRequest;
  }

  /**
   * Sets the request for paging.
   * By default all matching items will be returned.
   * @param pagingRequest  the new value of the property
   */
  public void setPagingRequest(PagingRequest pagingRequest) {
    this._pagingRequest = pagingRequest;
  }

  /**
   * Gets the the {@code pagingRequest} property.
   * By default all matching items will be returned.
   * @return the property, not null
   */
  public final Property<PagingRequest> pagingRequest() {
    return metaBean().pagingRequest().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the time series identifier for loading specific data points range.
   * @return the value of the property
   */
  public UniqueIdentifier getTimeSeriesId() {
    return _timeSeriesId;
  }

  /**
   * Sets the time series identifier for loading specific data points range.
   * @param timeSeriesId  the new value of the property
   */
  public void setTimeSeriesId(UniqueIdentifier timeSeriesId) {
    this._timeSeriesId = timeSeriesId;
  }

  /**
   * Gets the the {@code timeSeriesId} property.
   * @return the property, not null
   */
  public final Property<UniqueIdentifier> timeSeriesId() {
    return metaBean().timeSeriesId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the identifier value, matching against the <b>value</b> of the identifiers,
   * null to not match by identifier value.
   * This matches against the {@link Identifier#getValue() value} of the identifier
   * and does not match against the key. Wildcards are allowed.
   * This method is suitable for human searching, whereas the {@code identifiers}
   * search is useful for exact machine searching.
   * @return the value of the property
   */
  public String getIdentifierValue() {
    return _identifierValue;
  }

  /**
   * Sets the identifier value, matching against the <b>value</b> of the identifiers,
   * null to not match by identifier value.
   * This matches against the {@link Identifier#getValue() value} of the identifier
   * and does not match against the key. Wildcards are allowed.
   * This method is suitable for human searching, whereas the {@code identifiers}
   * search is useful for exact machine searching.
   * @param identifierValue  the new value of the property
   */
  public void setIdentifierValue(String identifierValue) {
    this._identifierValue = identifierValue;
  }

  /**
   * Gets the the {@code identifierValue} property.
   * null to not match by identifier value.
   * This matches against the {@link Identifier#getValue() value} of the identifier
   * and does not match against the key. Wildcards are allowed.
   * This method is suitable for human searching, whereas the {@code identifiers}
   * search is useful for exact machine searching.
   * @return the property, not null
   */
  public final Property<String> identifierValue() {
    return metaBean().identifierValue().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the identifiers to match, null to not match on identifiers.
   * This will return time series where at least one complete identifier in the series matches
   * at least one complete identifier in this bundle. Note that an empty bundle will not match
   * anything, whereas a null bundle places no restrictions on the result.
   * This method is suitable for exact machine searching, whereas the {@code identifierValue}
   * search is useful for human searching.
   * @return the value of the property
   */
  public List<Identifier> getIdentifiers() {
    return _identifiers;
  }

  /**
   * Sets the identifiers to match, null to not match on identifiers.
   * This will return time series where at least one complete identifier in the series matches
   * at least one complete identifier in this bundle. Note that an empty bundle will not match
   * anything, whereas a null bundle places no restrictions on the result.
   * This method is suitable for exact machine searching, whereas the {@code identifierValue}
   * search is useful for human searching.
   * @param identifiers  the new value of the property
   */
  public void setIdentifiers(List<Identifier> identifiers) {
    this._identifiers.clear();
    this._identifiers.addAll(identifiers);
  }

  /**
   * Gets the the {@code identifiers} property.
   * This will return time series where at least one complete identifier in the series matches
   * at least one complete identifier in this bundle. Note that an empty bundle will not match
   * anything, whereas a null bundle places no restrictions on the result.
   * This method is suitable for exact machine searching, whereas the {@code identifierValue}
   * search is useful for human searching.
   * @return the property, not null
   */
  public final Property<List<Identifier>> identifiers() {
    return metaBean().identifiers().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets current date (if appicalable for identifiers)
   * @return the value of the property
   */
  public LocalDate getCurrentDate() {
    return _currentDate;
  }

  /**
   * Sets current date (if appicalable for identifiers)
   * @param currentDate  the new value of the property
   */
  public void setCurrentDate(LocalDate currentDate) {
    this._currentDate = currentDate;
  }

  /**
   * Gets the the {@code currentDate} property.
   * @return the property, not null
   */
  public final Property<LocalDate> currentDate() {
    return metaBean().currentDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the data source, null to search all data sources.
   * @return the value of the property
   */
  public String getDataSource() {
    return _dataSource;
  }

  /**
   * Sets the data source, null to search all data sources.
   * @param dataSource  the new value of the property
   */
  public void setDataSource(String dataSource) {
    this._dataSource = dataSource;
  }

  /**
   * Gets the the {@code dataSource} property.
   * @return the property, not null
   */
  public final Property<String> dataSource() {
    return metaBean().dataSource().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the data provider, null to search all data providers.
   * @return the value of the property
   */
  public String getDataProvider() {
    return _dataProvider;
  }

  /**
   * Sets the data provider, null to search all data providers.
   * @param dataProvider  the new value of the property
   */
  public void setDataProvider(String dataProvider) {
    this._dataProvider = dataProvider;
  }

  /**
   * Gets the the {@code dataProvider} property.
   * @return the property, not null
   */
  public final Property<String> dataProvider() {
    return metaBean().dataProvider().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the data field to search, null to search all data fields.
   * @return the value of the property
   */
  public String getDataField() {
    return _dataField;
  }

  /**
   * Sets the data field to search, null to search all data fields.
   * @param dataField  the new value of the property
   */
  public void setDataField(String dataField) {
    this._dataField = dataField;
  }

  /**
   * Gets the the {@code dataField} property.
   * @return the property, not null
   */
  public final Property<String> dataField() {
    return metaBean().dataField().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the observation time, null to search all observation times.
   * @return the value of the property
   */
  public String getObservationTime() {
    return _observationTime;
  }

  /**
   * Sets the observation time, null to search all observation times.
   * @param observationTime  the new value of the property
   */
  public void setObservationTime(String observationTime) {
    this._observationTime = observationTime;
  }

  /**
   * Gets the the {@code observationTime} property.
   * @return the property, not null
   */
  public final Property<String> observationTime() {
    return metaBean().observationTime().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the start date, null to search from start date in data store.
   * @return the value of the property
   */
  public T getStart() {
    return _start;
  }

  /**
   * Sets the start date, null to search from start date in data store.
   * @param start  the new value of the property
   */
  public void setStart(T start) {
    this._start = start;
  }

  /**
   * Gets the the {@code start} property.
   * @return the property, not null
   */
  public final Property<T> start() {
    return metaBean().start().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the end date, null to search until the end date in data store.
   * @return the value of the property
   */
  public T getEnd() {
    return _end;
  }

  /**
   * Sets the end date, null to search until the end date in data store.
   * @param end  the new value of the property
   */
  public void setEnd(T end) {
    this._end = end;
  }

  /**
   * Gets the the {@code end} property.
   * @return the property, not null
   */
  public final Property<T> end() {
    return metaBean().end().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets set to true to load data points, otherwise return just meta data.
   * @return the value of the property
   */
  public boolean isLoadTimeSeries() {
    return _loadTimeSeries;
  }

  /**
   * Sets set to true to load data points, otherwise return just meta data.
   * @param loadTimeSeries  the new value of the property
   */
  public void setLoadTimeSeries(boolean loadTimeSeries) {
    this._loadTimeSeries = loadTimeSeries;
  }

  /**
   * Gets the the {@code loadTimeSeries} property.
   * @return the property, not null
   */
  public final Property<Boolean> loadTimeSeries() {
    return metaBean().loadTimeSeries().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets set to true to load the start and end date for time series.
   * @return the value of the property
   */
  public boolean isLoadDates() {
    return _loadDates;
  }

  /**
   * Sets set to true to load the start and end date for time series.
   * @param loadDates  the new value of the property
   */
  public void setLoadDates(boolean loadDates) {
    this._loadDates = loadDates;
  }

  /**
   * Gets the the {@code loadDates} property.
   * @return the property, not null
   */
  public final Property<Boolean> loadDates() {
    return metaBean().loadDates().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code TimeSeriesSearchRequest}.
   */
  public static class Meta<T> extends BasicMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    @SuppressWarnings("rawtypes")
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code pagingRequest} property.
     */
    private final MetaProperty<PagingRequest> _pagingRequest = DirectMetaProperty.ofReadWrite(this, "pagingRequest", PagingRequest.class);
    /**
     * The meta-property for the {@code timeSeriesId} property.
     */
    private final MetaProperty<UniqueIdentifier> _timeSeriesId = DirectMetaProperty.ofReadWrite(this, "timeSeriesId", UniqueIdentifier.class);
    /**
     * The meta-property for the {@code identifierValue} property.
     */
    private final MetaProperty<String> _identifierValue = DirectMetaProperty.ofReadWrite(this, "identifierValue", String.class);
    /**
     * The meta-property for the {@code identifiers} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<List<Identifier>> _identifiers = DirectMetaProperty.ofReadWrite(this, "identifiers", (Class) List.class);
    /**
     * The meta-property for the {@code currentDate} property.
     */
    private final MetaProperty<LocalDate> _currentDate = DirectMetaProperty.ofReadWrite(this, "currentDate", LocalDate.class);
    /**
     * The meta-property for the {@code dataSource} property.
     */
    private final MetaProperty<String> _dataSource = DirectMetaProperty.ofReadWrite(this, "dataSource", String.class);
    /**
     * The meta-property for the {@code dataProvider} property.
     */
    private final MetaProperty<String> _dataProvider = DirectMetaProperty.ofReadWrite(this, "dataProvider", String.class);
    /**
     * The meta-property for the {@code dataField} property.
     */
    private final MetaProperty<String> _dataField = DirectMetaProperty.ofReadWrite(this, "dataField", String.class);
    /**
     * The meta-property for the {@code observationTime} property.
     */
    private final MetaProperty<String> _observationTime = DirectMetaProperty.ofReadWrite(this, "observationTime", String.class);
    /**
     * The meta-property for the {@code start} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<T> _start = (DirectMetaProperty) DirectMetaProperty.ofReadWrite(this, "start", Object.class);
    /**
     * The meta-property for the {@code end} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<T> _end = (DirectMetaProperty) DirectMetaProperty.ofReadWrite(this, "end", Object.class);
    /**
     * The meta-property for the {@code loadTimeSeries} property.
     */
    private final MetaProperty<Boolean> _loadTimeSeries = DirectMetaProperty.ofReadWrite(this, "loadTimeSeries", Boolean.TYPE);
    /**
     * The meta-property for the {@code loadDates} property.
     */
    private final MetaProperty<Boolean> _loadDates = DirectMetaProperty.ofReadWrite(this, "loadDates", Boolean.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<Object>> _map;

    @SuppressWarnings({"unchecked", "rawtypes" })
    protected Meta() {
      LinkedHashMap temp = new LinkedHashMap();
      temp.put("pagingRequest", _pagingRequest);
      temp.put("timeSeriesId", _timeSeriesId);
      temp.put("identifierValue", _identifierValue);
      temp.put("identifiers", _identifiers);
      temp.put("currentDate", _currentDate);
      temp.put("dataSource", _dataSource);
      temp.put("dataProvider", _dataProvider);
      temp.put("dataField", _dataField);
      temp.put("observationTime", _observationTime);
      temp.put("start", _start);
      temp.put("end", _end);
      temp.put("loadTimeSeries", _loadTimeSeries);
      temp.put("loadDates", _loadDates);
      _map = Collections.unmodifiableMap(temp);
    }

    @Override
    public TimeSeriesSearchRequest<T> createBean() {
      return new TimeSeriesSearchRequest<T>();
    }

    @SuppressWarnings({"unchecked", "rawtypes" })
    @Override
    public Class<? extends TimeSeriesSearchRequest<T>> beanType() {
      return (Class) TimeSeriesSearchRequest.class;
    }

    @Override
    public Map<String, MetaProperty<Object>> metaPropertyMap() {
      return _map;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code pagingRequest} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<PagingRequest> pagingRequest() {
      return _pagingRequest;
    }

    /**
     * The meta-property for the {@code timeSeriesId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<UniqueIdentifier> timeSeriesId() {
      return _timeSeriesId;
    }

    /**
     * The meta-property for the {@code identifierValue} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> identifierValue() {
      return _identifierValue;
    }

    /**
     * The meta-property for the {@code identifiers} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<List<Identifier>> identifiers() {
      return _identifiers;
    }

    /**
     * The meta-property for the {@code currentDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<LocalDate> currentDate() {
      return _currentDate;
    }

    /**
     * The meta-property for the {@code dataSource} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> dataSource() {
      return _dataSource;
    }

    /**
     * The meta-property for the {@code dataProvider} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> dataProvider() {
      return _dataProvider;
    }

    /**
     * The meta-property for the {@code dataField} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> dataField() {
      return _dataField;
    }

    /**
     * The meta-property for the {@code observationTime} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> observationTime() {
      return _observationTime;
    }

    /**
     * The meta-property for the {@code start} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<T> start() {
      return _start;
    }

    /**
     * The meta-property for the {@code end} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<T> end() {
      return _end;
    }

    /**
     * The meta-property for the {@code loadTimeSeries} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> loadTimeSeries() {
      return _loadTimeSeries;
    }

    /**
     * The meta-property for the {@code loadDates} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> loadDates() {
      return _loadDates;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
