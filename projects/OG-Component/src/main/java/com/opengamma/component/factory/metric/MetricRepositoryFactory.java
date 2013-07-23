/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.component.factory.metric;

import info.ganglia.gmetric4j.gmetric.GMetric;
import info.ganglia.gmetric4j.gmetric.GMetric.UDPAddressingMode;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import com.codahale.metrics.ganglia.GangliaReporter;
import com.opengamma.component.ComponentRepository;
import com.opengamma.component.factory.AbstractComponentFactory;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.metric.OpenGammaMetricRegistry;

/**
 * 
 */
@BeanDefinition
public class MetricRepositoryFactory extends AbstractComponentFactory {
  @PropertyDefinition(validate = "notEmpty")
  private String _registryName;
  
  @PropertyDefinition
  private boolean _jmxPublish = true;

  @PropertyDefinition
  private boolean _slf4jPublish = true;

  @PropertyDefinition
  private boolean _gangliaPublish = true;

  @PropertyDefinition
  private String _gangliaAddress;
  
  @PropertyDefinition
  private Integer _gangliaPort;

  @PropertyDefinition
  private String _gangliaAddressingMode;
  
  @PropertyDefinition
  private Integer _gangliaTtl = 1;

  @Override
  public void init(ComponentRepository repo, LinkedHashMap<String, String> configuration) throws Exception {
    MetricRegistry summaryRegistry = new MetricRegistry();
    MetricRegistry detailedRegistry = new MetricRegistry();
    
    if (isJmxPublish()) {
      JmxReporter jmxReporter = JmxReporter.forRegistry(summaryRegistry).build();
      jmxReporter.start();
      jmxReporter = JmxReporter.forRegistry(detailedRegistry).build();
      jmxReporter.start();
    }
    
    if (isSlf4jPublish()) {
      Slf4jReporter logReporter = Slf4jReporter.forRegistry(summaryRegistry)
          .outputTo(LoggerFactory.getLogger(OpenGammaMetricRegistry.class))
          .convertRatesTo(TimeUnit.SECONDS)
          .convertDurationsTo(TimeUnit.MILLISECONDS)
          .build();
      logReporter.start(1, TimeUnit.MINUTES);
      logReporter = Slf4jReporter.forRegistry(detailedRegistry)
          .outputTo(LoggerFactory.getLogger(OpenGammaMetricRegistry.class))
          .convertRatesTo(TimeUnit.SECONDS)
          .convertDurationsTo(TimeUnit.MILLISECONDS)
          .build();
      logReporter.start(1, TimeUnit.MINUTES);
    }
    
    if (isGangliaPublish()) {
      // Only publish on Ganglia for summary.
      ArgumentChecker.notNull(getGangliaAddress(), "gangliaAddress");
      ArgumentChecker.notNull(getGangliaPort(), "gangliaPort");
      ArgumentChecker.notNull(getGangliaAddressingMode(), "gangliaAddressingMode");
      ArgumentChecker.notNull(getGangliaTtl(), "gangliaTtl");
      GMetric ganglia = new GMetric(getGangliaAddress(), getGangliaPort(), UDPAddressingMode.valueOf(getGangliaAddressingMode()), getGangliaTtl(), true);
      GangliaReporter gangliaReporter = GangliaReporter.forRegistry(summaryRegistry)
          .convertRatesTo(TimeUnit.SECONDS)
          .convertDurationsTo(TimeUnit.MILLISECONDS)
          .build(ganglia);
      gangliaReporter.start(1, TimeUnit.MINUTES);
    }
    
    OpenGammaMetricRegistry.setSummaryRegistry(summaryRegistry);
    OpenGammaMetricRegistry.setDetailedRegistry(detailedRegistry);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code MetricRepositoryFactory}.
   * @return the meta-bean, not null
   */
  public static MetricRepositoryFactory.Meta meta() {
    return MetricRepositoryFactory.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(MetricRepositoryFactory.Meta.INSTANCE);
  }

