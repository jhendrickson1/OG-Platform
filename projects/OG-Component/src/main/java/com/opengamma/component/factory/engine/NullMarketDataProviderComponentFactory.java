/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */

package com.opengamma.component.factory.engine;

import java.util.LinkedHashMap;
import java.util.Map;

import org.fudgemsg.FudgeContext;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.component.ComponentInfo;
import com.opengamma.component.ComponentRepository;
import com.opengamma.component.factory.AbstractComponentFactory;
import com.opengamma.engine.marketdata.InMemoryNamedMarketDataSpecificationRepository;
import com.opengamma.engine.marketdata.MarketDataProviderFactory;
import com.opengamma.engine.marketdata.NamedMarketDataSpecificationRepository;
import com.opengamma.util.fudgemsg.OpenGammaFudgeContext;

/**
 * A MarketDataProvider that provides no data.
 *
 * This provider should be used when no live market data is needed or available.
 * It provides an empty NamedMarketDataSpecificationRepository (which is required to be present).
 * If used it should *NOT* be referenced as a liveMarketDataProviderFactory to the
 * MarketDataProviderResolverComponentFactory (you shouldn't provide any value for this option)
 *
 */
@BeanDefinition
public class NullMarketDataProviderComponentFactory extends AbstractComponentFactory {

    /**
     * The classifier under which to publish.
     */
    @PropertyDefinition(validate = "notNull")
    private String _classifier;
    /**
     * The Fudge context.
     */
    @PropertyDefinition(validate = "notNull")
    private FudgeContext _fudgeContext = OpenGammaFudgeContext.getInstance();

    @Override
    public void init(ComponentRepository repo, LinkedHashMap<String, String> configuration) throws Exception {
      initMarketDataProviderFactory(repo);
    }

    protected MarketDataProviderFactory initMarketDataProviderFactory(final ComponentRepository repo) {
      InMemoryNamedMarketDataSpecificationRepository specRepository = new InMemoryNamedMarketDataSpecificationRepository();

      final ComponentInfo specRepositoryInfo = new ComponentInfo(NamedMarketDataSpecificationRepository.class, getClassifier());
      repo.registerComponent(specRepositoryInfo, specRepository);

      final ComponentInfo liveSourcesInfo = new ComponentInfo(NamedMarketDataSpecificationRepository.class, "live");
      repo.registerComponent(liveSourcesInfo, specRepository);

      return null;
    }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code NullMarketDataProviderComponentFactory}.
   * @return the meta-bean, not null
   */
  public static NullMarketDataProviderComponentFactory.Meta meta() {
    return NullMarketDataProviderComponentFactory.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(NullMarketDataProviderComponentFactory.Meta.INSTANCE);
  }

  @Override
  public NullMarketDataProviderComponentFactory.Meta metaBean() {
    return NullMarketDataProviderComponentFactory.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -281470431:  // classifier
        return getClassifier();
      case -917704420:  // fudgeContext
        return getFudgeContext();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -281470431:  // classifier
        setClassifier((String) newValue);
        return;
      case -917704420:  // fudgeContext
        setFudgeContext((FudgeContext) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_classifier, "classifier");
    JodaBeanUtils.notNull(_fudgeContext, "fudgeContext");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      NullMarketDataProviderComponentFactory other = (NullMarketDataProviderComponentFactory) obj;
      return JodaBeanUtils.equal(getClassifier(), other.getClassifier()) &&
          JodaBeanUtils.equal(getFudgeContext(), other.getFudgeContext()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getClassifier());
    hash += hash * 31 + JodaBeanUtils.hashCode(getFudgeContext());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the classifier under which to publish.
   * @return the value of the property, not null
   */
  public String getClassifier() {
    return _classifier;
  }

  /**
   * Sets the classifier under which to publish.
   * @param classifier  the new value of the property, not null
   */
  public void setClassifier(String classifier) {
    JodaBeanUtils.notNull(classifier, "classifier");
    this._classifier = classifier;
  }

  /**
   * Gets the the {@code classifier} property.
   * @return the property, not null
   */
  public final Property<String> classifier() {
    return metaBean().classifier().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the Fudge context.
   * @return the value of the property, not null
   */
  public FudgeContext getFudgeContext() {
    return _fudgeContext;
  }

  /**
   * Sets the Fudge context.
   * @param fudgeContext  the new value of the property, not null
   */
  public void setFudgeContext(FudgeContext fudgeContext) {
    JodaBeanUtils.notNull(fudgeContext, "fudgeContext");
    this._fudgeContext = fudgeContext;
  }

  /**
   * Gets the the {@code fudgeContext} property.
   * @return the property, not null
   */
  public final Property<FudgeContext> fudgeContext() {
    return metaBean().fudgeContext().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code NullMarketDataProviderComponentFactory}.
   */
  public static class Meta extends AbstractComponentFactory.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code classifier} property.
     */
    private final MetaProperty<String> _classifier = DirectMetaProperty.ofReadWrite(
        this, "classifier", NullMarketDataProviderComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code fudgeContext} property.
     */
    private final MetaProperty<FudgeContext> _fudgeContext = DirectMetaProperty.ofReadWrite(
        this, "fudgeContext", NullMarketDataProviderComponentFactory.class, FudgeContext.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "classifier",
        "fudgeContext");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -281470431:  // classifier
          return _classifier;
        case -917704420:  // fudgeContext
          return _fudgeContext;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends NullMarketDataProviderComponentFactory> builder() {
      return new DirectBeanBuilder<NullMarketDataProviderComponentFactory>(new NullMarketDataProviderComponentFactory());
    }

    @Override
    public Class<? extends NullMarketDataProviderComponentFactory> beanType() {
      return NullMarketDataProviderComponentFactory.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code classifier} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> classifier() {
      return _classifier;
    }

    /**
     * The meta-property for the {@code fudgeContext} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<FudgeContext> fudgeContext() {
      return _fudgeContext;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
