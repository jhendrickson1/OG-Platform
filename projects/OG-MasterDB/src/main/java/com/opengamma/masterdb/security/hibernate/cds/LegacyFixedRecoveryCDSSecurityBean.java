/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.masterdb.security.hibernate.cds;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

/**
 * 
 */
@BeanDefinition
public class LegacyFixedRecoveryCDSSecurityBean extends CreditDefaultSwapSecurityBean {
  
  @PropertyDefinition
  private Double _parSpread;

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code LegacyFixedRecoveryCDSSecurityBean}.
   * @return the meta-bean, not null
   */
  public static LegacyFixedRecoveryCDSSecurityBean.Meta meta() {
    return LegacyFixedRecoveryCDSSecurityBean.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(LegacyFixedRecoveryCDSSecurityBean.Meta.INSTANCE);
  }

  @Override
  public LegacyFixedRecoveryCDSSecurityBean.Meta metaBean() {
    return LegacyFixedRecoveryCDSSecurityBean.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 1556795764:  // parSpread
        return getParSpread();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 1556795764:  // parSpread
        setParSpread((Double) newValue);
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
      LegacyFixedRecoveryCDSSecurityBean other = (LegacyFixedRecoveryCDSSecurityBean) obj;
      return JodaBeanUtils.equal(getParSpread(), other.getParSpread()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getParSpread());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the parSpread.
   * @return the value of the property
   */
  public Double getParSpread() {
    return _parSpread;
  }

  /**
   * Sets the parSpread.
   * @param parSpread  the new value of the property
   */
  public void setParSpread(Double parSpread) {
    this._parSpread = parSpread;
  }

  /**
   * Gets the the {@code parSpread} property.
   * @return the property, not null
   */
  public final Property<Double> parSpread() {
    return metaBean().parSpread().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code LegacyFixedRecoveryCDSSecurityBean}.
   */
  public static class Meta extends CreditDefaultSwapSecurityBean.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code parSpread} property.
     */
    private final MetaProperty<Double> _parSpread = DirectMetaProperty.ofReadWrite(
        this, "parSpread", LegacyFixedRecoveryCDSSecurityBean.class, Double.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "parSpread");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1556795764:  // parSpread
          return _parSpread;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends LegacyFixedRecoveryCDSSecurityBean> builder() {
      return new DirectBeanBuilder<LegacyFixedRecoveryCDSSecurityBean>(new LegacyFixedRecoveryCDSSecurityBean());
    }

    @Override
    public Class<? extends LegacyFixedRecoveryCDSSecurityBean> beanType() {
      return LegacyFixedRecoveryCDSSecurityBean.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code parSpread} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> parSpread() {
      return _parSpread;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
