/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.util.result;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.google.common.base.Throwables;
import com.opengamma.util.ArgumentChecker;

/**
 * A serializable container for the details of a {@link Throwable}.
 * <p>
 * This class allows information about an arbitrary {@link Throwable} instance
 * to be stored and serialized.
 * @deprecated use {@link Failure} fields to store details of throwables that cause failures
 */
@Deprecated
@BeanDefinition
public final class ThrowableDetails implements ImmutableBean {

  /**
   * The type of the throwable.
   */
  @PropertyDefinition(validate = "notNull")
  private final Class<? extends Throwable> _type;
  /**
   * The message of the throwable or the class name if there is no message.
   */
  @PropertyDefinition(validate = "notNull")
  private final String _message;
  /**
   * The stack trace of the throwable.
   */
  @PropertyDefinition(validate = "notNull")
  private final String _stackTrace;

  //-------------------------------------------------------------------------
  /**
   * Obtains an instance of {@code ThrowableDetails}.
   * 
   * @param throwable  the throwable to create an instance for
   * @return the throwable details instance
   */
  public static ThrowableDetails of(Throwable throwable) {
    ArgumentChecker.notNull(throwable, "throwable");
    String message;
    if (throwable.getMessage() != null) {
      message = throwable.getMessage();
    } else {
      message = throwable.getClass().getSimpleName();
    }
    String stackTrace = Throwables.getStackTraceAsString(throwable);
    return new ThrowableDetails(throwable.getClass(), message, stackTrace);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ThrowableDetails}.
   * @return the meta-bean, not null
   */
  public static ThrowableDetails.Meta meta() {
    return ThrowableDetails.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ThrowableDetails.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static ThrowableDetails.Builder builder() {
    return new ThrowableDetails.Builder();
  }

  private ThrowableDetails(
      Class<? extends Throwable> type,
      String message,
      String stackTrace) {
    JodaBeanUtils.notNull(type, "type");
    JodaBeanUtils.notNull(message, "message");
    JodaBeanUtils.notNull(stackTrace, "stackTrace");
    this._type = type;
    this._message = message;
    this._stackTrace = stackTrace;
  }

  @Override
  public ThrowableDetails.Meta metaBean() {
    return ThrowableDetails.Meta.INSTANCE;
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
   * Gets the type of the throwable.
   * @return the value of the property, not null
   */
  public Class<? extends Throwable> getType() {
    return _type;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the message of the throwable or the class name if there is no message.
   * @return the value of the property, not null
   */
  public String getMessage() {
    return _message;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the stack trace of the throwable.
   * @return the value of the property, not null
   */
  public String getStackTrace() {
    return _stackTrace;
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
  public ThrowableDetails clone() {
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ThrowableDetails other = (ThrowableDetails) obj;
      return JodaBeanUtils.equal(getType(), other.getType()) &&
          JodaBeanUtils.equal(getMessage(), other.getMessage()) &&
          JodaBeanUtils.equal(getStackTrace(), other.getStackTrace());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getType());
    hash += hash * 31 + JodaBeanUtils.hashCode(getMessage());
    hash += hash * 31 + JodaBeanUtils.hashCode(getStackTrace());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(128);
    buf.append("ThrowableDetails{");
    buf.append("type").append('=').append(getType()).append(',').append(' ');
    buf.append("message").append('=').append(getMessage()).append(',').append(' ');
    buf.append("stackTrace").append('=').append(JodaBeanUtils.toString(getStackTrace()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ThrowableDetails}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code type} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Class<? extends Throwable>> _type = DirectMetaProperty.ofImmutable(
        this, "type", ThrowableDetails.class, (Class) Class.class);
    /**
     * The meta-property for the {@code message} property.
     */
    private final MetaProperty<String> _message = DirectMetaProperty.ofImmutable(
        this, "message", ThrowableDetails.class, String.class);
    /**
     * The meta-property for the {@code stackTrace} property.
     */
    private final MetaProperty<String> _stackTrace = DirectMetaProperty.ofImmutable(
        this, "stackTrace", ThrowableDetails.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "type",
        "message",
        "stackTrace");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3575610:  // type
          return _type;
        case 954925063:  // message
          return _message;
        case 2026279837:  // stackTrace
          return _stackTrace;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public ThrowableDetails.Builder builder() {
      return new ThrowableDetails.Builder();
    }

    @Override
    public Class<? extends ThrowableDetails> beanType() {
      return ThrowableDetails.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code type} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Class<? extends Throwable>> type() {
      return _type;
    }

    /**
     * The meta-property for the {@code message} property.
     * @return the meta-property, not null
     */
    public MetaProperty<String> message() {
      return _message;
    }

    /**
     * The meta-property for the {@code stackTrace} property.
     * @return the meta-property, not null
     */
    public MetaProperty<String> stackTrace() {
      return _stackTrace;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3575610:  // type
          return ((ThrowableDetails) bean).getType();
        case 954925063:  // message
          return ((ThrowableDetails) bean).getMessage();
        case 2026279837:  // stackTrace
          return ((ThrowableDetails) bean).getStackTrace();
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
   * The bean-builder for {@code ThrowableDetails}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<ThrowableDetails> {

    private Class<? extends Throwable> _type;
    private String _message;
    private String _stackTrace;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(ThrowableDetails beanToCopy) {
      this._type = beanToCopy.getType();
      this._message = beanToCopy.getMessage();
      this._stackTrace = beanToCopy.getStackTrace();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3575610:  // type
          return _type;
        case 954925063:  // message
          return _message;
        case 2026279837:  // stackTrace
          return _stackTrace;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 3575610:  // type
          this._type = (Class<? extends Throwable>) newValue;
          break;
        case 954925063:  // message
          this._message = (String) newValue;
          break;
        case 2026279837:  // stackTrace
          this._stackTrace = (String) newValue;
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
    public ThrowableDetails build() {
      return new ThrowableDetails(
          _type,
          _message,
          _stackTrace);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code type} property in the builder.
     * @param type  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder type(Class<? extends Throwable> type) {
      JodaBeanUtils.notNull(type, "type");
      this._type = type;
      return this;
    }

    /**
     * Sets the {@code message} property in the builder.
     * @param message  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder message(String message) {
      JodaBeanUtils.notNull(message, "message");
      this._message = message;
      return this;
    }

    /**
     * Sets the {@code stackTrace} property in the builder.
     * @param stackTrace  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder stackTrace(String stackTrace) {
      JodaBeanUtils.notNull(stackTrace, "stackTrace");
      this._stackTrace = stackTrace;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(128);
      buf.append("ThrowableDetails.Builder{");
      buf.append("type").append('=').append(JodaBeanUtils.toString(_type)).append(',').append(' ');
      buf.append("message").append('=').append(JodaBeanUtils.toString(_message)).append(',').append(' ');
      buf.append("stackTrace").append('=').append(JodaBeanUtils.toString(_stackTrace));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
