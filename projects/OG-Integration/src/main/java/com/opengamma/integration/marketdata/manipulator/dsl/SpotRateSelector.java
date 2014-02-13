/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.marketdata.manipulator.dsl;

import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.ImmutableConstructor;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.engine.marketdata.manipulator.DistinctMarketDataSelector;
import com.opengamma.engine.marketdata.manipulator.SelectorResolver;
import com.opengamma.engine.target.ComputationTargetType;
import com.opengamma.engine.value.ValueRequirementNames;
import com.opengamma.engine.value.ValueSpecification;
import com.opengamma.financial.currency.CurrencyPair;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.money.UnorderedCurrencyPair;

/**
 * Selects values with a requirement name of {@link ValueRequirementNames#SPOT_RATE} and a target type of
 * {@link CurrencyPair} or {@link UnorderedCurrencyPair}.
 * TODO ImmutableBean
 */
@BeanDefinition
public final class SpotRateSelector implements DistinctMarketDataSelector, ImmutableBean {

  /** Calc configs to which this selector will apply, null will match any config. */
  @PropertyDefinition
  private final Set<String> _calcConfigNames;

  @PropertyDefinition
  private final Set<CurrencyPair> _currencyPairs;
  //private final Set<UnorderedCurrencyPair> _unorderedCurrencyPairs = Sets.newHashSet();

  @ImmutableConstructor
  /* package */ SpotRateSelector(Set<String> calcConfigNames, Set<CurrencyPair> currencyPairs) {
    _calcConfigNames = calcConfigNames;
    _currencyPairs = ArgumentChecker.notEmpty(currencyPairs, "currencyPair");
    /*for (CurrencyPair currencyPair : currencyPairs) {
      _unorderedCurrencyPairs.add(UnorderedCurrencyPair.of(currencyPair.getBase(), currencyPair.getCounter()));
    }*/
    // TODO sanity check currency pairs. ordered and unordered sets should be the same size
    // otherwise it means a pair and its inverse are both included which can't be done
  }

  @Override
  public boolean hasSelectionsDefined() {
    return true;
  }

