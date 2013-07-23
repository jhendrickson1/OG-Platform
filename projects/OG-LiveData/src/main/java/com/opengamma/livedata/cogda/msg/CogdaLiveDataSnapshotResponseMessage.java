/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.livedata.cogda.msg;

import java.util.Map;

import org.fudgemsg.FudgeMsg;
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
public class CogdaLiveDataSnapshotResponseMessage extends CogdaLiveDataCommandResponseMessage {
  /**
   * The values in the snapshot.
   */
  @PropertyDefinition(validate = "notNull")
  private FudgeMsg _values;
  
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code CogdaLiveDataSnapshotResponseMessage}.
   * @return the meta-bean, not null
   */
  public static CogdaLiveDataSnapshotResponseMessage.Meta meta() {
    return CogdaLiveDataSnapshotResponseMessage.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(CogdaLiveDataSnapshotResponseMessage.Meta.INSTANCE);
  }

  @Override
  public CogdaLiveDataSnapshotResponseMessage.Meta metaBean() {
    return CogdaLiveDataSnapshotResponseMessage.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -823812830:  // values
        return getValues();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -823812830:  // values
        setValues((FudgeMsg) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_values, "values");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      CogdaLiveDataSnapshotResponseMessage other = (CogdaLiveDataSnapshotResponseMessage) obj;
      return JodaBeanUtils.equal(getValues(), other.getValues()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getValues());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the values in the snapshot.
   * @return the value of the property, not null
   */
  public FudgeMsg getValues() {
    return _values;
  }

  /**
   * Sets the values in the snapshot.
   * @param values  the new value of the property, not null
   */
  public void setValues(FudgeMsg values) {
    JodaBeanUtils.notNull(values, "values");
    this._values = values;
  }

  /**
   * Gets the the {@code values} property.
   * @return the property, not null
   */
  public final Property<FudgeMsg> values() {
    return metaBean().values().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code CogdaLiveDataSnapshotResponseMessage}.
   */
  public static class Meta extends CogdaLiveDataCommandResponseMessage.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code values} property.
     */
    private final MetaProperty<FudgeMsg> _values = DirectMetaProperty.ofReadWrite(
        this, "values", CogdaLiveDataSnapshotResponseMessage.class, FudgeMsg.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "values");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -823812830:  // values
          return _values;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends CogdaLiveDataSnapshotResponseMessage> builder() {
      return new DirectBeanBuilder<CogdaLiveDataSnapshotResponseMessage>(new CogdaLiveDataSnapshotResponseMessage());
    }

    @Override
    public Class<? extends CogdaLiveDataSnapshotResponseMessage> beanType() {
      return CogdaLiveDataSnapshotResponseMessage.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code values} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<FudgeMsg> values() {
      return _values;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