  @Override
  public MetricRepositoryFactory.Meta metaBean() {
    return MetricRepositoryFactory.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -1329285016:  // registryName
        return getRegistryName();
      case 1313494970:  // jmxPublish
        return isJmxPublish();
      case 283122412:  // slf4jPublish
        return isSlf4jPublish();
      case 113968702:  // gangliaPublish
        return isGangliaPublish();
      case -798358237:  // gangliaAddress
        return getGangliaAddress();
      case 44258738:  // gangliaPort
        return getGangliaPort();
      case -772603358:  // gangliaAddressingMode
        return getGangliaAddressingMode();
      case 555621019:  // gangliaTtl
        return getGangliaTtl();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -1329285016:  // registryName
        setRegistryName((String) newValue);
        return;
      case 1313494970:  // jmxPublish
        setJmxPublish((Boolean) newValue);
        return;
      case 283122412:  // slf4jPublish
        setSlf4jPublish((Boolean) newValue);
        return;
      case 113968702:  // gangliaPublish
        setGangliaPublish((Boolean) newValue);
        return;
      case -798358237:  // gangliaAddress
        setGangliaAddress((String) newValue);
        return;
      case 44258738:  // gangliaPort
        setGangliaPort((Integer) newValue);
        return;
      case -772603358:  // gangliaAddressingMode
        setGangliaAddressingMode((String) newValue);
        return;
      case 555621019:  // gangliaTtl
        setGangliaTtl((Integer) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notEmpty(_registryName, "registryName");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      MetricRepositoryFactory other = (MetricRepositoryFactory) obj;
      return JodaBeanUtils.equal(getRegistryName(), other.getRegistryName()) &&
          JodaBeanUtils.equal(isJmxPublish(), other.isJmxPublish()) &&
          JodaBeanUtils.equal(isSlf4jPublish(), other.isSlf4jPublish()) &&
          JodaBeanUtils.equal(isGangliaPublish(), other.isGangliaPublish()) &&
          JodaBeanUtils.equal(getGangliaAddress(), other.getGangliaAddress()) &&
          JodaBeanUtils.equal(getGangliaPort(), other.getGangliaPort()) &&
          JodaBeanUtils.equal(getGangliaAddressingMode(), other.getGangliaAddressingMode()) &&
          JodaBeanUtils.equal(getGangliaTtl(), other.getGangliaTtl()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getRegistryName());
    hash += hash * 31 + JodaBeanUtils.hashCode(isJmxPublish());
    hash += hash * 31 + JodaBeanUtils.hashCode(isSlf4jPublish());
    hash += hash * 31 + JodaBeanUtils.hashCode(isGangliaPublish());
    hash += hash * 31 + JodaBeanUtils.hashCode(getGangliaAddress());
    hash += hash * 31 + JodaBeanUtils.hashCode(getGangliaPort());
    hash += hash * 31 + JodaBeanUtils.hashCode(getGangliaAddressingMode());
    hash += hash * 31 + JodaBeanUtils.hashCode(getGangliaTtl());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the registryName.
   * @return the value of the property, not null
   */
  public String getRegistryName() {
    return _registryName;
  }

  /**
   * Sets the registryName.
   * @param registryName  the new value of the property, not null
   */
  public void setRegistryName(String registryName) {
    JodaBeanUtils.notEmpty(registryName, "registryName");
    this._registryName = registryName;
  }

  /**
   * Gets the the {@code registryName} property.
   * @return the property, not null
   */
  public final Property<String> registryName() {
    return metaBean().registryName().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the jmxPublish.
   * @return the value of the property
   */
  public boolean isJmxPublish() {
    return _jmxPublish;
  }

  /**
   * Sets the jmxPublish.
   * @param jmxPublish  the new value of the property
   */
  public void setJmxPublish(boolean jmxPublish) {
    this._jmxPublish = jmxPublish;
  }

  /**
   * Gets the the {@code jmxPublish} property.
   * @return the property, not null
   */
  public final Property<Boolean> jmxPublish() {
    return metaBean().jmxPublish().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the slf4jPublish.
   * @return the value of the property
   */
  public boolean isSlf4jPublish() {
    return _slf4jPublish;
  }

  /**
   * Sets the slf4jPublish.
   * @param slf4jPublish  the new value of the property
   */
  public void setSlf4jPublish(boolean slf4jPublish) {
    this._slf4jPublish = slf4jPublish;
  }

  /**
   * Gets the the {@code slf4jPublish} property.
   * @return the property, not null
   */
  public final Property<Boolean> slf4jPublish() {
    return metaBean().slf4jPublish().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the gangliaPublish.
   * @return the value of the property
   */
  public boolean isGangliaPublish() {
    return _gangliaPublish;
  }

  /**
   * Sets the gangliaPublish.
   * @param gangliaPublish  the new value of the property
   */
  public void setGangliaPublish(boolean gangliaPublish) {
    this._gangliaPublish = gangliaPublish;
  }

  /**
   * Gets the the {@code gangliaPublish} property.
   * @return the property, not null
   */
  public final Property<Boolean> gangliaPublish() {
    return metaBean().gangliaPublish().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the gangliaAddress.
   * @return the value of the property
   */
  public String getGangliaAddress() {
    return _gangliaAddress;
  }

  /**
   * Sets the gangliaAddress.
   * @param gangliaAddress  the new value of the property
   */
  public void setGangliaAddress(String gangliaAddress) {
    this._gangliaAddress = gangliaAddress;
  }

  /**
   * Gets the the {@code gangliaAddress} property.
   * @return the property, not null
   */
  public final Property<String> gangliaAddress() {
    return metaBean().gangliaAddress().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the gangliaPort.
   * @return the value of the property
   */
  public Integer getGangliaPort() {
    return _gangliaPort;
  }

  /**
   * Sets the gangliaPort.
   * @param gangliaPort  the new value of the property
   */
  public void setGangliaPort(Integer gangliaPort) {
    this._gangliaPort = gangliaPort;
  }

  /**
   * Gets the the {@code gangliaPort} property.
   * @return the property, not null
   */
  public final Property<Integer> gangliaPort() {
    return metaBean().gangliaPort().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the gangliaAddressingMode.
   * @return the value of the property
   */
  public String getGangliaAddressingMode() {
    return _gangliaAddressingMode;
  }

  /**
   * Sets the gangliaAddressingMode.
   * @param gangliaAddressingMode  the new value of the property
   */
  public void setGangliaAddressingMode(String gangliaAddressingMode) {
    this._gangliaAddressingMode = gangliaAddressingMode;
  }

  /**
   * Gets the the {@code gangliaAddressingMode} property.
   * @return the property, not null
   */
  public final Property<String> gangliaAddressingMode() {
    return metaBean().gangliaAddressingMode().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the gangliaTtl.
   * @return the value of the property
   */
  public Integer getGangliaTtl() {
    return _gangliaTtl;
  }

  /**
   * Sets the gangliaTtl.
   * @param gangliaTtl  the new value of the property
   */
  public void setGangliaTtl(Integer gangliaTtl) {
    this._gangliaTtl = gangliaTtl;
  }

  /**
   * Gets the the {@code gangliaTtl} property.
   * @return the property, not null
   */
  public final Property<Integer> gangliaTtl() {
    return metaBean().gangliaTtl().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code MetricRepositoryFactory}.
   */
  public static class Meta extends AbstractComponentFactory.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code registryName} property.
     */
    private final MetaProperty<String> _registryName = DirectMetaProperty.ofReadWrite(
        this, "registryName", MetricRepositoryFactory.class, String.class);
    /**
     * The meta-property for the {@code jmxPublish} property.
     */
    private final MetaProperty<Boolean> _jmxPublish = DirectMetaProperty.ofReadWrite(
        this, "jmxPublish", MetricRepositoryFactory.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code slf4jPublish} property.
     */
    private final MetaProperty<Boolean> _slf4jPublish = DirectMetaProperty.ofReadWrite(
        this, "slf4jPublish", MetricRepositoryFactory.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code gangliaPublish} property.
     */
    private final MetaProperty<Boolean> _gangliaPublish = DirectMetaProperty.ofReadWrite(
        this, "gangliaPublish", MetricRepositoryFactory.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code gangliaAddress} property.
     */
    private final MetaProperty<String> _gangliaAddress = DirectMetaProperty.ofReadWrite(
        this, "gangliaAddress", MetricRepositoryFactory.class, String.class);
    /**
     * The meta-property for the {@code gangliaPort} property.
     */
    private final MetaProperty<Integer> _gangliaPort = DirectMetaProperty.ofReadWrite(
        this, "gangliaPort", MetricRepositoryFactory.class, Integer.class);
    /**
     * The meta-property for the {@code gangliaAddressingMode} property.
     */
    private final MetaProperty<String> _gangliaAddressingMode = DirectMetaProperty.ofReadWrite(
        this, "gangliaAddressingMode", MetricRepositoryFactory.class, String.class);
    /**
     * The meta-property for the {@code gangliaTtl} property.
     */
    private final MetaProperty<Integer> _gangliaTtl = DirectMetaProperty.ofReadWrite(
        this, "gangliaTtl", MetricRepositoryFactory.class, Integer.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "registryName",
        "jmxPublish",
        "slf4jPublish",
        "gangliaPublish",
        "gangliaAddress",
        "gangliaPort",
        "gangliaAddressingMode",
        "gangliaTtl");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1329285016:  // registryName
          return _registryName;
        case 1313494970:  // jmxPublish
          return _jmxPublish;
        case 283122412:  // slf4jPublish
          return _slf4jPublish;
        case 113968702:  // gangliaPublish
          return _gangliaPublish;
        case -798358237:  // gangliaAddress
          return _gangliaAddress;
        case 44258738:  // gangliaPort
          return _gangliaPort;
        case -772603358:  // gangliaAddressingMode
          return _gangliaAddressingMode;
        case 555621019:  // gangliaTtl
          return _gangliaTtl;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends MetricRepositoryFactory> builder() {
      return new DirectBeanBuilder<MetricRepositoryFactory>(new MetricRepositoryFactory());
    }

    @Override
    public Class<? extends MetricRepositoryFactory> beanType() {
      return MetricRepositoryFactory.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code registryName} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> registryName() {
      return _registryName;
    }

    /**
     * The meta-property for the {@code jmxPublish} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> jmxPublish() {
      return _jmxPublish;
    }

    /**
     * The meta-property for the {@code slf4jPublish} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> slf4jPublish() {
      return _slf4jPublish;
    }

    /**
     * The meta-property for the {@code gangliaPublish} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> gangliaPublish() {
      return _gangliaPublish;
    }

    /**
     * The meta-property for the {@code gangliaAddress} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> gangliaAddress() {
      return _gangliaAddress;
    }

    /**
     * The meta-property for the {@code gangliaPort} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Integer> gangliaPort() {
      return _gangliaPort;
    }

    /**
     * The meta-property for the {@code gangliaAddressingMode} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> gangliaAddressingMode() {
      return _gangliaAddressingMode;
    }

    /**
     * The meta-property for the {@code gangliaTtl} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Integer> gangliaTtl() {
      return _gangliaTtl;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