  // TODO this can match the same value twice - once when the pair is ordered, once when unordered
  @Override
  public DistinctMarketDataSelector findMatchingSelector(ValueSpecification valueSpecification,
                                                         String calcConfigName,
                                                         SelectorResolver resolver) {
    if (_calcConfigNames != null && !_calcConfigNames.contains(calcConfigName)) {
      return null;
    }
    if (!ValueRequirementNames.SPOT_RATE.equals(valueSpecification.getValueName())) {
      return null;
    }
    ComputationTargetType targetType = valueSpecification.getTargetSpecification().getType();
    String idValue = valueSpecification.getTargetSpecification().getUniqueId().getValue();
    /*if (targetType.equals(ComputationTargetType.UNORDERED_CURRENCY_PAIR)) {
      UnorderedCurrencyPair unorderedCurrencyPair = UnorderedCurrencyPair.parse(idValue);
      if (!_unorderedCurrencyPairs.contains(unorderedCurrencyPair)) {
        return null;
      }
    } else */
    if (targetType.equals(CurrencyPair.TYPE)) {
      CurrencyPair currencyPair = CurrencyPair.parse(idValue);
      if (!_currencyPairs.contains(currencyPair) && !_currencyPairs.contains(currencyPair.inverse())) {
        return null;
      }
    } else {
      return null;
    }
    return this;
  }
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code SpotRateSelector}.
   * @return the meta-bean, not null
   */
  public static SpotRateSelector.Meta meta() {
    return SpotRateSelector.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(SpotRateSelector.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static SpotRateSelector.Builder builder() {
    return new SpotRateSelector.Builder();
  }

  @Override
  public SpotRateSelector.Meta metaBean() {
    return SpotRateSelector.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets calc configs to which this selector will apply, null will match any config.
   * @return the value of the property
   */
  public Set<String> getCalcConfigNames() {
    return _calcConfigNames;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the currencyPairs.
   * @return the value of the property
   */
  public Set<CurrencyPair> getCurrencyPairs() {
    return _currencyPairs;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public SpotRateSelector clone() {
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      SpotRateSelector other = (SpotRateSelector) obj;
      return JodaBeanUtils.equal(getCalcConfigNames(), other.getCalcConfigNames()) &&
          JodaBeanUtils.equal(getCurrencyPairs(), other.getCurrencyPairs());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getCalcConfigNames());
    hash += hash * 31 + JodaBeanUtils.hashCode(getCurrencyPairs());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("SpotRateSelector{");
    buf.append("calcConfigNames").append('=').append(getCalcConfigNames()).append(',').append(' ');
    buf.append("currencyPairs").append('=').append(JodaBeanUtils.toString(getCurrencyPairs()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code SpotRateSelector}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code calcConfigNames} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Set<String>> _calcConfigNames = DirectMetaProperty.ofImmutable(
        this, "calcConfigNames", SpotRateSelector.class, (Class) Set.class);
    /**
     * The meta-property for the {@code currencyPairs} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Set<CurrencyPair>> _currencyPairs = DirectMetaProperty.ofImmutable(
        this, "currencyPairs", SpotRateSelector.class, (Class) Set.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "calcConfigNames",
        "currencyPairs");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1364849553:  // calcConfigNames
          return _calcConfigNames;
        case 1094810440:  // currencyPairs
          return _currencyPairs;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public SpotRateSelector.Builder builder() {
      return new SpotRateSelector.Builder();
    }

    @Override
    public Class<? extends SpotRateSelector> beanType() {
      return SpotRateSelector.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code calcConfigNames} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Set<String>> calcConfigNames() {
      return _calcConfigNames;
    }

    /**
     * The meta-property for the {@code currencyPairs} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Set<CurrencyPair>> currencyPairs() {
      return _currencyPairs;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 1364849553:  // calcConfigNames
          return ((SpotRateSelector) bean).getCalcConfigNames();
        case 1094810440:  // currencyPairs
          return ((SpotRateSelector) bean).getCurrencyPairs();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code SpotRateSelector}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<SpotRateSelector> {

    private Set<String> _calcConfigNames;
    private Set<CurrencyPair> _currencyPairs;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(SpotRateSelector beanToCopy) {
      this._calcConfigNames = (beanToCopy.getCalcConfigNames() != null ? new HashSet<String>(beanToCopy.getCalcConfigNames()) : null);
      this._currencyPairs = (beanToCopy.getCurrencyPairs() != null ? new HashSet<CurrencyPair>(beanToCopy.getCurrencyPairs()) : null);
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1364849553:  // calcConfigNames
          return _calcConfigNames;
        case 1094810440:  // currencyPairs
          return _currencyPairs;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 1364849553:  // calcConfigNames
          this._calcConfigNames = (Set<String>) newValue;
          break;
        case 1094810440:  // currencyPairs
          this._currencyPairs = (Set<CurrencyPair>) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public SpotRateSelector build() {
      return new SpotRateSelector(
          _calcConfigNames,
          _currencyPairs);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code calcConfigNames} property in the builder.
     * @param calcConfigNames  the new value
     * @return this, for chaining, not null
     */
    public Builder calcConfigNames(Set<String> calcConfigNames) {
      this._calcConfigNames = calcConfigNames;
      return this;
    }

    /**
     * Sets the {@code currencyPairs} property in the builder.
     * @param currencyPairs  the new value
     * @return this, for chaining, not null
     */
    public Builder currencyPairs(Set<CurrencyPair> currencyPairs) {
      this._currencyPairs = currencyPairs;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(96);
      buf.append("SpotRateSelector.Builder{");
      buf.append("calcConfigNames").append('=').append(JodaBeanUtils.toString(_calcConfigNames)).append(',').append(' ');
      buf.append("currencyPairs").append('=').append(JodaBeanUtils.toString(_currencyPairs));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
