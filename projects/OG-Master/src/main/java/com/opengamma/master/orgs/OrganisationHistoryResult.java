/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.orgs;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.master.AbstractHistoryResult;
import com.opengamma.util.PublicSPI;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Result providing the history of an organisation.
 * <p/>
 * The returned documents may be a mixture of versions and corrections.
 * The document instant fields are used to identify which are which.
 * See {@link com.opengamma.master.orgs.OrganisationHistoryRequest} for more details.
 */
@PublicSPI
@BeanDefinition
public class OrganisationHistoryResult extends AbstractHistoryResult<OrganisationDocument> {

  /**
   * Creates an instance.
   */
  public OrganisationHistoryResult() {
  }

  /**
   * Creates an instance from a collection of documents.
   *
   * @param coll the collection of documents to add, not null
   */
  public OrganisationHistoryResult(Collection<OrganisationDocument> coll) {
    super(coll);
  }

  //-------------------------------------------------------------------------

  /**
   * Gets the returned organisations from within the documents.
   *
   * @return the organisations, not null
   */
  public List<ManageableOrganisation> getOrganisations() {
    List<ManageableOrganisation> result = new ArrayList<ManageableOrganisation>();
    if (getDocuments() != null) {
      for (OrganisationDocument doc : getDocuments()) {
        result.add(doc.getOrganisation());
      }
    }
    return result;
  }

  /**
   * Gets the first organisation, or null if no documents.
   *
   * @return the first organisation, null if none
   */
  public ManageableOrganisation getFirstOrganisation() {
    return getDocuments().size() > 0 ? getDocuments().get(0).getOrganisation() : null;
  }

  /**
   * Gets the single result expected from a query.
   * <p/>
   * This throws an exception if more than 1 result is actually available.
   * Thus, this method implies an assumption about uniqueness of the queried organisation.
   *
   * @return the matching organisation, not null
   * @throws IllegalStateException if no organisation was found
   */
  public ManageableOrganisation getSingleOrganisation() {
    if (getDocuments().size() != 1) {
      throw new OpenGammaRuntimeException("Expecting zero or single resulting match, and was " + getDocuments().size());
    } else {
      return getDocuments().get(0).getOrganisation();
    }
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code OrganisationHistoryResult}.
   * @return the meta-bean, not null
   */
  public static OrganisationHistoryResult.Meta meta() {
    return OrganisationHistoryResult.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(OrganisationHistoryResult.Meta.INSTANCE);
  }

  @Override
  public OrganisationHistoryResult.Meta metaBean() {
    return OrganisationHistoryResult.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      return super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code OrganisationHistoryResult}.
   */
  public static class Meta extends AbstractHistoryResult.Meta<OrganisationDocument> {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap());

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    public BeanBuilder<? extends OrganisationHistoryResult> builder() {
      return new DirectBeanBuilder<OrganisationHistoryResult>(new OrganisationHistoryResult());
    }

    @Override
    public Class<? extends OrganisationHistoryResult> beanType() {
      return OrganisationHistoryResult.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
